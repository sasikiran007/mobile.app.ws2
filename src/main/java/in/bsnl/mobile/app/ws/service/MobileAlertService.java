package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.ui.model.response.AlertStatResponse;
import in.bsnl.mobile.app.ws.ui.model.response.ServerAlertStatResponse;

import java.util.List;

public interface MobileAlertService {
    List<MobileAlertDao> getMobileAlerts();

    long getAlertDate();
//    public int getServerAlertCount();

//    int getServerCriticalCount();
//
//    int getServerMajorCount();
//
//    int getServerMinorCount();

//    AlertStatResponse getServerAlertStats();

    /*May302020*/
//    AlertStatResponse getAllAlertStats();

//    AlertStatResponse getDatabaseAlertStats();

    /*May302020*/

    /*May312020*/
    List<AlertStatResponse> getAlertStats();
}
