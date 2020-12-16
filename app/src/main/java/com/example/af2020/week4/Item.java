package com.example.af2020.week4;

class Item {
    
    private final String firstName;
    private final String lastName;
    
    Item(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
