package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.RegisteredUser;
import in.bsnl.mobile.app.ws.io.entity.User;
import in.bsnl.mobile.app.ws.shared.dto.UserDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {

    @Query("SELECT count(*) from user where email like ?1")
    public Integer getUserCount(String email);

    @Query("FROM user WHERE email = ?1")
    public User getUser(String email);

}
