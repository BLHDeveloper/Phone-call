package Controller;

public class CallLogItem {
    private String number;
    private String name;
    private String duration;
    private long callDate;

    public CallLogItem(String number, String name, String duration,long callDate) {
        this.number = number;
        this.name = name;
        this.duration = duration;
        this.callDate = callDate;

    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDuration() {
        return duration;
    }

    public long getCallDate() {
        return callDate;
    }

    public void setCallDate(long callDate) {
        this.callDate = callDate;
    }
}
