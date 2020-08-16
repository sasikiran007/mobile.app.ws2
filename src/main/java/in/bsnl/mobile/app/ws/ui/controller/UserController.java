package in.bsnl.mobile.app.ws.ui.controller;

import in.bsnl.mobile.app.ws.exceptions.UserServiceException;
import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.service.RegisteredUserService;
import in.bsnl.mobile.app.ws.service.UserService;
import in.bsnl.mobile.app.ws.shared.dto.UserDao;
import in.bsnl.mobile.app.ws.ui.model.request.UserLoginRequest;
import in.bsnl.mobile.app.ws.ui.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public UserResponse getUser(@RequestBody UserLoginRequest userLoginRequest) {
        String email = userLoginRequest.getEmail();

        if (userLoginRequest.getToken() == null)
            throw new UserServiceException("Token cannot be null");
        if (userLoginRequest.getEmail() == null)
            throw new UserServiceException("Email cannot be null");
        if (userLoginRequest.getUid() == null)
            throw new UserServiceException("Uid cannot be null");

        RegisteredUser registeredUser = registeredUserService.getRegisteredUser(email);
        if (registeredUser == null) throw new UserServiceException("Internal server error");

        if (! bCryptPasswordEncoder.matches(userLoginRequest.getUid(),registeredUser.getUid()) ) {
            throw new UserServiceException(("Internal server error"));
        }

        if (registeredUser.getToken() == null || !registeredUser.getToken().equals(userLoginRequest.getToken()) ) {
            registeredUserService.updateToken(email, userLoginRequest.getToken());
        }

        UserDao userDao = userService.getUser(email);
        if (userDao.getEmail() == null) {
            registeredUserService.deleteRegisteredUser(registeredUser);
            //Todo delete firebase user
            throw new UserServiceException(email + ": This email is not authenticated");
        }

        if (registeredUser.getName() == null || !registeredUser.getName().equals(userDao.getName()))
            registeredUserService.updateName(email, userDao.getName());
        registeredUserService.updateLoginstatus(email, 1);
        UserResponse userResponse = new UserResponse();
        userResponse.setDesignation(userDao.getDesignation());
        userResponse.setEmail(userDao.getEmail());
        userResponse.setMobile(userDao.getMobile());
        userResponse.setName(userDao.getName());
        userResponse.setProjects(userDao.getProjects());
        userResponse.setRoles(userDao.getRoles());
        return userResponse;
    }

    @GetMapping("/test")
    public String getTest() {
        return "tested";
    }

}
