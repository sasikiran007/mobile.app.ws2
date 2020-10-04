package in.bsnl.mobile.app.ws.ui.controller;

import in.bsnl.mobile.app.ws.exceptions.UserServiceException;
import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.security.AuthorizationFilter;
import in.bsnl.mobile.app.ws.service.RegisteredUserService;
import in.bsnl.mobile.app.ws.service.UserService;
import in.bsnl.mobile.app.ws.shared.dto.UserDao;
import in.bsnl.mobile.app.ws.ui.model.request.UserLoginRequest;
import in.bsnl.mobile.app.ws.ui.model.response.UserResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());
    static private final String TAG = "UserController : ";
    @Autowired
    UserService userService;

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public UserResponse getUser(@RequestBody UserLoginRequest userLoginRequest) {
        logger.debug(TAG + "Post /signupp is called");
        String email = userLoginRequest.getEmail();

        //[ Input data checking ]
        if (userLoginRequest.getToken() == null)
            throw new UserServiceException("Token cannot be null");
        if (userLoginRequest.getEmail() == null)
            throw new UserServiceException("Email cannot be null");
        if (userLoginRequest.getUid() == null)
            throw new UserServiceException("Uid cannot be null");


        //[ check input user  authorization ]
        UserDao userDao = userService.getUser(email);
        if (userDao.getEmail() == null) {
//            registeredUserService.deleteRegisteredUser(registeredUser);
            //Todo delete firebase user
            throw new UserServiceException(email + ": This email is not authorised");
        }

        //[ get registered user from repo
        //    -- if user is new then new registered user creates from Firebase
        RegisteredUser registeredUser = registeredUserService.getRegisteredUser(email);

        // Check input user authenticity
        if(! registeredUserService.isUserAuthenticated(userLoginRequest.getEmail(),userLoginRequest.getUid())) {
            throw new UserServiceException(("user is not authenticated"));
        }

        // Update registered user details from user data
        if (registeredUser.getToken() == null || !registeredUser.getToken().equals(userLoginRequest.getToken())) {
            registeredUserService.updateToken(email, userLoginRequest.getToken());
        }


        if (registeredUser.getName() == null || !registeredUser.getName().equals(userDao.getName()))
            registeredUserService.updateName(email, userDao.getName());
        registeredUserService.updateLoginstatus(email, 1);
//        }
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
        logger.debug(TAG + "Post /test is called");
        return "tested";
    }

}
