package in.bsnl.mobile.app.ws.shared.dto;

public class FilesystemLimitsDto {
    private int id;
    private String hostname;
    private String appName;
    private String fsName;
    private String alertSection;
    private String alertLevel;
    private int criticalLimit;
    private int majorLimit;
    private int minorLimit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
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

    public int getCriticalLimit() {
        return criticalLimit;
    }

    public void setCriticalLimit(int criticalLimit) {
        this.criticalLimit = criticalLimit;
    }

    public int getMajorLimit() {
        return majorLimit;
    }

    public void setMajorLimit(int majorLimit) {
        this.majorLimit = majorLimit;
    }

    public int getMinorLimit() {
        return minorLimit;
    }

    public void setMinorLimit(int minorLimit) {
        this.minorLimit = minorLimit;
    }
}
