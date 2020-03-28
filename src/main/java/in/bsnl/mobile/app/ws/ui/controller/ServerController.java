package in.bsnl.mobile.app.ws.ui.controller;


import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.ui.model.response.MobileAlertResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("servers")
public class ServerController {

   @Autowired
    MobileAlertService mobileAlertService;

    @GetMapping(path = "/alerts/filesystems")
    public String getFilesystems() {
        return "Get filesystemAlerts was called";
    }

    @GetMapping(path = "/alertts")
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
 }
