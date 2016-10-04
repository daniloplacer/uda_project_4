package com.example.daniloplacer.finalproject.backend;


import com.example.JokeProvider;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    public String getData() {
        String joke = JokeProvider.getJoke();

        return joke;
    }

}