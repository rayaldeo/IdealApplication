package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final double maxOverallWealth =10000000.0;
    private static final double maxInfluence =2000000.0;




    private double influenceAmount =500000.0;//This causing the application to crash...find a way for the android to show this number
    private double overallWealth =5000000.0;
    private TextView jobTextView;
    private TextView countryTextView;
    private TextView taxTextView;
    private TextView informationalTextView;
    private TextView overallWealthTextView;
    private TextView overallWealthPercentageTextView;
    private TextView influenceTextView;
    private TextView influencePercentageTextView;
   /*private TextView friendsAmountTextViewFamily;
    private TextView influenceAmountTextViewFamily;
    private TextView wealthAmountTextViewFamily;
    private TextView looksTextView;*/
    private TextView playerNameTextView;

    //TextViews on the Profile Dialog
    private TextView playerNameTextViewprofileDialog;
    private TextView userfriendsTextViewprofileDialog;
    private TextView neighbourhoodTextViewprofileDialog;
    private TextView professionalAssociatesTextViewprofileDialog;
    private TextView looksTextViewprofileDialog;
    private TextView overAllWealthTextViewprofileDialog;
    private TextView influenceTextViewprofileDialog;
    private TextView jobTextViewprofileDialog;
    private TextView countryTextViewprofileDialog;
    private TextView taxTextViewprofileDialog;
    private TextView worshippersTextViewprofileDialog;

    /*//TextViews on the FamilyType Dialog
    private EditText friendsAmountEditText;
    private EditText influenceAmountEditText;
    private EditText wealthAmountEditText;*/


    private ProgressBar overallWealthProgressBar;
    private ProgressBar influenceProgressBar;

    private  Button changeCountryButton,customFamilyButton;

    private ImageView profileImage;

    //Separate Dialogs
    private Dialog alertDialogProfile;
    //private Dialog alertDialog;

    /* array list to save the indexes of the array items*/
    private List<String> selectedItemIndexList;
    private int turn=0;

    /* public interface to communicate with the host activity*/
    public interface multiChoiceListDialogListener{
        public void onOkay(ArrayList<Integer> arrayList);
        public void onCancel();
    }

    private Human human;
    private Jobs job;
   //private Neighborhood neighborhood;
    private int age, schoolAttendanceAmount,socialisingWithFriends,workingOnPhysicalApp;
    private int value,looks=0,worshippers=0,friends=0,professionAssocites=0;
    private double wealth=0.0,tax=0.0;
    private Boolean banker=false,independent=false,buisnessowner=false,king=false,intern=false,male;
    private Family family;
    private Countries countryOfUser;
    //private Scanner scanner=new Scanner(System.in);
    private FamilyMember mother,sister,brother,father;
    private String familyName, firstNamePart,lastNamePart;
    Random rand = new Random();
    int randomNum = rand.nextInt((50-1)+1);
    private boolean begger=false;
    private boolean vagrant=false;
    private boolean packingboy=false;
    private boolean firefighter=false;
    private boolean scientist=false;
    private boolean god=false;
    private boolean sultan=false;
    private boolean worshippersFollow=false;
    private boolean omega=false;
    private boolean worshippersActivation=false;
    private boolean influencActivation=false;
    private boolean heavenBoolean =false;
    boolean dialogShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Before ANYTHING CREATE A HUMAN TO SET THE BASIC VARIABLES OF A HUMAN
         human = new Human();
        //setting the Java variables with the XML id:Initialization
        jobTextView =(TextView) findViewById(R.id.jobTextView);
        countryTextView = (TextView) findViewById(R.id.countryTextView);
        taxTextView = (TextView) findViewById(R.id.taxTextView);
        informationalTextView = (TextView) findViewById(R.id.informationalTextView);
        overallWealthTextView = (TextView) findViewById(R.id.overAllWealthTextView);
        overallWealthPercentageTextView = (TextView) findViewById(R.id.overallWealthPercentage);
        influenceTextView = (TextView) findViewById(R.id.influenceTextView);
        influencePercentageTextView=(TextView) findViewById(R.id.influencePercentageTextView);
        playerNameTextView=(TextView) findViewById(R.id.playerNameTextView);



        //Buttons
        changeCountryButton =(Button) findViewById(R.id.moveToButton);
        changeCountryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectACountry();
            }
        });


        //Profile Image
        profileImage = (ImageView) findViewById(R.id.profileImageView);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePictureDialog();

            }
        });

        //Progress Bars
        overallWealthProgressBar= (ProgressBar) findViewById(R.id.overallWealthProgressBar);
        influenceProgressBar = (ProgressBar) findViewById(R.id.influenceProgressBar);
        //Setting the Progress Bars
        calculateTheProgressBarPercentage();

        //Setting Values of all fields
        //Setting the overallWealth from java to XML
        keepStatsUpToDate(overallWealth, influenceAmount, human.getJob(), human.getCountry(), human.getCountry().getTaxes());

        informationalTextView.setText("Welcome and Good Luck!");

        init();
        makeYourName();
        selectACountry();
        selectAFamilyType();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculateTheProgressBarPercentage(){
        //Calculate the OverAllWealth Percentage
        double overallwealthvalue = ((overallWealth/maxOverallWealth)*overallWealthProgressBar.getMax());
        if(overallWealth<=0 ||overallwealthvalue<=0){
            overallWealthProgressBar.setProgress(0);
        }else{
           overallWealthProgressBar.setProgress((int) overallwealthvalue);
            String valueString;
            overallWealthPercentageTextView.setText(valueString = Double.toString(overallwealthvalue)+"/"+overallWealthProgressBar.getMax());

        }
        //Calculate the influence Percentage
        double influencevalue = ((influenceAmount/maxInfluence)*influenceProgressBar.getMax());
        if(influenceAmount<=0 || influencevalue<=0){
            influenceProgressBar.setProgress(0);
        }else{

            influenceProgressBar.setProgress((int)influencevalue);
            String valueString;
            influencePercentageTextView.setText(valueString = Double.toString(influencevalue)+"/"+influenceProgressBar.getMax());

        }


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    public void init(){

        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage("Welcome!The purpose of this Java Program is to create your fantasy ideal life.." +
                        ("\n") + "Create a character at the bottom of society." +
                        ("\n") + "Progress this character through the world and accumulate influence, wealth, and associates." +
                        ("\n") + "Don't hold back,accumulating everything the world has the offer is the key of winning the game" +
                        ("\n") + "Good Luck!")
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL")
                .setPositiveButton("Yes", this)
                .setNegativeButton("No", this)
                .setNeutralButton("Cancel", this)
                .setCancelable(false)
                .create();

        ad.show();


    }

    public void makeYourName(){
        final EditText userName = new EditText(this);

        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage("Enter in a Name(MAX:11 CHARACTERS)")
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        firstNamePart = userName.getText().toString();
                        playerNameTextView.setText(firstNamePart);


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        makeYourName();
                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        makeYourName();
                    }
                })
                .setCancelable(false)
                .setView(userName)
                .create();

        ad.show();
    }

    public void selectACountry() {
        //Get the arrayLIst from the String xml
        final List<String> countries = Arrays.asList(getResources().getStringArray(R.array.countries));

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
         //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.countrieslistlayout, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Choose a Country");

        final ListView countrieslistView = (ListView) convertView.findViewById(R.id.countriesListXML);
        //countrieslistView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,countries);
        alertDialog .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Object checkedItem;

                //This "try" statement is used so that if the user presses the "Yes" button without selecting something..the application would not call a Null Pointer/Crash
               try{
                    checkedItem = countrieslistView.getAdapter().getItem(countrieslistView.getCheckedItemPosition());

                    //Getting the selected string Country from Counties Class
                    countryOfUser = getThisCountry(checkedItem.toString());
                    if (checkedItem.toString().equals(Countries.Heaven.getName())) {
                        if (socialisingWithFriends < 4 || !heavenBoolean) {
                            Toast.makeText(MainActivity.this, "You have not socialised enough to access this place",
                                    Toast.LENGTH_LONG).show();

                        }
                        if (human.getWorshippers() < 10000 || !heavenBoolean) {
                            Toast.makeText(MainActivity.this, "You do not have enough worshippers to access this place",
                                    Toast.LENGTH_LONG).show();

                        }
                        if (human.getInfluence() < 100000 || !heavenBoolean) {
                            Toast.makeText(MainActivity.this, "You do not have enough power to access this place. Choose a different country",
                                    Toast.LENGTH_LONG).show();
                            selectACountry();
                        } else {
                            heavenBoolean = true;
                            countryOfUser = Countries.Heaven;

                        }
                    }

                    Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString(),
                            Toast.LENGTH_LONG).show();
                    //Once this  country is valid for use by the user, and it exist...then set the other variables
                    //Update Stats accordingly
                    keepStatsUpToDate(overallWealth, influenceAmount, job, countryOfUser, countryOfUser.getTaxes());
               } catch (Exception e){
                   Toast.makeText(MainActivity.this, "A Country is needed",
                           Toast.LENGTH_LONG).show();
                   selectACountry();

               }


        }
    })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                            selectACountry();

                    }
                })
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        selectACountry();
                        Toast.makeText(MainActivity.this, "A Country is needed",
                                Toast.LENGTH_LONG).show();

                    }
                })
                .setCancelable(false);
        countrieslistView.setAdapter(adapter);
        alertDialog.show();


    }

    public Countries getThisCountry(String countryName){

        if(countryName.equals(Countries.Irada.getName())) {
            countryOfUser =Countries.Irada;

        }else if (countryName.equals(Countries.Itican.getName())){
            countryOfUser =Countries.Itican;

        }else if (countryName.equals(Countries.Albaq.getName())){
            countryOfUser =Countries.Albaq;

        }else if (countryName.equals(Countries.Trinentina.getName())){
            countryOfUser =Countries.Trinentina;

        }else if (countryName.equals(Countries.Albico.getName())){
            countryOfUser =Countries.Albico;

        }else if (countryName.equals(Countries.Ugeria.getName())){
            countryOfUser =Countries.Ugeria;

        }else if (countryName.equals(Countries.Portada.getName())){
            countryOfUser =Countries.Portada;

        }else if (countryName.equals(Countries.Kuwador.getName())){
            countryOfUser =Countries.Kuwador;

        }else if (countryName.equals(Countries.Ukrark.getName())){
            countryOfUser =Countries.Ukrark;

        }else if (countryName.equals(Countries.Rany.getName())){
            countryOfUser =Countries.Rany;

        }else{

            countryOfUser =Countries.Heaven;

        }
        return countryOfUser;

    }

    public void CreateFamily(){}

    public void profilePictureDialog(){


        alertDialogProfile = new Dialog(MainActivity.this,android.R.style.Theme_Black);
         //

        //AlertDialog.Builder alertDialogProfile = new AlertDialog.Builder(MainActivity.this);

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.profileimagedialog, null);
        //alertDialogProfile.setView(convertView);
        alertDialogProfile.setContentView(R.layout.profileimagedialog);

        //keepStatsUpToDate(human.getOverAllWealth(), human.getInfluence(), human.getJob(),human.getCountry(),tax);
        //userNameTextView.setText(firstNamePart);
        alertDialogProfile.setTitle(firstNamePart + "'s Profile");

        playerNameTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.playerNameTextViewprofileDialog);
        playerNameTextViewprofileDialog.setText(firstNamePart);

        overAllWealthTextViewprofileDialog =(TextView) alertDialogProfile.findViewById(R.id.overAllWealthTextViewprofileDialog);
        overAllWealthTextViewprofileDialog.setText("Wealth:\n"+currencyFormat.format(human.getOverAllWealth()));

        influenceTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText("Influence:\n"+influenceAmount);

        jobTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setText("Job:" + human.getJob());

        countryTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText("Country:"+human.getCountryString());

        neighbourhoodTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText("Neighbourhood:"+human.getNeighborhood());

        taxTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog );
        taxTextViewprofileDialog.setText("Tax:"+currencyFormat.format(tax));

        userfriendsTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText("Friends:"+human.getFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText("Professional Associates:"+human.getProfessionalAssociates());

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText("Worshippers:"+human.getWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText("Looks:"+human.getLooks());


        alertDialogProfile.show();

    }

    public void keepStatsUpToDate(Double overallWealthA,Double influenceAmountA,Jobs jobA,Countries countryA,
    Double taxA){
        human.setOverAllwealth(overallWealthA);
        human.setInfluence(influenceAmountA);
        human.setJob(jobA);
        human.setCountries(countryA);
        tax=taxA;
        overallWealthTextView.setText("Wealth:\n"+currencyFormat.format(human.getOverAllWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText("Influence:\n"+influenceAmountString);
        jobTextView.setText("Job:" + human.getJob());
        countryTextView.setText("Country:"+human.getCountryString());
        taxTextView.setText("Tax:"+currencyFormat.format(tax));
        /*neighbourhoodTextView.setText("Neighbourhood:"+human.getNeighborhood());
        userFriendsTextView.setText("Friends:"+human.getFriends());
        professionalAssociatesTextView.setText("Professional Associates:"+human.getProfessionalAssociates());
        looksTextView.setText("Attractiveness:"+human.getLooks());*/

    }


    public void selectAFamilyType(){

        //Get the arrayLIst from the String xml
        final List<String> familyTypes = Arrays.asList(getResources().getStringArray(R.array.familyTypes));
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Choose A Family");

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.familycreationlayout, null, false);
        alertDialog.setView(convertView);
       final ListView familyListView = (ListView) convertView.findViewById(R.id.familyChoiceList);
       final EditText friendsAmountEditText=(EditText) convertView.findViewById(R.id.friendsAmountEditText);
       final EditText influenceAmountEditText= (EditText) convertView.findViewById(R.id.influenceAmountEditText);
       final EditText wealthAmountEditText= (EditText) convertView.findViewById(R.id.wealthAmountEditText);
       final Switch customFamilySwitch = (Switch) convertView.findViewById(R.id.customFamilySwitch);
       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, familyTypes);
        customFamilySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Toast.makeText(MainActivity.this, "All Values need to be greater than 0 or you will have to create a new Family",
                            Toast.LENGTH_LONG).show();
                    friendsAmountEditText.setVisibility(View.VISIBLE);
                    influenceAmountEditText.setVisibility(View.VISIBLE);
                    wealthAmountEditText.setVisibility(View.VISIBLE);
                    familyListView.setAdapter(null);
                } else {
                    // The toggle is disabled
                    selectAFamilyType();
                }
            }
        });

        alertDialog
               /* //Use this Button to unlock the Custom Family Text View
                .setNeutralButton("Custom Family", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        friendsAmountEditText.setVisibility(View.VISIBLE);
                        influenceAmountEditText.setVisibility(View.VISIBLE);
                        wealthAmountEditText.setVisibility(View.VISIBLE);
                    }
                })*/

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()

                            {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Object checkedItem;
                                    if (customFamilySwitch.isChecked()) {
                                        try {
                                            //Construtor being called:public Family( double familyWealthA,double influenceA,int friendsA)
                                            family = new Family(Double.parseDouble(wealthAmountEditText.getText().toString()), Double.parseDouble(influenceAmountEditText.getText().toString()),
                                                    Integer.parseInt(friendsAmountEditText.getText().toString()));

                                        } catch (Exception e) {
                                            Toast.makeText(MainActivity.this, "A Custom Family with <=0 values",
                                                    Toast.LENGTH_LONG).show();
                                            selectAFamilyType();

                                        }
                                    } else {


                                        //This code gets the checked item, and catching the (Null Pointer) exception if nothing is selected
                                        try {

                                            checkedItem = familyListView.getAdapter().getItem(familyListView.getCheckedItemPosition());
                                            Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString(),
                                                    Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            Toast.makeText(MainActivity.this, "A Family Type is needed",
                                                    Toast.LENGTH_LONG).show();
                                            selectAFamilyType();

                                        }
                                    }
                                }


                            }

                    );


            familyListView.setAdapter(adapter);
            alertDialog.show();


        }

    //This Method is used to display stats of the Family during the Tutorial Stages
    public void profilePictureDialogFamilyTutorial(){


        alertDialogProfile = new Dialog(MainActivity.this,android.R.style.Theme_Black);
        //

        //AlertDialog.Builder alertDialogProfile = new AlertDialog.Builder(MainActivity.this);

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = (View) inflater.inflate(R.layout.profileimagedialog, null);
        //alertDialogProfile.setView(convertView);
        alertDialogProfile.setContentView(R.layout.profileimagedialog);

        //keepStatsUpToDate(human.getOverAllWealth(), human.getInfluence(), human.getJob(),human.getCountry(),tax);
        //userNameTextView.setText(firstNamePart);
        alertDialogProfile.setTitle(firstNamePart + "'s Family Profile");

        playerNameTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.playerNameTextViewprofileDialog);
        playerNameTextViewprofileDialog.setText(firstNamePart);

        overAllWealthTextViewprofileDialog =(TextView) alertDialogProfile.findViewById(R.id.overAllWealthTextViewprofileDialog);
        overAllWealthTextViewprofileDialog.setText("Wealth:\n"+currencyFormat.format(human.getOverAllWealth()));

        influenceTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText("Influence:\n"+influenceAmount);

        jobTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setText("Job:" + human.getJob());

        countryTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText("Country:"+human.getCountryString());

        neighbourhoodTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText("Neighbourhood:"+human.getNeighborhood());

        taxTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog );
        taxTextViewprofileDialog.setText("Tax:"+currencyFormat.format(tax));

        userfriendsTextViewprofileDialog=(TextView)alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText("Friends:"+human.getFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText("Professional Associates:"+human.getProfessionalAssociates());

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText("Worshippers:"+human.getWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText("Looks:"+human.getLooks());


        alertDialogProfile.show();

    }

    }


