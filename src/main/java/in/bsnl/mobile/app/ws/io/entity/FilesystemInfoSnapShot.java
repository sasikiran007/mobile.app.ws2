package in.bsnl.mobile.app.ws.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity(name = "current_cdr_server_filesystem")
public class FilesystemInfoSnapShot {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    private Date date;
    private Time time;
    private String hostname;
    private String ipaddress;
    private String fsName;
    private double pctUsed;
    private String osName;
    private String serverType;
    private String fsStatus;
    private String remarks;
    private String sshStatus;
    private String pingStatus;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public String getFsName() {
        return fsName;
    }

    public double getPctUsed() {
        return pctUsed;
    }

    public String getOsName() {
        return osName;
    }

    public String getServerType() {
        return serverType;
    }

    public String getFsStatus() {
        return fsStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getSshStatus() {
        return sshStatus;
    }

    public String getPingStatus() {
        return pingStatus;
    }
}
