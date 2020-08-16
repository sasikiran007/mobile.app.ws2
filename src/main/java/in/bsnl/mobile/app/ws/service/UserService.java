package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.network.FirebaseUser;
import in.bsnl.mobile.app.ws.shared.dto.UserDao;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    public UserDao getUser(String email);
//    public FirebaseUser getFirebaseUserByEmail(String email);

}
