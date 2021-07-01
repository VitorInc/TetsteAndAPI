package com.example.APIrestID.excepts;

public class DidntRegister extends RuntimeException {
    public DidntRegister(){
        super("Sorry, your request wasen't register please try again");
    }
}
