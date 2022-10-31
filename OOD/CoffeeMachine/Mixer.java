package com.laioffer.OOD.CoffeeMachine;

public class Mixer {
    public IStrategy strategy;

    public Mixer() { }

    public void SetStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public Ready Process() throws Exception {
        if(this.strategy == null)
            throw new Exception("Please choose strategy");

        return this.strategy.Process();
    }
}
