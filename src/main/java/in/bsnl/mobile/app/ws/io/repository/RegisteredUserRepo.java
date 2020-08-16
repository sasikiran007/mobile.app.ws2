package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface  RegisteredUserRepo extends CrudRepository<RegisteredUser,Integer> {

    @Query("FROM registered_user where email like ?1")
    public RegisteredUser getRegisteredUser(String email);

    @Modifying
    @Query("UPDATE registered_user set token = ?2 where email = ?1")
    @Transactional
    public void updateToken(String email,String token);

    @Modifying
    @Query("UPDATE registered_user set name = ?2 where email = ?1")
    @Transactional
    public void updateName(String email,String name);

    @Modifying
    @Query("UPDATE registered_user set uid = ?2 where email = ?1")
    @Transactional
    public void updateUid(String email,String uid);

    @Modifying
    @Query("UPDATE registered_user set loginStatus = ?2 where email = ?1")
    @Transactional
    void updateLoginStatus(String email, int i);
}
