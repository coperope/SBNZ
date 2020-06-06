package main.facts;

public class Bundle {
    private int finishedRules;

    public Bundle() {
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
}
