package com.laioffer.AmazonLocker;

public class Package {
    private final Size packageSize;
    private final String packageId;
    Package(Size size, String id) {
        packageSize = size;
        packageId = id;
    }
    public Size getSize() { return packageSize; }
    public String getId() { return packageId; }
}
