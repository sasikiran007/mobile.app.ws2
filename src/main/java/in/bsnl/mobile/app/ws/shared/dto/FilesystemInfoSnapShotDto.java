package in.bsnl.mobile.app.ws.shared.dto;

import java.sql.Time;
import java.util.Date;

public class FilesystemInfoSnapShotDto {
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

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    public double getPctUsed() {
        return pctUsed;
    }

    public void setPctUsed(double pctUsed) {
        this.pctUsed = pctUsed;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getFsStatus() {
        return fsStatus;
    }

    public void setFsStatus(String fsStatus) {
        this.fsStatus = fsStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSshStatus() {
        return sshStatus;
    }

    public void setSshStatus(String sshStatus) {
        this.sshStatus = sshStatus;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }
}
