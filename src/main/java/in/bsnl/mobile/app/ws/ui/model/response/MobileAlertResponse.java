package in.bsnl.mobile.app.ws.ui.model.response;

import java.util.Date;

public class MobileAlertResponse {
        private long date;
        private String alertId;
        private String alertSection;
        private String alertLevel;
        private String entity;
        private String osName;
        private String appName;
        private String message;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlertSection() {
        return alertSection;
    }

    public void setAlertSection(String alertSection) {
        this.alertSection = alertSection;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
