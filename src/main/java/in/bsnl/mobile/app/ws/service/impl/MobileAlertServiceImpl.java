package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.*;
import in.bsnl.mobile.app.ws.io.repository.*;
import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.AlertDao;
import in.bsnl.mobile.app.ws.shared.dto.EventDto;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.shared.utils.MyUtilities;
import in.bsnl.mobile.app.ws.shared.utils.RandomAlertId;
import in.bsnl.mobile.app.ws.ui.model.response.*;
import org.apache.catalina.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class MobileAlertServiceImpl implements MobileAlertService {

    @Autowired
    MobileAlertRepo mobileAlertRepo;
    @Autowired
    AlertRepo alertRepo;
    @Autowired
    AlertOldRepo alertOldRepo;

    @Autowired
    TrackerRepo trackerRepo;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    EventIdRepo eventIdRepo;

    @Override
    public List<AlertDao> getAlerts() {
        List<AlertDao> returned = new ArrayList<>();
        if(getDataValidity()) {
            Iterable<Alert> alerts = alertRepo.findAll();
            for (Alert alert : alerts) {
                AlertDao alertDao = new AlertDao();
                BeanUtils.copyProperties(alert, alertDao);
                returned.add(alertDao);
            }
        }else  {
            Iterable<AlertOld> alerts = alertOldRepo.findAll();
            for (AlertOld alert : alerts) {
                AlertDao alertDao = new AlertDao();
                BeanUtils.copyProperties(alert, alertDao);
                returned.add(alertDao);
            }
        }
        return returned;
    }

    @Override
    public List<EventDto> getEvents(String alertLevel) {
        List<Event> events = eventRepo.getEvents(alertLevel);
        List<EventDto> returned = new ArrayList<>();
        for( Event event: events) {
            Integer id = Integer.parseInt(event.getEventId());
            EventId eventId = eventIdRepo.findById(id).orElse(null);
            if(eventId == null)
                continue;
            EventDto eventDto = new EventDto();
            BeanUtils.copyProperties(event,eventDto);
            BeanUtils.copyProperties(eventId,eventDto);
            returned.add(eventDto);
        }
        return returned;
    }


//    @Override
//    public List<MobileAlertDao> getMobileAlerts() {
//        boolean isMAEmpty = true;
//        boolean isAIDEmpty = false;
//        Iterable<MobileAlert> mobileAlerts =  mobileAlertRepo.findAll( );
//        if( ((ArrayList) mobileAlerts).isEmpty()) { isMAEmpty = true;}else  { isMAEmpty = false;}
//
//        Iterable<Alert> alerts = alertRepo.findAll();
//        for (Alert alert : alerts) {
//            if(alert.getAlertId() == null || alert.getAlertId().isEmpty()) {
//                isAIDEmpty = true;
//                alert.setAlertId(RandomAlertId.generate(20));
//            }
//        }
//        if(!isAIDEmpty) { //no fresh data
//            if(!isMAEmpty) { // also MobileAlert is not empty ==> nothing to do;
//            }
//            else  { // No mobile alets insert mobile alerts
//                mobileAlerts = alertToMobileAlert(alerts);
//                mobileAlertRepo.saveAll(mobileAlerts);
//            }
//        }
//        else {
//            alertRepo.deleteAll();
//            alertRepo.saveAll(alerts);
//            mobileAlertRepo.deleteAll();
//            mobileAlerts = alertToMobileAlert(alerts);
//            mobileAlertRepo.saveAll(mobileAlerts);
//        }
//        List<MobileAlertDao> returned = new ArrayList<>();
//        for(MobileAlert mobileAlert : mobileAlerts ) {
//            MobileAlertDao mobileAlertDao = new MobileAlertDao();
//            BeanUtils.copyProperties(mobileAlert, mobileAlertDao);
//            returned.add(mobileAlertDao);
//        }
//        return returned;
//    }

    private String getAlertDate(String scriptName) {
        return trackerRepo.getTackerNumber(scriptName);
    }

    private String getAlertDateOld(String scriptName) {
        return trackerRepo.getTackerNumberOld(scriptName);
    }

    @Override
    public List<AlertStatResponse> getAlertStats() {
        List<AlertStatResponse> alertStatResponses = new ArrayList<>();
        alertStatResponses.add(getAllAlertStats());
        alertStatResponses.add(getServerAlertStats());
        alertStatResponses.add(getDatabaseAlertStats());
        return alertStatResponses;

    }


    @Override
    public List<TrackerResponse> getTrackers() {
        Iterable<Tracker> trackers = trackerRepo.findAll();
        List<TrackerResponse> returned = new ArrayList<>();
        for (Tracker tracker : trackers) {
            TrackerResponse trackerResponse = new TrackerResponse();
            BeanUtils.copyProperties(tracker, trackerResponse);
            returned.add(trackerResponse);
        }

        return returned;
    }


    //*** Server Alert stats
    private AlertStatResponse getServerAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getServerAlertCount());
        alertStatResponse.setCriticalCount(getServerCriticalCount());
        alertStatResponse.setMajorCount(getServerMajorCount());
        alertStatResponse.setMinorCount(getServerMinorCount());
        alertStatResponse.setSection("server");

        if (getDataValidity())
            alertStatResponse.setDate(getAlertDate("blue"));
        else
            alertStatResponse.setDate(getAlertDateOld("blue"));
        return alertStatResponse;
    }

    private int getServerAlertCount() {
        if (getDataValidity()) return alertRepo.countServerAllAlerts();
        else return alertOldRepo.countServerAllAlerts();
        //return 0;
    }

    private int getServerCriticalCount() {
        if (getDataValidity()) return alertRepo.countServerCriticalAlerts();
        else return alertOldRepo.countServerCriticalAlerts();
        //return 0;
    }

    private int getServerMajorCount() {
        if (getDataValidity()) return alertRepo.countServerMajorAlerts();
        else return alertOldRepo.countServerMajorAlerts();
        //return 0;
    }

    private int getServerMinorCount() {
        if (getDataValidity()) return alertRepo.countServerMinorAlerts();
        else return alertOldRepo.countServerMinorAlerts();
        //return 0;
    }
    //*** Server Alert stats

    // *************** Database Stats*************************
    private AlertStatResponse getDatabaseAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getDatabaseAlertCount());
        alertStatResponse.setCriticalCount(getDatabaseCriticalCount());

        alertStatResponse.setMajorCount(getDatabaseMajorCount());
        alertStatResponse.setMinorCount(getDatabaseMinorCount());
        alertStatResponse.setSection("database");
        if (getDataValidity())
            alertStatResponse.setDate(getAlertDate("blue"));
        else
            alertStatResponse.setDate(getAlertDateOld("blue"));
        return alertStatResponse;

    }

    private int getDatabaseAlertCount() {
        if (getDataValidity()) return alertRepo.countDatabaseAllAlerts();
        else return alertOldRepo.countDatabaseAllAlerts();
        //return 0;
    }

    private int getDatabaseCriticalCount() {
        if (getDataValidity()) return alertRepo.countDatabaseCriticalAlerts();
        else return alertOldRepo.countDatabaseCriticalAlerts();
        //return 0;
    }

    private int getDatabaseMajorCount() {
        if (getDataValidity()) return alertRepo.countDatabaseMajorAlerts();
        else return alertOldRepo.countDatabaseMajorAlerts();
        //return 0;
    }

    private int getDatabaseMinorCount() {
        if (getDataValidity()) return alertRepo.countDatabaseMinorAlerts();
        else return alertOldRepo.countDatabaseMinorAlerts();
        //return 0;
    }
    //***************************** Database alerts ******************************


