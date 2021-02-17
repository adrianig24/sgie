package es.sgie.back.service.impl;

import es.sgie.back.domain.User;
import es.sgie.back.domain.enums.Kind;
import es.sgie.back.exception.ServiceException;
import es.sgie.back.repository.UserRepository;
import es.sgie.back.security.Role;
import es.sgie.back.security.UserDetailsMapper;
import es.sgie.back.service.UserService;
import es.sgie.back.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepostory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findById(UUID id) throws ServiceException {
        Optional<User> optional = this.userRepostory.findById(id);
        User user = null;

        if (optional.isPresent() && optional.get().getActive()) {
            user = optional.get();

            User connected = this.getUserFromSecurityContext();

            if (connected.getRole() == Role.ROLE_ADM.toString()) {
                return user;
            }

            if (connected.getDNI() == user.getDNI()) {
                return user;
            } else {
                log.error("User whith id: " + connected.getId() + ", get information about different user id: " + user.getId());
                throw new ServiceException("User whith id: " + connected.getId() + ", get information about different user id: " + user.getId());
            }
        }

        log.error("User whith id: " + id + ", not exist or not enabled");
        throw new ServiceException("User whith ID: " + id + ", not exist or not enabled");
    }

    @Override
    public User findByPic(String pic) throws ServiceException {
        Optional<User> user = this.userRepostory.findByPic(pic);
        if (user.isPresent() && user.get().getActive()) {
            return user.get();
        }
        log.error("User whith PIC: " + pic + ", not exist or not enabled");
        throw new ServiceException("User whith PIC: " + pic + ", not exist or not enabled");
    }

    @Override
    public User createUser(User user) throws ServiceException {

        user.setActive(Boolean.TRUE);
       // user.setPic(user.getDNI());
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setKind(Kind.ADMIN);

        try {
            return this.userRepostory.save(user);
        } catch (Exception e) {
            log.error("Error creating Admin with data: " + user.toString());
            throw new ServiceException("Error in UserServiceImpl.createAdmin: ", e);
        }
    }

    @Override
    public User updateUser(User user) throws ServiceException {
        if (user == null || user.getId() == null) {
            log.error("Argument 'user' is null in UserServiceImpl.updateAdmin");
            throw new IllegalArgumentException("Argument 'user' is null in UserServiceImpl.updateAdmin");
        }

        try {
            return this.userRepostory.save(user);
        } catch (Exception e) {
            log.error("Error updating User with data: " + user.toString());
            throw new ServiceException("Error in UserServiceImpl.updateAdmin: ", e);
        }

    }

    @Override
    public void disableUser(UUID id) throws ServiceException {
        if (id == null) {
            log.error("Argument 'id' is null in UserServiceImpl.disableAdmin");
            throw new IllegalArgumentException("Argument 'id' is null in UserServiceImpl.disableAdmin");
        }

        try {
            User a = this.findById(id);
            a.setActive(Boolean.FALSE);
            a.setPassword(this.bCryptPasswordEncoder.encode(StringUtils.getDisabledPassword()));
            this.updateUser((User) a);
        } catch (Exception e) {
            log.error("Error disabling User with id: " + id);
            throw new ServiceException("Error in UserServiceImpl.disableAdmin: ", e);
        }
    }




    @Override
    public UserDetails loadUserByPic(String pic) throws ServiceException {
        Optional<User> user = this.userRepostory.findByPic(pic);
        if (user.isPresent() && user.get().getActive()) {
            return UserDetailsMapper.build(user.get());
        }
        log.error("User whith PIC: " + pic + ", not exist");
        throw new ServiceException("User whith PIC: " + pic + ", not exist or not enabled");
    }


    @Override
    public List<User> findAllUsersByKind(Kind kind){
        List<User> u = this.userRepostory.findAllUsersByKind(kind);
        return u;
    }

    public User getUserFromSecurityContext() throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        return this.findByPic(username);
    }



}
