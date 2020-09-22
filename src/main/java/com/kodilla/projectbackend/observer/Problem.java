package com.kodilla.projectbackend.observer;


import java.util.HashSet;
import java.util.Set;


public class Problem implements Observable {

    private final Set<Observer> observers;
    private String message;
    private final String name;

    public Problem(String name, Observer observer) {
        observers = new HashSet<>();
        attach(observer);
        this.name = name;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observers){
            observer.update(this);
        }
    }

    public void addMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return name + " " + message;
    }
}
