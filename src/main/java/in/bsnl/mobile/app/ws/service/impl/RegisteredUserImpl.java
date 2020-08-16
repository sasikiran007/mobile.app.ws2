package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.io.repository.RegisteredUserRepo;
import in.bsnl.mobile.app.ws.network.FirebaseNetworkClient;
import in.bsnl.mobile.app.ws.network.FirebaseUser;
import in.bsnl.mobile.app.ws.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.image.BandCombineOp;

@Service
public class RegisteredUserImpl implements RegisteredUserService {
    @Autowired
    RegisteredUserRepo repo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public RegisteredUser getRegisteredUser(String email) {
        RegisteredUser registeredUser = repo.getRegisteredUser(email);
        if(registeredUser == null || registeredUser.getUid() == null) {

            FirebaseUser firebaseUser = FirebaseNetworkClient.getUserByEmail(email);
            if(firebaseUser == null  || firebaseUser.getUid() == null)
                return null;

            if(registeredUser == null) {
                registeredUser = new RegisteredUser();
                registeredUser.setEmail(firebaseUser.getEmail());
                registeredUser.setUid(bCryptPasswordEncoder.encode(firebaseUser.getUid()));
                saveRegisteredUser(registeredUser);
            }else if(registeredUser.getUid() == null){
                updateUid(email,firebaseUser.getUid());
            }
        }
        return registeredUser;
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
    public void updateToken(String email,String token){
        repo.updateToken(email,token);
    }

    @Override
    public void updateName(String email,String name) {
        repo.updateName(email,name);

    }

    @Override
    public void updateUid(String email,String uid) {
        repo.updateUid(email,uid);
    }

    @Override
    public void updateLoginstatus(String email, int i) {
        repo.updateLoginStatus(email,i);
    }
}
