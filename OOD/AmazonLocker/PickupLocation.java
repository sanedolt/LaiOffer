package com.laioffer.OOD.AmazonLocker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class PickupLocation {
    Map<Size, Queue<Locker>> lockers; // <key= lockerSize, val = availableLocker>
    Map<String, Locker> packageToLocker; // <key = packageId, val = Locker>

    // constructor: to handle creating lockers for a pickup location
    // <key = lockerSize, val = count>
    public PickupLocation (Map<Size, Integer> lockerSizes) {
        lockers = new HashMap<>();
        packageToLocker = new HashMap<>();
        // initialize lockers
        int counter=0;
        for (Size size : lockerSizes.keySet()) {
            Queue<Locker> q = new ArrayDeque<>();
            int count = lockerSizes.get(size);
            for (int i = 0; i < count; i++) {
                q.offer(new Locker(size,++counter));
            }
            lockers.put(size, q);
        }
    }
    // find locker for a package and return the locker
    // if no available locker, return null
    public Locker dropoffPackage(Package p) {
        for (Size size : Size.values()) { // try from smallest size
            if (size.ordinal() < p.getSize().numVal()) continue; // pass locker < p.size
            Locker locker = findLocker(p, size);
            if (locker != null) return locker; // find locker
        }
        return null;
    }
    // return package with the id
    public Package pickupPackage(String packageId) {
        Locker locker = packageToLocker.remove(packageId); // get and remove
        if (locker == null) return null; // cannot find package
        Package p = locker.emptyLocker(); // get package from locker
        lockers.get(locker.getSize()).offer(locker); // put locker back to queue
        return p;
    }

    private Locker findLocker(Package p, Size size) {
        if (lockers.get(size).isEmpty()) return null; // no available locker for the size
        Locker locker = lockers.get(size).poll(); // get the locker
        locker.assignPackage(p); // assign locker to package
        packageToLocker.put(p.getId(), locker); // record <package, locker> in packageMap
        return locker;
    }
}
