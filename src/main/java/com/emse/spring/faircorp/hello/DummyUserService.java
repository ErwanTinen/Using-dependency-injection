package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService{

    public String[] NameList = {"Elodie","Charles"};

    @Autowired
    public GreetingService greetingService;

    public void greetAll() {

        for (String name : NameList){
            greetingService.greet(name);
        }

    }
}
