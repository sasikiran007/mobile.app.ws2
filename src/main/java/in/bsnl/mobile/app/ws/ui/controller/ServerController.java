package in.bsnl.mobile.app.ws.ui.controller;


import in.bsnl.mobile.app.ws.io.entity.Alert;
import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.ui.model.response.AlertStatResponse;
import in.bsnl.mobile.app.ws.ui.model.response.MobileAlertResponse;
import in.bsnl.mobile.app.ws.ui.model.response.ServerAlertStatResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("alert")
public class ServerController {

    @Autowired
    MobileAlertService mobileAlertService;

    @GetMapping(path = "/test")
    public String getFilesystems() {
        return "Get test was called";
    }

    @GetMapping(path = "/list")
    public List<MobileAlertResponse> getMobileAlerts() {
        List<MobileAlertResponse> returned = new ArrayList<>();
        List<MobileAlertDao> mobileAlertDaos = mobileAlertService.getMobileAlerts();
        for (MobileAlertDao mobileAlertDao : mobileAlertDaos) {
            MobileAlertResponse mobileAlertResponse = new MobileAlertResponse();
            BeanUtils.copyProperties(mobileAlertDao, mobileAlertResponse);
            returned.add(mobileAlertResponse);
        }

        return returned;
    }

    @GetMapping(path = "/stat")
    public List<AlertStatResponse> getAlertStats() {
        return mobileAlertService.getAlertStats();
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
