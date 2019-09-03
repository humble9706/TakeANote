package com.toborehumble.takeanote;

public interface Publisher {
    void subscribeListener(Listener listener);
    void unSubscribeListener(Listener listener);
    void notifyListeners(Listener listener);
}
