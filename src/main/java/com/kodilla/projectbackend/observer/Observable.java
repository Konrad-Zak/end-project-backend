package com.kodilla.projectbackend.observer;

public interface Observable {
    void attach(Observer observer);
    void notifyObservers();
}
