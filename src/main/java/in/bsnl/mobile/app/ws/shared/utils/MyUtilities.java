package in.bsnl.mobile.app.ws.shared.utils;

import in.bsnl.mobile.app.ws.io.entity.Alert;
import in.bsnl.mobile.app.ws.io.entity.MobileAlert;

import java.util.AbstractList;
import java.util.List;

public abstract class MyUtilities {
    public static MobileAlert convertAlertToMobileAlert(Alert alert) {
        MobileAlert mobileAlert = new MobileAlert();
        mobileAlert.setAlertId(alert.getAlertId());
        mobileAlert.setAlertLevel(alert.getAlertLevel());
        mobileAlert.setAlertSection(alert.getAlertSection());
        mobileAlert.setAppName(alert.getAppName());
        mobileAlert.setDate(alert.getDate());
        mobileAlert.setOsName(alert.getOsName());
        mobileAlert.setEntity(alert.getEntity());
        String message = "";
        if(mobileAlert.getEntity().equalsIgnoreCase("filesystem")) {
            switch (alert.getProperty()) {
                case "utilization" :
                    message = alert.getEntityId() + " is " + alert.getPropertyValue() + "% utilized";
                    break;
                case "rwStatus" :
                    message = alert.getEntityId() + " is  read only";
                    break;
                case  "mountStatus" :
                    message = alert.getEntityId() + " is  not mounted";
                    break;
                default:message = alert.getEntityId() + " " + alert.getProperty() + " = " + alert.getPropertyValue();

            }

        }
        if(mobileAlert.getEntity().equalsIgnoreCase("serverHealth")) {
            switch (alert.getProperty())
            {
                case "uptime" :
                    message = alert.getEntityId() + " uptime is " + alert.getPropertyValue() + " day(s)";
                    break;
                case "pingStatus" :
                    message = alert.getEntityId() + " is not reachable.";
                    break;
                case "sshStatus" :
                    message = alert.getEntityId() + " : ssh failed";
                    break;
                default :
                    message = alert.getEntityId() + " " + alert.getProperty() + " : " + alert.getPropertyValue();
            }
        }
        mobileAlert.setMessage(message);
        return mobileAlert;
    }
}
