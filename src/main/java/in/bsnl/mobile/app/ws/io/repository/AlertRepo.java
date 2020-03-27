package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepo extends CrudRepository<Alert, Integer> {
}
