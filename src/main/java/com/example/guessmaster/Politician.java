package com.example.guessmaster;

//Erjon Musa 20296816

public class Politician extends Person{

    private String party;

    public String getParty(){
        return party;
    }

    //overriding entityType() declared in Person Class
    public String entityType() {
        return "this entity is a Politician!";
    }


    public void setParty(String partyIn) {
        if(partyIn != null) {
            party = partyIn;
        }
    }

    //default constructor
    public Politician(){
        super();
        party = "";
    }

    public Politician(String nameIn, Date bornIn, double difficultyIn, String genderIn, String partyIn) {
        super(nameIn, bornIn, difficultyIn, genderIn);
        if(partyIn != null) {
            party = partyIn;
        }
    }

    //clone method that implements the copy constructor
    public Politician clone() {
        Politician newPolitician = new Politician(this);
        return newPolitician;

    }

    //copy constructor
    public Politician(Politician poliIn) {
        super(poliIn);
        this.party = poliIn.getParty();
    }

    public String toString() {
        return super.toString() + "\n" + "Party: " + party + "\n";
    }
}
