package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.EventId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventIdRepo extends CrudRepository<EventId,Integer> {
    @Query("FROM event_id WHERE alert_level = ?1")
    public List<EventId> getEventIds(String alertLevel);
}
