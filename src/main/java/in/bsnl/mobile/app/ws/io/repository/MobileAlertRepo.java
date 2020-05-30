package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.MobileAlert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAlertRepo extends CrudRepository<MobileAlert, Integer> {


    //Iterable<MobileAlert> findAllByAlertId();

}
