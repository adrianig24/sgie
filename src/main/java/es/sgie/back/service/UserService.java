package es.sgie.back.service;

import es.sgie.back.domain.User;
import es.sgie.back.domain.enums.Kind;
import es.sgie.back.exception.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

/**
 * Service for user operations
 */
public interface UserService {

    User findById(UUID id) throws ServiceException;

    /**
     * Security requirements
     */
    UserDetails loadUserByPic(String pic) throws ServiceException;
    List<User> findAllUsersByKind(Kind kind);

    User findByPic(String pic) throws ServiceException;
    User createUser(User user) throws ServiceException;
    User updateUser(User user) throws ServiceException;
    void disableUser(UUID id) throws ServiceException;


}
