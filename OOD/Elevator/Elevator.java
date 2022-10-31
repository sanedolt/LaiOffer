package com.laioffer.OOD.Elevator;

import java.util.Collections;
import java.util.PriorityQueue;

//public class Elevator {
//    int curFloor; // current floor
//    Status state;
//    private PriorityQueue<Request> upQueue;
//    private PriorityQueue<Request> downQueue;
//
//    public Elevator() {
//        curFloor = 1;            // Initialize: standing at first floor
//        curDir = Status.IDLE;  // status: idle
//        upQueue = new PriorityQueue<>();
//        downQueue = new PriorityQueue<>(Collections.reverseOrder());
//        // descending by destFloor
//    }
//
//    public void run() {
//        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
//            processRequest();
//        }
//        // finish all requests
//        state = Status.IDLE;
//    }
//
//    public void upRequest(Request req) {
//        // if request from outside, there are two requests:
//        // step 1 to pick up the requester at his curr floor
//        if (req.loc == Location.OUTSIDE) {
//            upQueue.offer(new Request(req.curFloor, req.curFloor, Status.UP, Location.OUTSIDE));
//        }
//        //step2: go to dest floor
//        upQueue.offer(req);
//    }
//
//    public void downRequest(Request req) {
//        // if request from outside, there are two requests:
//        // step 1 to pick up the requester at his curr floor
//        if (req.loc == Location.OUTSIDE) {
//            downQueue.offer(new Request(req.curFloor, req.curFloor, Status.DOWN, Location.OUTSIDE));
//        }
//        //step2: go to dest floor
//        downQueue.offer(req);
//    }
//
//    private void processRequest() {
//        // if idle, process up request first, then down request
//        if (state == Status.UP || state == Status.IDLE) {
//            processUpRequest();
//            processDownRequest();
//        } else {
//            processDownRequest();
//            processUpRequest();
//        }
//    }
//
//    private void processUpRequest() {
//        while (!upQueue.isEmpty()) {
//            Request req = upQueue.poll(); // process req from lower to higher floor
//            curFloor = req.destFloor; // get to destFloor
//        }
//        state = downQueue.isEmpty() ? Status.IDLE : Status.DOWN;
//    }
//
//    private void processDownRequest() {
//        while (!downQueue.isEmpty()) {
//            Request req = downQueue.poll(); // process req from higher to lower floor
//            curFloor = req.destFloor; // get to destFloor
//        }
//        state = upQueue.isEmpty() ? Status.IDLE : Status.UP;
//    }
//}


public class Elevator {
    int curFloor; // current floor
    Status curDir;
    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;
    private PriorityQueue<Integer> nextUpQueue;
    private PriorityQueue<Integer> nextDownQueue;

    public Elevator() {
        curFloor = 1;            // Initialize: standing at first floor
        curDir = Status.IDLE;  // status: idle
        upQueue = new PriorityQueue<>();
        downQueue = new PriorityQueue<>(Collections.reverseOrder());
        nextUpQueue = new PriorityQueue<>();
        nextDownQueue = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void run() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            processRequest();
        }
        // finish all requests
        curDir = Status.IDLE;
    }
    // process request in the same direction first
    private void processRequest() {
        if (curDir == Status.UP || curDir == Status.IDLE) {
            process(upQueue);
            swap(downQueue, nextDownQueue); // downQ is empty 换地址，不能直接赋值
            curDir = Status.DOWN; // set dir to down
            process(downQueue); // process down req
            swap(upQueue, nextUpQueue);
            curDir = Status.UP;
        } else { //
            process(downQueue); // process down req
            swap(upQueue, nextUpQueue);
            curDir = Status.UP;
            process(upQueue);
            swap(downQueue, nextDownQueue);
            curDir = Status.DOWN;
        }
    }
    private void process(PriorityQueue<Integer> q) { // has issue
        while (!q.isEmpty()) {
            curFloor = q.poll(); // go to reqFloor
            // take request
        }
    }
    private void swap(PriorityQueue<Integer> q1, PriorityQueue<Integer> q2) {
        PriorityQueue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
    }
    // request from outside, reqFloor is destFloor
    public void outsideRequest(int reqFloor, Status reqDir) {
        // case0: if curDir = idle, enqueue to curQueue
        // case1: requestDir & curDir opposite, enqueue to corresponding dir next queue
        // case2: requestDir & curDir same:
        //   case2.1 if reqFloor in the moving direction, enqueue to curQueue
        //   case2.2 enqueue to corresponding dir next queue
        // only one queue is current processed, if curDir is up, only Queue will
        // be offered, downQueue is empty
        if (curDir == Status.IDLE || curDir == reqDir) { // case0&2
            if (reqDir == Status.UP && (curDir == Status.IDLE || reqFloor > curFloor)) {
                upQueue.offer(reqFloor);
            } else if (reqDir == Status.DOWN && (curDir == Status.IDLE || reqFloor < curFloor)) {
                downQueue.offer(reqFloor);
            } else if (reqDir == Status.UP) { // case2.2: UP
                nextUpQueue.offer(reqFloor);
            } else if (reqDir == Status.DOWN) { // case2.2: DOWN
                nextDownQueue.offer(reqFloor);
            }
        } else { // case 3: opposite dir
            if (reqDir == Status.UP) { // UP
                nextUpQueue.offer(reqFloor);
            } else if (reqDir == Status.DOWN) { // DOWN
                nextDownQueue.offer(reqFloor);
            }
        }
    }

    public void insideRequest(int reqFloor) {
        if (curFloor < reqFloor) { // reqDir is UP
            if (curDir == Status.UP) upQueue.offer(reqFloor); // same dir
            else if (curDir == Status.DOWN) nextUpQueue.offer(reqFloor);
        } else { // curFloor > reqFloor, reqDir is Down
            if (curDir == Status.UP) nextDownQueue.offer(reqFloor);
            else if (curDir == Status.DOWN) downQueue.offer(reqFloor);
        }
    }

}

