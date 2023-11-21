package com.example.guessmaster;

//Erjon Musa 20296816

public  class Country extends Entity{

    private String capital;

    public String getCapital() {
        return capital;
    }

    //overriding entityType from entity class
    public String entityType() {
        return "This entity is a country!";
    }

    public void setCapital(String capitalIn) {
        if(capitalIn != null) {
            capital = capitalIn;
        }
    }
    //Default Constructor
    public Country(){
        super();
        capital = "";
    }

    public Country(String nameIn, Date bornIn, double difficultyIn, String capitalIn){
        super(nameIn, bornIn, difficultyIn);
        if(capitalIn != null) {
            capital = capitalIn;
        }
    }

    //Copy Constructor
    public Country(Country countryIn) {
        super(countryIn);
        if (countryIn.getCapital() != null) {
            setCapital(countryIn.getCapital());

        }
    }

    public String toString() {
        return super.closingMessage() + super.toString() + "Capital: " + capital;
    }

    //clone method that implements the copy constructor
    public Entity clone() {
        Country newCountry = new Country(this);
        return newCountry;
    }
}


