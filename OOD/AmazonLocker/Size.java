package com.laioffer.OOD.AmazonLocker;

public enum Size {
    Small(0),
    Medium(1),
    Large(2);

    private int numVal;

    Size(int numVal) {
        this.numVal = numVal;
    }

    public int numVal() {
        return numVal;
    }
}
