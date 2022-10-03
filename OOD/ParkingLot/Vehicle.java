package com.laioffer.OOD.ParkingLot;

public abstract class Vehicle {
    public abstract VehicleSize getSize();
}

class Car extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Compact;
    }
}

class Truck extends Vehicle {
    @Override
    public VehicleSize getSize() {
        return VehicleSize.Large;
    }
}
