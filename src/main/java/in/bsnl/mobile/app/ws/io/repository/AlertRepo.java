package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepo extends CrudRepository<Alert, Integer> {
    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'minor' ")
    int countServerMinorAlerts();

    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'major' ")
    int countServerMajorAlerts();

    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'critical' ")
    int countServerCriticalAlerts();

    @Query("select count(*) from alert where alert_section = 'server'")
    int countServerAllAlerts();

    /*@Query("select date from alert limit 1")
    String findDateOfAlert();*/
}
