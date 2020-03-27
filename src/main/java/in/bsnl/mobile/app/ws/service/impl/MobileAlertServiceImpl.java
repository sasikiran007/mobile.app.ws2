package in.bsnl.mobile.app.ws.service.impl;

import in.bsnl.mobile.app.ws.io.entity.Alert;
import in.bsnl.mobile.app.ws.io.entity.MobileAlert;
import in.bsnl.mobile.app.ws.io.repository.AlertRepo;
import in.bsnl.mobile.app.ws.io.repository.MobileAlertRepo;
import in.bsnl.mobile.app.ws.service.MobileAlertService;
import in.bsnl.mobile.app.ws.shared.dto.MobileAlertDao;
import in.bsnl.mobile.app.ws.shared.utils.MyUtilities;
import in.bsnl.mobile.app.ws.shared.utils.RandomAlertId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileAlertServiceImpl implements MobileAlertService {

    @Autowired
    MobileAlertRepo mobileAlertRepo;
    @Autowired
    AlertRepo alertRepo;
    @Override
    public List<MobileAlertDao> getMobileAlerts() {
        boolean isMAEmpty = true;
        boolean isAIDEmpty = false;
        Iterable<MobileAlert> mobileAlerts =  mobileAlertRepo.findAll( );
        if( ((ArrayList) mobileAlerts).isEmpty()) { isMAEmpty = true;}else  { isMAEmpty = false;}

        Iterable<Alert> alerts = alertRepo.findAll();
        for (Alert alert : alerts) {
            if(alert.getAlertId() == null || alert.getAlertId().isEmpty()) {
                isAIDEmpty = true;
                alert.setAlertId(RandomAlertId.generate(20));
            }
        }
        if(!isAIDEmpty) { //no fresh data
            if(!isMAEmpty) { // also MobileAlert is not empty ==> nothing to do;
            }
            else  { // No mobile alets insert mobile alerts
                mobileAlerts = alertToMobilealert(alerts);
                mobileAlertRepo.saveAll(mobileAlerts);
            }
        }
        else {
            alertRepo.deleteAll();
            alertRepo.saveAll(alerts);
            mobileAlertRepo.deleteAll();
            mobileAlerts = alertToMobilealert(alerts);
            mobileAlertRepo.saveAll(mobileAlerts);
        }
        List<MobileAlertDao> returned = new ArrayList<>();
        for(MobileAlert mobileAlert : mobileAlerts ) {
            MobileAlertDao mobileAlertDao = new MobileAlertDao();
            BeanUtils.copyProperties(mobileAlert, mobileAlertDao);
            returned.add(mobileAlertDao);
        }
        return returned;
    }

    private List<MobileAlert> alertToMobilealert(Iterable<Alert> alerts) {
        List<MobileAlert> mobileAlerts = new ArrayList<>();
        for(Alert alert: alerts) {
            MobileAlert mobileAlert = MyUtilities.convertAlertToMobileAlert(alert);
            mobileAlerts.add(mobileAlert);
        }
        return mobileAlerts;
    }
}
