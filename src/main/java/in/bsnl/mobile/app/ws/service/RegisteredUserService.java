package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;

public interface RegisteredUserService {
    public RegisteredUser getRegisteredUser(String email);

    public boolean isUserAuthenticated(String email,String inputUid);

    public void saveRegisteredUser(RegisteredUser registeredUser);

    public void deleteRegisteredUser(RegisteredUser registeredUser);

    public void updateToken(String email,String token);

    public void updateName(String email,String name);

    public void updateUidByEncription(String email,String uid);

    public void updateLoginstatus(String email, int i);
}
