package lk.ijse.projectggmountain.entity;

public class Event {
    private String eventId;
    private String eventName;
    private String date;
    private String time;
    private String eventType;
    private String empId;

    public Event(String eventId, String eventName, String date, String time, String eventType, String empId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.eventType = eventType;
        this.empId = empId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", eventType='" + eventType + '\'' +
                ", empId='" + empId + '\'' +
                '}';
    }
}
