package in.bsnl.mobile.app.ws.service;

import in.bsnl.mobile.app.ws.io.entity.MobileAlert;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;

import java.util.List;

public interface MobileAlertService {
    public List<MobileAlertDao> getMobileAlerts();
}
