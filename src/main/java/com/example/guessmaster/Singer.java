package com.example.guessmaster;

public class Singer extends Person{

    //instance variables
    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    public String getAlbum() {
        return debutAlbum;
    }

    public void setAlbum(String albumIn) {
        if(albumIn != null) {
            debutAlbum = albumIn;
        }
    }

    public String entityType() {
        return "this entity is a singer!";
    }

    public void setDate(Date dateIn) {
        debutAlbumReleaseDate = dateIn;
    }

    public Date getDate() {
        return debutAlbumReleaseDate;
    }

    //Default constructor
    public Singer(){
        super();
        debutAlbum = "";
        debutAlbumReleaseDate = new Date();
    }

    public Singer(String nameIn, Date bornIn, double difficultyIn, String genderIn, String albumIn, Date albumDateIn) {
        super(nameIn, bornIn, difficultyIn, genderIn);
        debutAlbum = albumIn;
        debutAlbumReleaseDate = albumDateIn;
    }

    //copy constructor
    public Singer(Singer singerIn) {
        super(singerIn);
        debutAlbum = singerIn.getAlbum();
        debutAlbumReleaseDate = singerIn.getDate();
    }

    public String toString() {
        return super.toString() + "\nDebut Album: " + debutAlbum + "\n" + "Release Date: " + debutAlbumReleaseDate.toString();
    }

    //clone method that implements the copy constructor
    public Singer clone() {
        Singer singerClone = new Singer(this);
        return singerClone;
    }
}
