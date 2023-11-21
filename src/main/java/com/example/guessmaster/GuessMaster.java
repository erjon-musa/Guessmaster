package com.example.guessmaster;

//Erjon Musa 20296816
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class GuessMaster extends AppCompatActivity {

    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private ImageView entityImage;
    public String answer;
    private int numberOfCandidateEntities;
    private Entity[] entities;
    private int numOfTickets;
    int entityId = 0;



    Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), 0.25, "Male", "Liberal");
    Singer dion =  new Singer("Celine Dion", new Date("March", 30, 1961), 0.5, "Female", "La voix du bon Dieu", new Date("November", 6, 1981));
    Country usa = new Country("United States", new Date("July", 4, 1776), 0.1, "Washington D.C.");
    Person myCreator = new Person("Erjon Musa", new Date("August", 16, 2003), 1, "Male");




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guess_activity);

        entityName = (TextView) findViewById(R.id.entityName);
        ticketsum = (TextView) findViewById(R.id.ticket);
        guessButton = (Button) findViewById(R.id.btnguess);
        userIn = (EditText) findViewById(R.id.guessinput);
        btnclearContent = (Button) findViewById(R.id.btnclear);
        entityImage = (ImageView) findViewById(R.id.entityImage);

        //shows a random entitty on the screen
        changeEntity();
        welcomeToGame(entities[entityId]);
        btnclearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeEntity();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playGame(entities[entityId]);
            }
        });
    }

    private void changeEntity() {
        //provides functionality for the change entity button
        userIn.getText().clear();
        entityId = genRandomEntityInd();

        Entity entity = entities[entityId];
        entityName.setText(entity.getName());
        ImageSetter(entity);
    }

    private void ImageSetter(Entity entity){

        if (entity.getName().equalsIgnoreCase("Justin Trudeau")) {
            entityImage.setImageResource(R.drawable.justint);
        }
        else if(entity.getName().equalsIgnoreCase("Celine Dion")) {
            entityImage.setImageResource(R.drawable.celidion);
        }
        else if(entity.getName().equalsIgnoreCase("United States")){
            entityImage.setImageResource(R.drawable.usaflag);
        }
        else if(entity.getName().equalsIgnoreCase("Erjon Musa")){
            entityImage.setImageResource(R.drawable.mycreatorpic);
        }
    }

    public void welcomeToGame(Entity entity){
        //Welcome alert
        ImageSetter(entity);
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster Game v3");
        welcomealert.setMessage((entity.welcomeMessage()));
        welcomealert.setCancelable(false); // No cancel Button
        welcomealert.setNegativeButton("Start Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting ... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        //show Dialog
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    //default constructor
    public GuessMaster() {
        //initializing that there are 0 candidates
        numberOfCandidateEntities = 0;
        //setting max length of the entity to 100
        entities = new Entity[50];
        addEntity(trudeau);
        addEntity(dion);
        addEntity(usa);
        addEntity(myCreator);
    }

    //function that adds an entity and increases the number of candidates by 1
    public void addEntity(Entity entity) {
        Entity entityClone = entity.clone();
        entities[numberOfCandidateEntities++] = entityClone;

    }

    public void playGame(Entity entity) {


        entityName.setText(entity.getName());
        //Get Input from the EdiText
        answer = userIn.getText().toString().trim();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

        //checks if inputted date is earlier than the enitities birthday
        if (date.precedes(entity.getBorn())){
            AlertDialog.Builder precedesAlert = new AlertDialog.Builder(GuessMaster.this);
            //dialog box shows that the date is wrong
            precedesAlert.setTitle("Incorrect");
            precedesAlert.setMessage("Try a date later than " + date.toString());
            precedesAlert.setCancelable(false);
            //will countinue the game when "Ok" is clicked
            precedesAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                userIn.getText().clear();
                }
            });
            //shows alert
            precedesAlert.show();
        }

        //checks if date is is later than the entities date
        else if (entity.getBorn().precedes(date)){
            AlertDialog.Builder laterAlert = new AlertDialog.Builder(GuessMaster.this);
            laterAlert.setTitle("Incorrect");
            laterAlert.setMessage("Try a date earlier than " + date.toString());
            laterAlert.setCancelable(false);
            laterAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userIn.getText().clear();
                }
            });
            laterAlert.show();
        }

        //checks if correct date is inputted
        else {
            // The user guessed the correct date
            AlertDialog.Builder correctAlert = new AlertDialog.Builder(GuessMaster.this);
            correctAlert.setTitle("You Won");
            correctAlert.setMessage("BINGO!! You guessed the correct birthday!\n" + entity.toString());
            correctAlert.setCancelable(false);
            // Update the ticket count and display it after the continue button is clicked
            numOfTickets += entity.getAwardedTicketNumber();
            ticketsum.setText("Total tickets: " + numOfTickets);

            correctAlert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Allow the user to guess again and change the entity
                    userIn.getText().clear();
                    ContinueGame();
                }
            });
            correctAlert.show();
        }

    }

    public void ContinueGame(){
        //continues game after a correct entity is chosen
        entityId = genRandomEntityInd();
        Entity entity = entities[entityId];

        String entName = entity.getName();

        ImageSetter(entity);
        entityName.setText(entName);
        userIn.getText().clear();
    }
    //plays the game with the entity at the random index
    public void playGame(int entityInd) {
        playGame(entities[entityInd]);
    }

    //generates a random index to be used in the Entity array
    public void playGame() {
        playGame(genRandomEntityInd());
    }
    
    int genRandomEntityInd() {
        Random randNumb = new Random();
        //will find a random numbers between 0 and numberOfCandidateEntities
        int rand = randNumb.nextInt(numberOfCandidateEntities);
        return rand;

    }
}