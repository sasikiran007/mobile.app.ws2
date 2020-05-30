package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.ui.model.response.ServerAlertStatResponse;

import java.util.List;

public interface MobileAlertService {
    public List<MobileAlertDao> getMobileAlerts();

    public long getAlertDate();
    public int getServerAlertCount();

    int getServerCriticalCount();

    int getServerMajorCount();

    int getServerMinorCount();

    ServerAlertStatResponse getServerAlertStats();
}
