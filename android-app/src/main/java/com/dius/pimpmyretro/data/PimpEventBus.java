package com.dius.pimpmyretro.data;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class PimpEventBus {
	
    private static Bus sInstance = new Bus(ThreadEnforcer.ANY);;

    private PimpEventBus() {
    }

    public static void post(Object event) {
        synchronized (event) {
            sInstance.post(event);
        }
    }

    public static void register(Object subscriber) {
        sInstance.register(subscriber);
    }

    public static void unregister(Object subscriber) {
        sInstance.unregister(subscriber);
    }
	
}