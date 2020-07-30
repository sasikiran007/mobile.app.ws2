package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.Tracker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepo extends CrudRepository<Tracker,Integer> {
    @Query("SELECT trackerNumber from alert_tracker where scriptName like ?1")
    String getTackerNumber(String scriptName);

}
