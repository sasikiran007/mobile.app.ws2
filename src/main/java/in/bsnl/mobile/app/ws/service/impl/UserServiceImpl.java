package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.exceptions.UserServiceException;
import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.io.entity.User;
import in.bsnl.mobile.app.ws.io.repository.RegisteredUserRepo;
import in.bsnl.mobile.app.ws.io.repository.UserProjectRepo;
import in.bsnl.mobile.app.ws.io.repository.UserRepo;
import in.bsnl.mobile.app.ws.io.repository.UserRoleRepo;
import in.bsnl.mobile.app.ws.network.FirebaseNetworkClient;
import in.bsnl.mobile.app.ws.network.FirebaseUser;
import in.bsnl.mobile.app.ws.service.RegisteredUserService;
import in.bsnl.mobile.app.ws.service.UserService;
import in.bsnl.mobile.app.ws.shared.dto.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserProjectRepo userProjectRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDao getUser(String email) {
        UserDao userDao = new UserDao();

        if(userRepo.getUserCount(email) == 1 ){
            User user  = userRepo.getUser(email);
            userDao.setName(user.getName());
            userDao.setEmail(user.getEmail());
            userDao.setDesignation(user.getDesignation());
            userDao.setMobile(user.getMobile());
            userDao.setProjects(userProjectRepo.getProjects(email));
            userDao.setRoles(userRoleRepo.getRoles(email));
        }else {
            return userDao;
        }

        return userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
//        User user = userRepo.getUser(email);
//        RegisteredUser registeredUser = new RegisteredUser();
//        if(user == null) {
//            registeredUser = registeredUserRepo.getRegisteredUser(email);
//            if(registeredUser != null) {
//                //TODO Delete firebase entry
//            }
////            throw new UserServiceException(email + ": This email is not authorized ");
////            return null;
//        }
//        registeredUser = registeredUserRepo.getRegisteredUser(email);
//        if(registeredUser == null || registeredUser.getUid() == null) {
//            FirebaseUser firebaseUser = FirebaseNetworkClient.getUserByEmail(email);
//            if(firebaseUser.getUid() == null || firebaseUser.getEmail() == null)
//                throw new UserServiceException("Somthing is wrong, no data found in firebase");
//            registeredUser = new RegisteredUser();
//            registeredUser.setEmail(email);
//            registeredUser.setUid(bCryptPasswordEncoder.encode(firebaseUser.getUid()));
//            registeredUser.setName(user.getName());
//            registeredUser.setLoginStatus(1);
//            registeredUserRepo.save(registeredUser);
//        }

        FirebaseUser firebaseUser = FirebaseNetworkClient.getUserByEmail(email);
        if(firebaseUser.getUid() == null || firebaseUser.getEmail() == null)
                throw new UsernameNotFoundException("Somthing is wrong, no data found in firebase");
        return new org.springframework.security.core.userdetails.User(firebaseUser.getEmail()
        , bCryptPasswordEncoder.encode(firebaseUser.getUid()),true,true,true,true,new ArrayList<>());
    }
}
