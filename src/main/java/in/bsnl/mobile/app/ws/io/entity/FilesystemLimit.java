package in.bsnl.mobile.app.ws.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "filesystem_limits")
public class FilesystemLimit {

    @Id
    @GeneratedValue
    @Column(nullable = false)
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

    public String getHostname() {
        return hostname;
    }

    public String getAppName() {
        return appName;
    }

    public String getFsName() {
        return fsName;
    }

    public String getAlertSection() {
        return alertSection;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public int getCriticalLimit() {
        return criticalLimit;
    }

    public int getMajorLimit() {
        return majorLimit;
    }

    public int getMinorLimit() {
        return minorLimit;
    }
}
