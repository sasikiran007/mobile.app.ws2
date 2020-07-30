package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.Alert;
import in.bsnl.mobile.app.ws.io.entity.MobileAlert;
import in.bsnl.mobile.app.ws.io.entity.Tracker;
import in.bsnl.mobile.app.ws.io.repository.AlertRepo;
import in.bsnl.mobile.app.ws.io.repository.MobileAlertRepo;
import in.bsnl.mobile.app.ws.io.repository.TrackerRepo;
import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.AlertDao;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.shared.utils.MyUtilities;
import in.bsnl.mobile.app.ws.shared.utils.RandomAlertId;
import in.bsnl.mobile.app.ws.ui.model.response.AlertResponse;
import in.bsnl.mobile.app.ws.ui.model.response.AlertStatResponse;
import in.bsnl.mobile.app.ws.ui.model.response.ServerAlertStatResponse;
import in.bsnl.mobile.app.ws.ui.model.response.TrackerResponse;
import org.apache.catalina.Server;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileAlertServiceImpl implements MobileAlertService {

    @Autowired
    MobileAlertRepo mobileAlertRepo;
    @Autowired
    AlertRepo alertRepo;

    @Autowired
    TrackerRepo trackerRepo;
    @Override
    public List<AlertDao> getAlerts() {
        Iterable<Alert> alerts = alertRepo.findAll();
        List<AlertDao> returned = new ArrayList<>();
        for(Alert alert : alerts) {
            AlertDao alertDao = new AlertDao();
            BeanUtils.copyProperties(alert,alertDao);
            returned.add(alertDao);
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
        Iterable<Tracker> trackers =  trackerRepo.findAll();
        List<TrackerResponse> returned = new ArrayList<>();
        for(Tracker tracker  : trackers) {
            TrackerResponse trackerResponse = new TrackerResponse();
            BeanUtils.copyProperties(tracker,trackerResponse);
            returned.add(trackerResponse);
        }

        return returned;
    }

    private int getServerAlertCount() {
        return alertRepo.countServerAllAlerts();
        //return 0;
    }

    private int getServerCriticalCount() {
        return alertRepo.countServerCriticalAlerts();
        //return 0;
    }

    private int getServerMajorCount() {
        return alertRepo.countServerMajorAlerts();
        //return 0;
    }

    private int getServerMinorCount() {
        return alertRepo.countServerMinorAlerts();
        //return 0;
    }

    private AlertStatResponse getServerAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getServerAlertCount());
        alertStatResponse.setCriticalCount(getServerCriticalCount());
        alertStatResponse.setDate(getAlertDate("blue"));
        alertStatResponse.setMajorCount(getServerMajorCount());
        alertStatResponse.setMinorCount(getServerMinorCount());
        alertStatResponse.setSection("server");
        return alertStatResponse;
    }

    private AlertStatResponse getDatabaseAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getDatabaseAlertCount());
        alertStatResponse.setCriticalCount(getDatabaseCriticalCount());
        alertStatResponse.setDate(getAlertDate("blue"));
        alertStatResponse.setMajorCount(getDatabaseMajorCount());
        alertStatResponse.setMinorCount(getDatabaseMinorCount());
        alertStatResponse.setSection("database");
        return alertStatResponse;

    }

//    May302020


    private int getAllAlertCount() {
        return alertRepo.countAllAlerts();
        //return 0;
    }

    private int getAllCriticalCount() {
        return alertRepo.countCriticalAlerts();
        //return 0;
    }

    private int getAllMajorCount() {
        return alertRepo.countMajorAlerts();
        //return 0;
    }

    private int getAllMinorCount() {
        return alertRepo.countMinorAlerts();
        //return 0;
    }

    private AlertStatResponse getAllAlertStats() {
        AlertStatResponse alertStatResponse = new AlertStatResponse();
        alertStatResponse.setCount(getAllAlertCount());
        alertStatResponse.setCriticalCount(getAllCriticalCount());
        alertStatResponse.setDate(getAlertDate("blue"));
        alertStatResponse.setMajorCount(getAllMajorCount());
        alertStatResponse.setMinorCount(getAllMinorCount());
        alertStatResponse.setSection("all");
        return alertStatResponse;
    }

    private int getDatabaseAlertCount() {
        return alertRepo.countDatabaseAllAlerts();
        //return 0;
    }

    private int getDatabaseCriticalCount() {
        return alertRepo.countDatabaseCriticalAlerts();
        //return 0;
    }

    private int getDatabaseMajorCount() {
        return alertRepo.countDatabaseMajorAlerts();
        //return 0;
    }

    private int getDatabaseMinorCount() {
        return alertRepo.countDatabaseMinorAlerts();
        //return 0;
    }



//    May302020

    private List<MobileAlert> alertToMobileAlert(Iterable<Alert> alerts) {
        List<MobileAlert> mobileAlerts = new ArrayList<>();
        for(Alert alert: alerts) {
            MobileAlert mobileAlert = MyUtilities.convertAlertToMobileAlert(alert);
            mobileAlerts.add(mobileAlert);
        }
        return mobileAlerts;
    }
}
