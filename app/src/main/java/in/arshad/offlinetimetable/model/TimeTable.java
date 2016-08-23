package in.arshad.offlinetimetable.model;

/**
 * Created by root on 18/8/16.
 */
public class TimeTable {

    int id;
    String trainNo;
    String trainName;
    String islNo;
    String stationCode;
    String stationName;
    String arrivalTime;
    String departureTime;
    String distance;
    String sourceStnCode;
    String sourceStnName;
    String destStnCode;
    String destStnName;

    public TimeTable(){}

    public TimeTable(int id, String trainNo, String trainName, String islNo, String stationCode, String stationName,
                     String arrivalTime, String departureTime, String distance, String sourceStnCode, String sourceStnName,
                     String destStnCode, String destStnName) {
        this.id = id;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.islNo = islNo;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.distance = distance;
        this.sourceStnCode = sourceStnCode;
        this.sourceStnName = sourceStnName;
        this.destStnCode = destStnCode;
        this.destStnName = destStnName;
    }

    public int getId() {
        return id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDestStnCode() {
        return destStnCode;
    }

    public String getDestStnName() {
        return destStnName;
    }

    public String getDistance() {
        return distance;
    }

    public String getIslNo() {
        return islNo;
    }

    public String getSourceStnCode() {
        return sourceStnCode;
    }

    public String getSourceStnName() {
        return sourceStnName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setDestStnCode(String destStnCode) {
        this.destStnCode = destStnCode;
    }

    public void setDestStnName(String destStnName) {
        this.destStnName = destStnName;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIslNo(String islNo) {
        this.islNo = islNo;
    }

    public void setSourceStnCode(String sourceStnCode) {
        this.sourceStnCode = sourceStnCode;
    }

    public void setSourceStnName(String sourceStnName) {
        this.sourceStnName = sourceStnName;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

}