//    May302020


    //********************* All Alert stats ************************

    private AlertStatResponse getAllAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getAllAlertCount());
        alertStatResponse.setCriticalCount(getAllCriticalCount());
        alertStatResponse.setMajorCount(getAllMajorCount());
        alertStatResponse.setMinorCount(getAllMinorCount());
        alertStatResponse.setSection("all");

        if (getDataValidity())
            alertStatResponse.setDate(getAlertDate("blue"));
        else
            alertStatResponse.setDate(getAlertDateOld("blue"));
        return alertStatResponse;
    }

    private int getAllAlertCount() {
        if (getDataValidity()) return alertRepo.countAllAlerts();
        else return alertOldRepo.countAllAlerts();
        //return 0;
    }

    private int getAllCriticalCount() {
        if (getDataValidity()) return alertRepo.countCriticalAlerts();
        else return alertOldRepo.countCriticalAlerts();
        //return 0;
    }

    private int getAllMajorCount() {
        if (getDataValidity()) return alertRepo.countMajorAlerts();
        else return alertOldRepo.countMajorAlerts();
        //return 0;
    }

    private int getAllMinorCount() {
        if (getDataValidity()) return alertRepo.countMinorAlerts();
        else return alertOldRepo.countMinorAlerts();
        //return 0;
    }
    //********************* All Alert stats ************************

    private boolean getDataValidity() {
        if (getAlertDate("blue").equals("0"))
            return false;
        else
            return true;
    }


//    May302020

    private List<MobileAlert> alertToMobileAlert(Iterable<Alert> alerts) {
        List<MobileAlert> mobileAlerts = new ArrayList<>();
        for (Alert alert : alerts) {
            MobileAlert mobileAlert = MyUtilities.convertAlertToMobileAlert(alert);
            mobileAlerts.add(mobileAlert);
        }
        return mobileAlerts;
    }
}
