package es.sgie.back.service;

import es.sgie.back.domain.UAdmin;
import es.sgie.back.domain.UCustomer;
import es.sgie.back.domain.UPsychologist;
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

    UAdmin createAdmin(UAdmin admin) throws ServiceException;
    UAdmin updateAdmin(UAdmin admin) throws ServiceException;
    void disableAdmin(UUID id) throws ServiceException;

   // UCustomer buyTests() throws ServiceException;
    UCustomer takeTests(String pic) throws ServiceException;

    UPsychologist createMedical(UPsychologist medical) throws ServiceException;
    //Medical updateMedical(Medical medical);
    //Boolean disableMedical(UUID id); // change password and disable user

    UCustomer createCustomer(UCustomer customer) throws ServiceException;
    // Customer updateCustomerl(Customer customer);
    // Boolean disableCustomer(UUID id); // change password and disable user
}
