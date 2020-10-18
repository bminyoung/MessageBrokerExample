package com.daou.messageBroker;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    void subscribe(Observer observer){
        observers.add(observer);
    }

    void unSubscribe(Observer observer){
        observers.remove(observer);
    }

    /*
    * function as broadcasting
    * */
    void notifyObservers(String topic, String msg){
        observers.stream().filter(o -> o.isContainTopic(topic)).forEach(o -> o.send(msg));
    }

    public void close(){
        observers.clear();
    }
}
