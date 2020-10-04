package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.io.repository.RegisteredUserRepo;
import in.bsnl.mobile.app.ws.network.FirebaseNetworkClient;
import in.bsnl.mobile.app.ws.network.FirebaseUser;
import in.bsnl.mobile.app.ws.service.RegisteredUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.image.BandCombineOp;

@Service
public class RegisteredUserImpl implements RegisteredUserService {
    Logger log = Logger.getLogger(RegisteredUserImpl.class.getName());
    @Autowired
    RegisteredUserRepo repo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RegisteredUser getRegisteredUser(String email) {
        RegisteredUser registeredUser = repo.getRegisteredUser(email);
        if (registeredUser == null || registeredUser.getUid() == null) {
            FirebaseUser firebaseUser = FirebaseNetworkClient.getUserByEmail(email);
            if (firebaseUser == null || firebaseUser.getUid() == null) {
                log.debug("no record was found in firebase for this email :"+email);
                return null;
            }

            if (registeredUser == null) {
                log.debug("record was not found in registerUser table for this email: "+email+".. may the user is logging first time");
                registeredUser = new RegisteredUser();
                registeredUser.setEmail(firebaseUser.getEmail());
                registeredUser.setUid(bCryptPasswordEncoder.encode(firebaseUser.getUid()));
                saveRegisteredUser(registeredUser);
                log.debug("new record is created in registerUser table");
            } else if (registeredUser.getUid() == null) {
                log.debug("registerUser uid is null in database, so updating it with input user uid");
                updateUidByEncription(email, firebaseUser.getUid()); // updateUid function encodes firewbaseUser uid
            }
        }
        return registeredUser;
    }

    @Override
    public boolean isUserAuthenticated(String email, String inputUid) {
        RegisteredUser registeredUser = repo.getRegisteredUser(email);
        if (bCryptPasswordEncoder.matches(inputUid, registeredUser.getUid())) {
            log.debug(email + ": user authenticated: input user uid is matched with stored registered user uid");
            return true;
        }
        else {
            log.debug(email+" input user uid is not matched with registerUser uid");
            FirebaseUser firebaseUser = FirebaseNetworkClient.getUserByEmail(email);
            if (bCryptPasswordEncoder.matches(inputUid,bCryptPasswordEncoder.encode(firebaseUser.getUid()))) {
                log.debug(email+" user authenticated, input user uid matches with firebaseUser uid");
                updateUidByEncription(email,inputUid);
                return true;
            }else {
                log.debug(email+" user is not authenticated");
                return false;
            }

        }
    }

    @Override
    public void saveRegisteredUser(RegisteredUser registeredUser) {
        repo.save(registeredUser);
    }

    @Override
    public void deleteRegisteredUser(RegisteredUser registeredUser) {
        repo.delete(registeredUser);
    }

    @Override
    public void updateToken(String email, String token) {
        repo.updateToken(email, token);
    }

    @Override
    public void updateName(String email, String name) {
        repo.updateName(email, name);

    }

    @Override
    public void updateUidByEncription(String email, String uid) {
        repo.updateUid(email, bCryptPasswordEncoder.encode(uid));
    }

    @Override
    public void updateLoginstatus(String email, int i) {
        repo.updateLoginStatus(email, i);
    }
}
