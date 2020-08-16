package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.User;
import in.bsnl.mobile.app.ws.io.entity.UserProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepo extends CrudRepository<UserProject,Integer> {
    @Query("SELECT project from user_project where email = ?1")
    public List<String> getProjects(String email);
}
