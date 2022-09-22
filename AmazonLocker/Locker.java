package com.laioffer.AmazonLocker;

public class Locker {
    private final Size lockerSize;
    private final int lockerNum;
    private Package packageInLocker;

    Locker(Size size, int num) {
        lockerSize = size;
        lockerNum = num;
        // lockerId = UUID.randomUUID().toString();
    }
    public Size getSize() { return lockerSize; }
    public int getNum() { return lockerNum; }
    public void assignPackage (Package p) { packageInLocker = p;}

    public Package emptyLocker() {
        Package p = packageInLocker;
        packageInLocker = null;
        return p;
    }
}
