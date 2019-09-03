package com.toborehumble.takeanote;

import java.util.ArrayList;

public class NotePublisher implements Publisher {
    private ArrayList<Listener> listeners;

    @Override
    public void subscribeListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unSubscribeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(Listener listener) {
        for (Listener listener1 : listeners){
            listener1.update();
        }
    }
}
