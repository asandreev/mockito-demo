package com.example.mockitodemo.services;

public class BrickFactory {

    private static volatile BrickFactory factoryInstance;

    public static void setFactoryInstance(BrickFactory testFactory) {
        factoryInstance = testFactory;
    }

    public static BrickFactory getFactory() {
        if (factoryInstance == null) {
            synchronized (BrickFactory.class) {
                if (factoryInstance == null) {
                    factoryInstance = new BrickFactory();
                }
            }
        }
        return factoryInstance;
    }

    public Brick getBrick() {
        return new Brick();
    }

}
