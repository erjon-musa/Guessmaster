package com.example.guessmaster;

//Erjon Musa 20296816

public abstract class Entity {

    private String name;
    private Date born;
    private double difficulty;

    public abstract String entityType();
    public abstract Entity clone();

    //Default constructor
    public Entity() {
        name = null;
        born = new Date();

    }

    int getAwardedTicketNumber() {
        return (int) (this.difficulty * 100);
    }

    //Constructor with parameters of name and date
    public Entity(String nameIn, Date bornIn, double difficultyIn) {
        if (nameIn != null) {
            name = nameIn;
        }
        else {
            return;
        }
        born = bornIn;

        if( difficultyIn >= 0 && difficultyIn <= 1) {
            difficulty = difficultyIn;
        }
        else {
            return;
        }
    }

    public Entity(Entity entity) {
        //creating copy constructor for privacy leak issues
        this.name = entity.name;
        this.born = new Date(entity.born);
        this.difficulty = entity.difficulty;
    }

    public String welcomeMessage() {
        return "Welcome! Lets start the game! " + entityType();
    }
    //getter for the name
    public String getName(){
        return name;
    }

    //getter for the birthday
    public Date getBorn() {
        Date newDate = new Date(born);
        return newDate;
    }

    //toString method for an Entity
    public String toString() {
        String nameString = "Name: " + name;
        String dateString = "Born on: " + born.toString();
        return nameString + "\n" + dateString + "\n";
    }


    public boolean equals(Entity entity) {
        //checking if the born dates and names are equal and returning a boolean value
        if(entity.getBorn().equals(born) && entity.name.equalsIgnoreCase(name) && entity.difficulty == this.difficulty) {
            return true;
        }
        else {
            return false;
        }
    }

    public double getDifficulty() {
        return difficulty;
    }

    public String closingMessage() {
        return "Congratulation! The detailed information about the entity you guessed is: \n";
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }


}

