package com.anthonyra95.covid.contollers;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException(Long id) {
        super("Could not find State " + id);
    }
}
