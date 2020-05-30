package in.bsnl.mobile.app.ws.ui.controller;


import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
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
@RequestMapping("server")
public class ServerController {

   @Autowired
    MobileAlertService mobileAlertService;

    @GetMapping(path = "/filesystem")
    public String getFilesystems() {
        return "Get filesystemAlerts was called";
    }

    @GetMapping(path = "/alert/all")
    public List<MobileAlertResponse> getMobileAlerts() {
        List<MobileAlertResponse> returned = new ArrayList<>();
        List<MobileAlertDao> mobileAlertDaos = mobileAlertService.getMobileAlerts();
        for(MobileAlertDao mobileAlertDao : mobileAlertDaos) {
            MobileAlertResponse mobileAlertResponse = new MobileAlertResponse();
            BeanUtils.copyProperties(mobileAlertDao, mobileAlertResponse);
            returned.add(mobileAlertResponse);
        }

        return  returned;
    }

    @GetMapping(path = "/alert/critical/count")
    public int getServerCriticalCount(){
        return mobileAlertService.getServerCriticalCount();
    }
    @GetMapping(path = "/alert/major/count")
    public int getServerMajorCount(){
        return mobileAlertService.getServerMajorCount();
    }
    @GetMapping(path = "/alert/minor/count")
    public int getServerMinorCount(){
        return mobileAlertService.getServerMinorCount();
    }

    @GetMapping(path = "/alert/stat")
    public ServerAlertStatResponse getServerAlertStats() {
        return mobileAlertService.getServerAlertStats();
    }
    /*
    @GetMapping(path = "/alert/stat")
    public ServerAlertStatResponse getServerAlertStat() {
        long date = mobileAlertService.getDate();
        int coount = mobileAlertService.getCount();
        return new ServerAlertStatResponse(date,coount);
    }

     */
 }
