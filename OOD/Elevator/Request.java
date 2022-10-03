package com.laioffer.OOD.Elevator;

public class Request implements Comparable<Request> {
    int curFloor;
    int destFloor;
    Status state;
    Location loc;
    public Request(int curFloor, int destFloor, Status state, Location loc) {
        this.curFloor = curFloor;
        this.destFloor = destFloor;
        this.state = state;
        this.loc = loc;
    }
    @Override
    public int compareTo(Request other) {
        return destFloor - other.destFloor;   // ascending by destFloor
    }

}
