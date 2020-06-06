package main.facts;

public class Bundle {
    private int finishedRules;
    private Long vehicleID;
    public Bundle() {
    }

    public Bundle(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getFinishedRules() {
        return finishedRules;
    }

    public void setFinishedRules(int finishedRules) {
        this.finishedRules = finishedRules;
    }

    public void increaseFinishedRules(){
        this.finishedRules = this.finishedRules +1;
    }

    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }
}
