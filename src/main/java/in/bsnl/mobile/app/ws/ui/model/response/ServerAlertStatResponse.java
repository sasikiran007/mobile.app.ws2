package in.bsnl.mobile.app.ws.ui.model.response;

public class ServerAlertStatResponse {
    private long date;
    private int count;

    /*public ServerAlertStatResponse(long date, int count, int criticalCount, int majorCount, int minorCount) {
        this.date = date;
        this.count = count;
        this.criticalCount = criticalCount;
        this.majorCount = majorCount;
        this.minorCount = minorCount;
    }

     */

    private int criticalCount;
    private int majorCount;
    private int minorCount;





    public long getDate() {
        return date;
    }

    public int getCriticalCount() {
        return criticalCount;
    }

    public void setCriticalCount(int criticalCount) {
        this.criticalCount = criticalCount;
    }

    public int getMajorCount() {
        return majorCount;
    }

    public void setMajorCount(int majorCount) {
        this.majorCount = majorCount;
    }

    public int getMinorCount() {
        return minorCount;
    }

    public void setMinorCount(int minorCount) {
        this.minorCount = minorCount;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
