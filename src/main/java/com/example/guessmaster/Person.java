package com.example.guessmaster;

//Erjon Musa 20296816
//Wanted to put my friends in the game so I added a Student subclass of Entity
public class Person extends Entity {

    private String gender;


    public String entityType() {
        return "This entity is a Person!";
    }

    //clone method from the abstract method in Entity
    public Entity clone() {
        Person newPerson = new Person(this);
        return newPerson;
    }

    public void setGender(String genderIn) {
        if(genderIn != null) {
            gender = genderIn;
        }
    }
    public String getGender(){
        return gender;
    }

    public Person(){
        super();
        gender = "";
    }


    public Person(String nameIn, Date bornIn, double difficultyIn, String genderIn){
        super(nameIn, bornIn, difficultyIn);
        setGender(genderIn);
    }

    //Copy Constructor
    public Person(Person personIn) {
        super(personIn);
        setGender(personIn.getGender());
    }

    public String toString() {
        return super.closingMessage() + super.toString() + "Gender: " + this.getGender();
    }
}

