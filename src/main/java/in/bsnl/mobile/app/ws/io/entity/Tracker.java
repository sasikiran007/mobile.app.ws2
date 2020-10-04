package in.bsnl.mobile.app.ws.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "alert_tracker")
public class Tracker {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    private String scriptName;
    private String trackerNumber;
    private String trackerNumberOld;
    private int period;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getTrackerNumber() {
        return trackerNumber;
    }

    public void setTrackerNumber(String trackerNumber) {
        this.trackerNumber = trackerNumber;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getTrackerNumberOld() {
        return trackerNumberOld;
    }

    public void setTrackerNumberOld(String trackerNumberOld) {
        this.trackerNumberOld = trackerNumberOld;
    }
}
