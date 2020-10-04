package in.bsnl.mobile.app.ws.ui.controller;


import in.bsnl.mobile.app.ws.security.AuthorizationFilter;
import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.AlertDao;
import in.bsnl.mobile.app.ws.shared.dto.EventDto;
import in.bsnl.mobile.app.ws.ui.model.response.AlertResponse;
import in.bsnl.mobile.app.ws.ui.model.response.AlertStatResponse;
import in.bsnl.mobile.app.ws.ui.model.response.EventResponse;
import in.bsnl.mobile.app.ws.ui.model.response.TrackerResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("alert")
public class ServerController {
    Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());
    static private final String TAG = "ServerController : ";


    @Autowired
    MobileAlertService mobileAlertService;

    @GetMapping(path = "/test")
    public String getFilesystems() {
        logger.debug(TAG+"Get /test is called");
        return "Get test was called";
    }

//    @GetMapping(path = "/list")
//    public List<MobileAlertResponse> getMobileAlerts() {
//        List<MobileAlertResponse> returned = new ArrayList<>();
//        List<MobileAlertDao> mobileAlertDaos = mobileAlertService.getMobileAlerts();
//        for (MobileAlertDao mobileAlertDao : mobileAlertDaos) {
//            MobileAlertResponse mobileAlertResponse = new MobileAlertResponse();
//            BeanUtils.copyProperties(mobileAlertDao, mobileAlertResponse);
//            returned.add(mobileAlertResponse);
//        }
//
//        return returned;
//    }

    @GetMapping(path = "/list")
    public List<AlertResponse> getAlerts(){
        logger.debug(TAG+"Get /list is called");
        List<AlertResponse> returned = new ArrayList<>();
        List<AlertDao> alertDaos = mobileAlertService.getAlerts();
        for(AlertDao alertDao : alertDaos) {
            AlertResponse alertResponse = new AlertResponse();
            BeanUtils.copyProperties(alertDao,alertResponse);
            returned.add(alertResponse);
        }
        return returned;
    }

    @GetMapping(path = "/stat")
    public List<AlertStatResponse> getAlertStats() {
        logger.debug(TAG+"Get /stat is called");
        return mobileAlertService.getAlertStats();
    }

    @GetMapping(path = "/trackers")
    public List<TrackerResponse> getTrackers() {
        logger.debug(TAG+"Get /trackers is called");
        return mobileAlertService.getTrackers();
    }

    @GetMapping(path = "/events/{alertLevel}")
    public List<EventResponse> getEvents(@PathVariable("alertLevel") String alertLevel) {
        List<EventResponse> returned  = new ArrayList<>();
        List<EventDto> eventDtos = mobileAlertService.getEvents(alertLevel);
        for(EventDto eventDto: eventDtos) {
            EventResponse eventResponse = new EventResponse();
            BeanUtils.copyProperties(eventDto, eventResponse);
            returned.add(eventResponse);
        }
        return  returned;
    }

    /* Mar302020
    @GetMapping(path = "/server/critical/count")
    private int getServerCriticalCount() {
        return mobileAlertService.getServerCriticalCount();
    }

    @GetMapping(path = "/server/major/count")
    private int getServerMajorCount() {
        return mobileAlertService.getServerMajorCount();
    }

    @GetMapping(path = "/server/minor/count")
    private int getServerMinorCount() {
        return mobileAlertService.getServerMinorCount();
    }
    */

//    @GetMapping(path = "/stat/server")
//    private AlertStatResponse getServerAlertStats() {
//        return mobileAlertService.getServerAlertStats();
//    }

    //    May302020
//    @GetMapping(path = "/stat/all")
//    public AlertStatResponse getAllAlertStats() {
//        return mobileAlertService.getAllAlertStats();
//    }

//    @GetMapping(path  = "/stat/database")
//    public AlertStatResponse getDatabaseAlertStats() {
//        return mobileAlertService.getDatabaseAlertStats();
//    }
//    May302020


    /*
    @GetMapping(path = "/alert/stat")
    public ServerAlertStatResponse getServerAlertStat() {
        long date = mobileAlertService.getDate();
        int coount = mobileAlertService.getCount();
        return new ServerAlertStatResponse(date,coount);
    }

     */
}
