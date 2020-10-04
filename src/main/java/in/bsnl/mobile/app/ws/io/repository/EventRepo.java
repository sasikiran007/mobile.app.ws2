package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<Event,Integer> {
    @Query("FROM event WHERE alert_level = ?1")
    public List<Event> getEvents(String alertLevel);
}
