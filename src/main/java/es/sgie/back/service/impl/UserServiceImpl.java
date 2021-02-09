package es.sgie.back.service.impl;

import es.sgie.back.domain.UAdmin;
import es.sgie.back.domain.UCustomer;
import es.sgie.back.domain.UPsychologist;
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

            if (connected.getPic() == user.getPic()) {
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
    public UCustomer takeTests(String pic) throws ServiceException {

        User u = this.findByPic(pic);

        if (u instanceof UCustomer) {
            UCustomer uc = (UCustomer) u;

            return uc;
        }
        return null;

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

    @Override
    public UAdmin createAdmin(UAdmin admin) throws ServiceException {
        if (admin == null) {
            log.error("Argument 'admin' is null in UserServiceImpl.createAdmin");
            throw new IllegalArgumentException("Argument 'admin' is null in UserServiceImpl.createAdmin");
        }

        admin.setActive(Boolean.TRUE);
        admin.setPic(admin.getEmail());
        admin.setPassword(this.bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setKind(Kind.ADMIN);

        try {
            return this.userRepostory.save(admin);
        } catch (Exception e) {
            log.error("Error creating Admin with data: " + admin.toString());
            throw new ServiceException("Error in UserServiceImpl.createAdmin: ", e);
        }
    }

    @Override
    public UAdmin updateAdmin(UAdmin admin) throws ServiceException{
        if (admin == null || admin.getId() == null) {
            log.error("Argument 'admin' is null in UserServiceImpl.updateAdmin");
            throw new IllegalArgumentException("Argument 'admin' is null in UserServiceImpl.updateAdmin");
        }

        try {
            return this.userRepostory.save(admin);
        } catch (Exception e) {
            log.error("Error updating User with data: " + admin.toString());
            throw new ServiceException("Error in UserServiceImpl.updateAdmin: ", e);
        }
    }

    @Override
    public void disableAdmin(UUID id)throws ServiceException {
        if (id == null) {
            log.error("Argument 'id' is null in UserServiceImpl.disableAdmin");
            throw new IllegalArgumentException("Argument 'id' is null in UserServiceImpl.disableAdmin");
        }

        try {
            User a = this.findById(id);
            a.setActive(Boolean.FALSE);
            a.setPassword(this.bCryptPasswordEncoder.encode(StringUtils.getDisabledPassword()));
            this.updateAdmin((UAdmin) a);
        } catch (Exception e) {
            log.error("Error disabling User with id: " + id);
            throw new ServiceException("Error in UserServiceImpl.disableAdmin: ", e);
        }
    }

    @Override
    public UPsychologist createMedical(UPsychologist medical) throws ServiceException {
        if (medical == null) {
            log.error("Argument 'medical' is null in UserServiceImpl.createMedical");
            throw new IllegalArgumentException("Argument 'medical' is null in UserServiceImpl.createMedical");
        }

        medical.setActive(Boolean.TRUE);
        medical.setPic(medical.getEmail());
        medical.setPassword(this.bCryptPasswordEncoder.encode(medical.getPassword()));
        medical.setKind(Kind.MEDICAL);

        try {
            return this.userRepostory.save(medical);
        } catch (Exception e) {
            log.error("Error creating Medical with data: " + medical.toString());
            throw new ServiceException("Error in UserServiceImpl.createMedical: ", e);
        }
    }

    @Override
    public UCustomer createCustomer(UCustomer customer) throws ServiceException {
        if (customer == null) {
            log.error("Argument 'customer' is null in UserServiceImpl.createCustomer");
            throw new IllegalArgumentException("Argument 'customer' is null in UserServiceImpl.createCustomer");
        }

        // PIC must be unique
        String PIC = null;
        Optional<User> u = null;

        //do-while to protect code from unprovable identical PICs
        do {
            PIC = StringUtils.getInitialPIC();
            u = this.userRepostory.findByPic(PIC);
        } while (u.isPresent());

        customer.setActive(Boolean.TRUE);
        customer.setKind(Kind.CUSTOMER);
        customer.setPassword(this.bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setPic(PIC);

        try {
            return this.userRepostory.save(customer);
        } catch (Exception e) {
            log.error("Error creating Customer with data: " + customer.toString());
            throw new ServiceException("Error in UserServiceImpl.createCustomer: ", e);
        }
    }

    public User getUserFromSecurityContext() throws ServiceException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        return this.findByPic(username);
    }

}
