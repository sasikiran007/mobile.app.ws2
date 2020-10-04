package in.bsnl.mobile.app.ws.io.repository;

import in.bsnl.mobile.app.ws.io.entity.AlertOld;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertOldRepo extends CrudRepository<AlertOld, Integer> {

    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'minor' ")
    int countServerMinorAlerts();

    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'major' ")
    int countServerMajorAlerts();

    @Query("select count(*) from alert where alert_section = 'server' and alert_level = 'critical' ")
    int countServerCriticalAlerts();

    @Query("select count(*) from alert where alert_section = 'server'")
    int countServerAllAlerts();

    /* New code for all type alert stats May-30-2020*/

    @Query("select count(*) from alert where alert_level = 'minor' ")
    int countMinorAlerts();

    @Query("select count(*) from alert where  alert_level = 'major' ")
    int countMajorAlerts();

    @Query("select count(*) from alert where  alert_level = 'critical' ")
    int countCriticalAlerts();

    @Query("select count(*) from alert")
    int countAllAlerts();

    /* Database Alert stats */
    @Query("select count(*) from alert where alert_section = 'database' and alert_level = 'minor' ")
    int countDatabaseMinorAlerts();

    @Query("select count(*) from alert where alert_section = 'database' and alert_level = 'major' ")
    int countDatabaseMajorAlerts();

    @Query("select count(*) from alert where alert_section = 'database' and alert_level = 'critical' ")
    int countDatabaseCriticalAlerts();

    @Query("select count(*) from alert where alert_section = 'database'")
    int countDatabaseAllAlerts();
    /******** May-30-2020 ********************/

    /*@Query("select date from alert limit 1")
    String findDateOfAlert();*/
}
