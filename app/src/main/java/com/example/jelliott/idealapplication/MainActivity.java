package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener  {

    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final double maxOverallWealth = 10000000.0;
    private static final int maxInfluence = 2000000;


    private int influenceAmountDefault = 500000;
    private double overallWealthDefault = 5000000.0;
    private int influence;

    private TextView jobTextView;
    private TextView countryTextView;
    private TextView taxTextView;
    private TextView informationalTextView;
    private TextView overallWealthTextView;
    private TextView overallWealthPercentageTextView;
    private TextView influenceTextView;
    private TextView influencePercentageTextView;

    private TextView workingOnPhysicalAppTextView;
    private TextView socialisingWithFriendsTextView;
    private TextView schoolAttendanceAmountTextView;
    private TextView priceToMoveTextView;
    private TextView playerNameTextView;

    //TextViews on the Profile Dialog
    private TextView playerNameTextViewprofileDialog;
    private TextView age_Turn_textView;
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


    //For the Progress Bars
    private static final int PROGRESS = 0x1;
    private Handler mHandler;
    private int mProgressStatusOverAllWealth = 0, mProgressStatusInfluence = 0;


    private ProgressBar overallWealthProgressBar;
    private ProgressBar influenceProgressBar;

    private Button changeCountryButton,selectAJobButton,schoolButton,workOnPhysicalAppearanceButton,socializeWithPeopleButton;

    private ImageView profileImage;

    //Separate Dialogs
    private Dialog alertDialogProfile;

    //private Dialog alertDialog;

    /* array list to save the indexes of the array items*/
    private List<String> selectedItemIndexList;
    private int turn = 0;


    private Human human;
    private int age = 0, schoolAttendanceAmount = 0, socialisingWithFriends = 0, workingOnPhysicalApp = 0;
    private int value, looks = 0, worshippers = 0, friends = 0, professionalAssociates = 0;
    private double wealth = 0.0, tax = 0.0;

    private Family family;
    private Countries countryOfUser;
    //private Scanner scanner=new Scanner(System.in);
    private FamilyMember mother, sister, brother, father;
    private String familyName, firstNamePart, lastNamePart;
    Random rand = new Random();
    int randomNum = rand.nextInt((50 - 1) + 1);
    private Boolean banker = false, independent = false, buisnessowner = false, king = false, intern = false;
    private boolean begger = false;
    private boolean vagrant = false;
    private boolean packingboy = false;
    private boolean firefighter = false;
    private boolean scientist = false;
    private boolean god = false;
    private boolean sultan = false;
    private boolean worshippersFollow = false;
    private boolean omega = false;
    private boolean worshippersActivation = false;
    private boolean influencActivation = false;
    private boolean heavenBoolean = false;
    private int tempNum = age - 10;
    //This boolean is tokeep track whether the user is done intializing their IDEAL Life
    boolean dialogShown=true;
    //For selecting and image
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //Before ANYTHING CREATE A HUMAN TO SET THE BASIC VARIABLES OF A HUMAN
        human = new Human();

        //setting the Java variables with the XML id:Initialization
        jobTextView = (TextView) findViewById(R.id.jobTextView);
        countryTextView = (TextView) findViewById(R.id.countryTextView);
        taxTextView = (TextView) findViewById(R.id.taxTextView);
        informationalTextView = (TextView) findViewById(R.id.informationalTextView);
        informationalTextView.setMovementMethod(new ScrollingMovementMethod());
        overallWealthTextView = (TextView) findViewById(R.id.overAllWealthTextView);
        overallWealthPercentageTextView = (TextView) findViewById(R.id.overallWealthPercentage);
        influenceTextView = (TextView) findViewById(R.id.influenceTextView);
        influencePercentageTextView = (TextView) findViewById(R.id.influencePercentageTextView);
        playerNameTextView = (TextView) findViewById(R.id.playerNameTextView);
        age_Turn_textView = (TextView) findViewById(R.id.age_Turn_textView);
        //TextViews with the Buttons on fragment_main.xml
        workingOnPhysicalAppTextView = (TextView) findViewById(R.id.workingOnPhysicalAppTextView);
        socialisingWithFriendsTextView = (TextView) findViewById(R.id.socialisingWithFriendsTextView);
        schoolAttendanceAmountTextView = (TextView) findViewById(R.id.schoolAttendanceAmountTextView);
        priceToMoveTextView = (TextView) findViewById(R.id.priceToMoveTextView);

        //Buttons
        //Change Country Button
        changeCountryButton = (Button) findViewById(R.id.moveToButton);
        changeCountryButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                selectACountry();
            }
        });
        //Select A Job Button
        selectAJobButton=(Button) findViewById(R.id.jobButton);
        selectAJobButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                selectAJob();
            }
        });
        //Go To School Button
        schoolButton=(Button) findViewById(R.id.schoolButton);
        schoolButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                goToSchool();
            }
        });
        //Work on Physical Appearance
        workOnPhysicalAppearanceButton= (Button) findViewById(R.id.physicalButton);
        workOnPhysicalAppearanceButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                workOnPhysical();
            }
        });
        //Socialize with People
        socializeWithPeopleButton= (Button) findViewById(R.id.socializeButton);
        socializeWithPeopleButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                socializeWithPeople();
            }
        });



        //Profile Image
        profileImage = (ImageView) findViewById(R.id.profileImageView);
        profileImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                profilePictureDialog();

            }
        });

        profileImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                profileImage.setImageURI(photoPickerIntent.getData());
                return false;
            }
        });

        //Progress Bars
        overallWealthProgressBar = (ProgressBar) findViewById(R.id.overallWealthProgressBar);
        influenceProgressBar = (ProgressBar) findViewById(R.id.influenceProgressBar);
        mHandler = new Handler();
        //Setting the Progress Bars
        //calculateTheProgressBarPercentage(overallWealthDefault,5000000);


        //Setting Values of all fields
        //Setting the overallWealth from java to XML
        //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, human.getJob(), human.getCountry(), human.getCountry().getTaxes());
        age_Turn_textView.setText("Age:" + Integer.toString(age));
        workingOnPhysicalAppTextView.setText("Physical Appearance:" + Integer.toString(workingOnPhysicalApp));
        socialisingWithFriendsTextView.setText("Socialize Amount:" + Integer.toString(socialisingWithFriends));
        schoolAttendanceAmountTextView.setText("School Attendance:" + Integer.toString(schoolAttendanceAmount));
        priceToMoveTextView.setText("Price:" + Double.toString(0.0));
        //informationalTextView.setText("Welcome and Good Luck!");

        //init();
        //makeYourName();
        //selectAFamilyType();
        //selectACountry();
        //The Actual IDEAL Application
        new Thread(new IDEALLifeProgram()).run();

        //tutorialWithFamily();

        //chancesOfLife();
        //idealLifeParameters();
        //selectAJob();

        //new IDEALLifeProgram().execute();






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
            init();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    private void calculateTheProgressBarPercentage(Double overallWealthA, int influenceAmountA) {//The Influence Progress Bar Percentage is not very accurate...May not be a big issue

        /*
        Calculating the percentage of the wealth/Influence first and then figuring out if they are <=0 before updating the actual progress Bar...
        So that no number less than Zero is placed into the progressBar and potentially crashing the program
         */
        //Calculate the OverAllWealth Percentage
        double overallwealthvalue = ((overallWealthA / maxOverallWealth) * 100);//Complete the whole percentage equation and then convert number to Int for the Progress Bars
        //Calculate the influence Percentage
        double influencevalue = ((influenceAmountA * 1.0) / (maxInfluence * 1.0) * 100);
        //Don't need to get the product of (human.getOverAllWealth() / maxOverallWealth) and 100 because the percentFormat already multiplies product

        //This will allow the progress Bars to decrease in value
       if(overallWealthProgressBar.getProgress()> overallwealthvalue){
           overallWealthProgressBar.setProgress((int) overallwealthvalue);
           overallWealthPercentageTextView.setText((int) overallwealthvalue + "%");
       }
        if(influenceProgressBar.getProgress()> influencevalue){
            influenceProgressBar.setProgress((int) influencevalue);
            influencePercentageTextView.setText((int) influencevalue + "%");
        }
        if ( overallwealthvalue <= 1 ) {
            overallWealthProgressBar.setProgress(1);
            overallWealthPercentageTextView.setText("<" + percentFormat.format(0.01));

        }
        if ( influencevalue <= 1 ) {

            influenceProgressBar.setProgress(1);
            influencePercentageTextView.setText("<" + percentFormat.format(0.01));
        }

        // mProgressStatusOverAllWealth = 0,getmProgressStatusInfluence=0
        //overAllWealthProgress must be changed to int because Progress Bars does not accept Doubles
        final int overAllWealthProgress = (int) overallwealthvalue;
        final int influenceProgress = (int) influencevalue;
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatusOverAllWealth < overAllWealthProgress) {
                    mProgressStatusOverAllWealth += 1;//Progress Bars can not accept a value less than one..so 0.5 will not work

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("A");
                            overallWealthProgressBar.setProgress(mProgressStatusOverAllWealth);
                            overallWealthPercentageTextView.setText(mProgressStatusOverAllWealth + "%");
                        }
                    });

                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(225);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

        }).start();

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (mProgressStatusInfluence < influenceProgress) {
                    mProgressStatusInfluence += 1;//Progress Bars can not accept a value less than one..so 0.5 will not work

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("A");
                            influenceProgressBar.setProgress(mProgressStatusInfluence);
                            influencePercentageTextView.setText(mProgressStatusInfluence + "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(225);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }).start();

        //influenceProgressBar.setProgress((int) influencevalue );
        informationalTextView.setText("Influence Parameter:" + influenceAmountA + "InfluenceProgress" + influencevalue + " WealthProgress " + overAllWealthProgress + " overallwealthvalue: " + overallwealthvalue + " influencevalue " + influencevalue);
        System.out.println("Influence Parameter:" + influenceAmountA + "InfluenceProgress" + influencevalue + " WealthProgress " + overAllWealthProgress + " overallwealthvalue: " + overallwealthvalue + " influencevalue " + influencevalue);
        //String valueString;
        //influencePercentageTextView.setText(valueString = percentFormat.format(influencevalue) );
        //overallWealthProgressBar.setProgress((int) overallwealthvalue);

        //overallWealthPercentageTextView.setText(valueString = percentFormat.format(overallwealthvalue) );


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

    public void init() {
        //So that the Application knows that a dialog is up
        dialogShown=true;

        AlertDialog ad = new AlertDialog.Builder(this)
                .setMessage("Welcome!The purpose of this Java Program is to create your fantasy ideal life.." +
                        ("\n") + "Create a character at the bottom of society." +
                        ("\n") + "Progress this character through the world and accumulate influence, wealth, and associates." +
                        ("\n") + "Don't hold back,accumulating everything the world has the offer is the key of winning the game" +
                        ("\n") + "Good Luck!")
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL")
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                       dialogShown=false;
                    }
                })
                .setCancelable(false)
                .create();

        ad.show();

    }

    public void makeYourName() {
        //So that the Application knows that a dialog is up
        dialogShown=true;
        //Creating a Layout for the EditTextViews
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Set up First Name Text View
        final EditText userNameFirst = new EditText(this);
        userNameFirst.setHint("Enter In First Name");
        layout.addView(userNameFirst);

        //Setup the Last Name Text View
        final EditText userNameFamily = new EditText(this);
        userNameFamily.setHint("Enter In Last Name");
        layout.addView(userNameFamily);


        AlertDialog ad = new AlertDialog.Builder(this)

                .setMessage("Enter in a Name(MAX:11 CHARACTERS)")
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        firstNamePart = userNameFirst.getText().toString();
                        playerNameTextView.setText(firstNamePart);
                        lastNamePart = userNameFamily.getText().toString();
                        dialogShown=false;


                    }
                })
                .setCancelable(false)
                .setView(layout)
                .create();

        ad.show();
    }

    public void selectACountry() {
        //So that the Application knows that a dialog is up
        dialogShown=true;
        //Get the arrayLIst from the String xml
        final List<String> countries = Arrays.asList(getResources().getStringArray(R.array.countries));

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.countrieslistlayout, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle("Choose a Country");

        final ListView countrieslistView = (ListView) convertView.findViewById(R.id.countriesListXML);
        //countrieslistView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, countries);
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Object checkedItem;

                //This "try" statement is used so that if the user presses the "Yes" button without selecting something..the application would not call a Null Pointer/Crash
                try {
                    //This returns the object attached to to selected item in the listView
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
                    //Get the Proper Neighborhood
                    if (human.getFriends() > human.getCountry().getRequiredFriends() || human.getOverAllWealth() > human.getCountry().getRequireedWealth()
                            && human.getInfluence() > human.getCountry().getRequiredInfluence()) {
                        human.setNeighborhood(human.getCountry().getRichNeighborHood());


                    } else if (human.getFriends() == human.getCountry().getRequiredFriends() || human.getOverAllWealth() == human.getCountry().getRequireedWealth()
                            && human.getInfluence() == human.getCountry().getRequiredInfluence()) {
                        human.setNeighborhood(human.getCountry().getMiddleNeighborHood());

                    } else {
                        human.setNeighborhood(human.getCountry().getPoorNeighborHood());
                    }
                    //Update Country
                    countryOfUser = getThisCountry(checkedItem.toString());
                    human.setCountries(countryOfUser);
                    countryTextView.setText("Country:" + human.getCountry());
                    //Update Tax
                    //You can chain .get commands as long as that .get returns a class/object with its own getters
                    taxTextView.setText("Tax:" + currencyFormat.format(human.getCountry().getTaxes()));
                    tax = human.getCountry().getTaxes();

                    //Update the Amount of money it Costs to move from current Country to Next
                    //Tax+BasePrice(100)*CountryMultiplier
                    priceToMoveTextView.setText("Price" + currencyFormat.format((human.getCountry().getTaxes() + 100) * human.getCountry().getMultiplier()));
                    keepStatsUpToDate(-(human.getCountry().getTaxes() + 100 * human.getCountry().getMultiplier()),0,human.getJob(),human.getCountry().getTaxes(),0,0,0,0);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "A Country is needed",
                            Toast.LENGTH_LONG).show();
                    selectACountry();

                }
                dialogShown = false;

            }
        })

                .setCancelable(false);
        countrieslistView.setAdapter(adapter);
        alertDialog.show();


    }

    public Countries getThisCountry(String countryName) {

        if (countryName.equals(Countries.Irada.getName())) {
            countryOfUser = Countries.Irada;

        } else if (countryName.equals(Countries.Itican.getName())) {
            countryOfUser = Countries.Itican;

        } else if (countryName.equals(Countries.Albaq.getName())) {
            countryOfUser = Countries.Albaq;

        } else if (countryName.equals(Countries.Trinentina.getName())) {
            countryOfUser = Countries.Trinentina;

        } else if (countryName.equals(Countries.Albico.getName())) {
            countryOfUser = Countries.Albico;

        } else if (countryName.equals(Countries.Ugeria.getName())) {
            countryOfUser = Countries.Ugeria;

        } else if (countryName.equals(Countries.Portada.getName())) {
            countryOfUser = Countries.Portada;

        } else if (countryName.equals(Countries.Kuwador.getName())) {
            countryOfUser = Countries.Kuwador;

        } else if (countryName.equals(Countries.Ukrark.getName())) {
            countryOfUser = Countries.Ukrark;

        } else if (countryName.equals(Countries.Rany.getName())) {
            countryOfUser = Countries.Rany;

        } else {

            countryOfUser = Countries.Heaven;

        }
        return countryOfUser;

    }

    public void selectAFamilyType() {
        //So that the Application knows that a dialog is up
        dialogShown=true;

        //Get the arrayLIst from the String xml
        final List<String> familyTypes = Arrays.asList(getResources().getStringArray(R.array.familyTypes));
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Choose A Family");

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.familycreationlayout, null, false);
        alertDialog.setView(convertView);
        final ListView familyListView = (ListView) convertView.findViewById(R.id.familyChoiceList);
        final EditText friendsAmountEditText = (EditText) convertView.findViewById(R.id.friendsAmountEditText);
        final EditText influenceAmountEditText = (EditText) convertView.findViewById(R.id.influenceAmountEditText);
        final EditText wealthAmountEditText = (EditText) convertView.findViewById(R.id.wealthAmountEditText);
        final Switch customFamilySwitch = (Switch) convertView.findViewById(R.id.customFamilySwitch);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, familyTypes);
        customFamilySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Toast.makeText(MainActivity.this, "All Values need to be greater than 0 or you will have to create a new Family",
                    //Toast.LENGTH_LONG).show();
                    familyListView.setEnabled(false);
                    familyListView.setBackgroundColor(Color.parseColor("#3f3f3f"));
                    familyListView.setItemChecked(0, false);
                    familyListView.setItemChecked(1, false);
                    familyListView.setItemChecked(2, false);
                    familyListView.setItemChecked(3, false);
                    friendsAmountEditText.setVisibility(View.VISIBLE);
                    influenceAmountEditText.setVisibility(View.VISIBLE);
                    wealthAmountEditText.setVisibility(View.VISIBLE);

                } else {
                    // The toggle is disabled
                    //isChecked=false;
                    //selectAFamilyType();
                    familyListView.setEnabled(true);
                    familyListView.setBackgroundColor(Color.parseColor("#ff8a54"));
                    familyListView.setItemChecked(0, false);
                    familyListView.setItemChecked(1, false);
                    familyListView.setItemChecked(2, false);
                    familyListView.setItemChecked(3, false);
                    friendsAmountEditText.setVisibility(View.INVISIBLE);
                    influenceAmountEditText.setVisibility(View.INVISIBLE);
                    wealthAmountEditText.setVisibility(View.INVISIBLE);

                }
            }
        });
        alertDialog
                //Use this Button to unlock the Custom Family Text View-> Don't use this because in an
                // AlertDialog all the attached buttons close the Dialog
                /*.setNeutralButton("Custom Family", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        friendsAmountEditText.setVisibility(View.VISIBLE);
                        influenceAmountEditText.setVisibility(View.VISIBLE);
                        wealthAmountEditText.setVisibility(View.VISIBLE);
                    }
                })*/
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()

                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Object checkedItem;

                                if (customFamilySwitch.isChecked()) {

                                    try {
                                        //Constructor being called:public Family( double familyWealthA,double influenceA,int friendsA)
                                        family = new Family(Double.parseDouble(wealthAmountEditText.getText().toString()), Integer.parseInt(influenceAmountEditText.getText().toString()),
                                                Integer.parseInt(friendsAmountEditText.getText().toString()));
                                        keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                                tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                        human.setFriends(Integer.parseInt(friendsAmountEditText.getText().toString()));
                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity.this, "A Custom Family with <=0 values",
                                                Toast.LENGTH_LONG).show();
                                        selectAFamilyType();

                                    }
                                } else {
                                    checkedItem = familyListView.getAdapter().getItem(familyListView.getCheckedItemPosition());
                                    //Getting the String from the selected item from the Family List View
                                    String selectedFamilyType = checkedItem.toString();
                                    //This  try/catch block code catches the (Null Pointer) exception if nothing is selected
                                    //try {

                                    switch (selectedFamilyType) {

                                        case "Rich Family":
                                            father = new FamilyMember(true, familyName, Jobs.BUSINESSOWNER, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                            mother = new FamilyMember(false, familyName, Jobs.BANKTER, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                            sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                            brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                            family = new Family(familyName, brother, sister, father, mother);


                                            break;
                                        case "Middle Family":
                                            father = new FamilyMember(true, familyName, Jobs.FIREFIGHTER, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                            mother = new FamilyMember(false, familyName, Jobs.FIREFIGHTER, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                            sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                            brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                            family = new Family(familyName, brother, sister, father, mother);


                                            break;
                                        default:
                                            father = new FamilyMember(true, familyName, Jobs.BEGGER, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                            mother = new FamilyMember(false, familyName, Jobs.PACKINGBOY, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                            sister = new FamilyMember(false, familyName, Jobs.INTERN, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                            brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                            family = new Family(familyName, brother, sister, father, mother);

                                            break;
                                    }
                                    keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                            tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());


                                    dialogShown = false;
                                    Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString(),
                                            Toast.LENGTH_LONG).show();

                                    //} catch (Exception e) {
                                    //Toast.makeText(MainActivity.this, "A Family Type is needed",
                                    // Toast.LENGTH_LONG).show();
                                    //selectAFamilyType();

                                    //}
                                }


                            }


                        }

                );
        familyListView.setAdapter(adapter);
        alertDialog.show();

    }


    public void profilePictureDialog() {


        alertDialogProfile = new Dialog(MainActivity.this, android.R.style.Theme_Black);
        //

        //AlertDialog.Builder alertDialogProfile = new AlertDialog.Builder(MainActivity.this);

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.profileimagedialog, null);
        //alertDialogProfile.setView(convertView);
        alertDialogProfile.setContentView(R.layout.profileimagedialog);

        //keepStatsUpToDate(human.getOverAllWealth(), human.getInfluence(), human.getJob(),human.getCountry(),tax);
        //userNameTextView.setText(firstNamePart);
        alertDialogProfile.setTitle(firstNamePart + " " + lastNamePart + "'s Profile");

        playerNameTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.playerNameTextViewprofileDialog);
        playerNameTextViewprofileDialog.setText(firstNamePart + " " + lastNamePart);

        overAllWealthTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.overAllWealthTextViewprofileDialog);
        overAllWealthTextViewprofileDialog.setText(getString(R.string.overallWelathTextView_text) + ": \n" + currencyFormat.format(human.getOverAllWealth()));

        influenceTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText(getString(R.string.influenceTextView) + ": \n" + human.getInfluence());

        jobTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setText(getString(R.string.getJob_text) + ":" + human.getJob());

        countryTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText(getString(R.string.getCountry_text) + ":" + human.getCountryString());

        neighbourhoodTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText(getString(R.string.neighbourhoodTextView_text) + ":" + human.getNeighborhood());

        taxTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog);
        taxTextViewprofileDialog.setText(getText(R.string.tax_text) + ":" + currencyFormat.format(tax));

        userfriendsTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText(getText(R.string.friendsTextView_text) + ":" + human.getFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText(getText(R.string.professionalAssociatesTextView_text) + ":" + human.getProfessionalAssociates());

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText(getText(R.string.worshippersTextView_text) + ":" + human.getWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText(getText(R.string.looksTextView_text) + ":" + human.getLooks());


        alertDialogProfile.show();

    }

    //This Method is used to display stats of the Family during the Tutorial Stages
    public void profilePictureDialogFamilyTutorial() {


        alertDialogProfile = new Dialog(MainActivity.this, android.R.style.Theme_Black);
        //

        //AlertDialog.Builder alertDialogProfile = new AlertDialog.Builder(MainActivity.this);

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.profileimagedialog, null);
        //alertDialogProfile.setView(convertView);
        alertDialogProfile.setContentView(R.layout.profileimagedialog);

        //keepStatsUpToDate(human.getOverAllWealth(), human.getInfluence(), human.getJob(),human.getCountry(),tax);
        //userNameTextView.setText(firstNamePart);
        alertDialogProfile.setTitle(firstNamePart + "'s Family Profile");

        playerNameTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.playerNameTextViewprofileDialog);
        playerNameTextViewprofileDialog.setText(firstNamePart);

        overAllWealthTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.overAllWealthTextViewprofileDialog);
        overAllWealthTextViewprofileDialog.setText("Wealth:\n" + currencyFormat.format(human.getOverAllWealth()));

        influenceTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText("Influence:\n" + influenceAmountDefault);

        jobTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setText("Job:" + human.getJob());

        countryTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText("Country:" + human.getCountryString());

        neighbourhoodTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText("Neighbourhood:" + human.getNeighborhood());

        taxTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog);
        taxTextViewprofileDialog.setText("Tax:" + currencyFormat.format(tax));

        userfriendsTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText("Friends:" + human.getFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText("Professional Associates:" + human.getProfessionalAssociates());

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText("Worshippers:" + human.getWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText("Looks:" + human.getLooks());


        alertDialogProfile.show();

    }

    public void chancesOfLife() {

        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })


                .setTitle("IDEAL:Chances of Life")
                .setCancelable(false)
                .create();



        ///If you want to test any specific chance of life just place in the case number into switch bracket..the program will only run that switch
        switch (randomNum) {

            case 0:
                //Move to another country with higher or lower income
                //This affects your wealth with the family in the tutorial
                //This will affect your overall wealth in th later game

                ad.setMessage("You decided to move;so here is the  opportunity to choose a different country!");
                 selectACountry();


                break;
            case 1:

                //ad.setMessage("2");
                if (age < 20) {
                    if (family.getFamilyFriends() > 1000) {
                         ad.setMessage("Your family is very famous and this allows you to earn $500");
                        family.setFamilyWealth(human.getOverAllWealth() + 500);
                         ad.setMessage("This character's wealth is $" + family.getFamilyWealth());

                        //CharDetails();
                    } else if (family.getFamilyFriends() > 500) {
                         ad.setMessage("Your family is Famous and this allows you to earn $100");
                        family.setFamilyWealth(human.getOverAllWealth() + 100);
                         ad.setMessage("This character's wealth is $" + family.getFamilyWealth());

                        //CharDetails();
                    } else {
                         ad.setMessage("Your family is not very popular;it is beneficial to have more friends!!");
                    }

                } else {
                    if (human.getFriends() > 1000) {
                         ad.setMessage("You are very famous and this allows you to earn $500");
                        human.setOverAllwealth(human.getOverAllWealth() + 500);
                         ad.setMessage("This character's wealth is $" + human.getOverAllWealth());

                        //CharDetails();
                    } else if (human.getFriends() > 500) {
                         ad.setMessage("You are famous  and this allows you to earn $100");
                        human.setOverAllwealth(human.getOverAllWealth() + 100);
                         ad.setMessage("This character's wealth is $" + human.getOverAllWealth());

                        //CharDetails();
                    } else {
                         ad.setMessage("You are not very popular;it is beneficial to have more friends!!");
                    }

                }


                break;
            case 2:

                // ad.setMessage("3");
                if (human.getInfluence() > 100000 || human.getInfluence() + family.getFamilyInfluence() > 100000) {
                     ad.setMessage("Your family has a high influence level and is able to get you to the best schools and mentors" + "\n" + "You have will have access to the intern job "
                            + "\n" + "You have will have access to the Banker job "
                            + "\n" + "You have will have access to the Business Owner job ");

                    intern = true;
                    banker = true;
                    buisnessowner = true;

                } else {
                     ad.setMessage("Your family's influence is not very high;try increasing it!!");
                }


                break;
            case 3:

                // ad.setMessage("4");
                if (human.getFriends() > 100000 || human.getFriends() + family.getFamilyFriends() > 100000) {
                     ad.setMessage("Having many friends make ways for more friends.You gained 500 friends");
                    human.setFriends(human.getFriends() + 500);
                    //CharDetails();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (human.getFriends() > 1000 || human.getFriends() + family.getFamilyFriends() > 1000) {
                     ad.setMessage("You don't have much friends but you still get a bonus.You gained 100 friends");
                    human.setFriends(human.getFriends() + 100);
                    //CharDetails();

                } else {
                     ad.setMessage("You do not have enough friends to access this bonus");

                }

                break;
            case 4:

                // ad.setMessage("5");
                 ad.setMessage("A storm came over and destroyed your car.You lose some of your money in the process of replacing it");

                if (age > 20) {
                    human.setOverAllwealth(human.getOverAllWealth() - 10000);
                     ad.setMessage("You got charged:10000");
                    //CharDetails();


                } else {
                    wealth = -10000;
                    updatingStateOfFamily(wealth, 0);
                    wealth = 0;
                     ad.setMessage("Your family got charged $10000");


                }

                break;
            case 5:
                // ad.setMessage("6");

                if (human.getLooks() > 1 && human.getLooks() < 5) {
                     ad.setMessage("You are not very good looking and people do not resonate with you very well.What a shallow world where people judge based on your looks \n You gain nothing");


                } else if (human.getLooks() == 5) {
                     ad.setMessage("You are average looking so very few people take you on face value.What a shallow world where people judge based on your looks \n You gain $100 \n" +
                            "You gain 100 influence" +
                            "\n You gain 100 friends");


                    human.setOverAllwealth(human.getOverAllWealth() + 100);
                    human.setInfluence(human.getInfluence() + 100);
                    human.setFriends(human.getFriends() + 100);


                } else if (human.getLooks() < 15) {
                    ad.setMessage("People automatically love you at face value.What a shallow world where people judge based on your looks \n You gain $500 \n" +
                            "You gain 500 influence" +
                            "\n You gain 500 friends");

                    human.setOverAllwealth(human.getOverAllWealth() + 500);
                    human.setInfluence(human.getInfluence() + 500);
                    human.setFriends(human.getFriends() + 500);

                }
                //CharDetails();
                break;
            case 6:
                // ad.setMessage("7");
                if (god && age > 20) {
                    ad.setMessage("You have surpass the earthly domain and is ready to create your own heaven");
                    human.setJob(Jobs.GOD);
                    worshippersFollow = true;
                    heavenBoolean = true;
                    ad.setMessage("You have also gained access to heaven");
                    //CharDetails();


                } else if (age > 20) {
                    ad.setMessage("You inherited Godly talent! You begin to attract worshippers");
                    worshippersFollow = true;
                } else {
                    ad.setMessage("You need to be outside the family tutorial to access this benefit");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 7:
                genieDoSomething();

                break;

            case 8:
                // ad.setMessage("9");
                if (age > 20) {
                    ad.setMessage("You won the lottery so you will gain $500000");
                    human.setOverAllwealth(human.getOverAllWealth() + 500000);
                    ad.setMessage(Double.toString(human.getOverAllWealth()));

                } else {
                    wealth = 500000;
                    updatingStateOfFamily(wealth, 0);
                    ad.setMessage("Your family won the lottery,gained $500000");
                    wealth = 0;
                }

                break;


            case 9:
                // ad.setMessage("10");
                ad.setMessage("Your recent trip to IDEAL Center of Academics added professional Associates to call onto later in life");
                human.setProfessionalAssociates(human.getProfessionalAssociates() + 500);
                //CharDetails();


                break;

            case 10:

                // ad.setMessage("11");
                ad.setMessage("You get a boost in influence, wealth, and friends!You are very lucky");
                human.setOverAllwealth(human.getOverAllWealth() + 500000);
                human.setFriends(human.getFriends() + 50000);
                human.setInfluence(human.getInfluence() + 5000);
                //CharDetails();

                break;

            case 11:

                // ad.setMessage("12");
                ad.setMessage("You get a boost in professional associates, looks, and worshippers !You are very lucky");
                human.setProfessionalAssociates(human.getProfessionalAssociates() + 5000);
                human.setLooks(human.getLooks() + 2);
                human.setWorshippers(human.getWorshippers() + 5000);

                break;

            case 12:

                // ad.setMessage("13");
                ad.setMessage("You lose in  influence, wealth, and friends!You are very unlucky");
                human.setOverAllwealth(human.getOverAllWealth() - 5000);
                human.setFriends(human.getFriends() - 500);
                human.setInfluence(human.getInfluence() - 500);

            case 13:

                ad.setMessage("You lose in professional associates, looks, and worshippers !You are very unlucky");
                human.setProfessionalAssociates(human.getProfessionalAssociates() - 500);
                human.setLooks(human.getLooks() - 2);
                human.setWorshippers(human.getWorshippers() + 50);
                //CharDetails();
                 break;


        }

        ad.show();

    }

    public void genieDoSomething() {

        final Jobs temjobA = human.getJob(); ///I must undo any changes if the user doesn't comply with my instructions
        if (age > 19) {
            informationalTextView.setText("You magically found a genie and he is able to grant you three wishes!");

            for (int i = 0; i < 3; i++) {

                //Get the arrayLIst from the String xml
                final List<String> countries = Arrays.asList(getResources().getStringArray(R.array.genieChoices));

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                //Call the XML List view and set it with  the countries array
                LayoutInflater inflater = getLayoutInflater();
                View convertView = inflater.inflate(R.layout.countrieslistlayout, null);
                alertDialog.setView(convertView);
                alertDialog.setTitle("Choose A Wish");

                final ListView countrieslistView = (ListView) convertView.findViewById(R.id.countriesListXML);
                //countrieslistView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, countries);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        Object checkedItem;
                        Double tempwealthA=0.0;

                        //This "try" statement is used so that if the user presses the "Yes" button without selecting something..the application would not call a Null Pointer/Crash
                        try {
                            //This returns the object attached to to selected item in the listView
                            checkedItem = countrieslistView.getAdapter().getItem(countrieslistView.getCheckedItemPosition());


                            Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString(),
                                    Toast.LENGTH_LONG).show();
                            //Once this  country is valid for use by the user, and it exist...then set the other variables
                            //Update Stats accordingly
                            selectingAWish(value);
                            //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, job, countryOfUser, countryOfUser.getTaxes());
                            keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, temjobA,
                                    tax,human.getLooks(),human.getWorshippers(),human.getFriends(),human.getProfessionalAssociates());
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "You forfeit your wish",
                                    Toast.LENGTH_LONG).show();
                            chancesOfLife();///I may need this..I am not sure right now though


                            keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, temjobA,
                                    tax, human.getLooks(), human.getWorshippers(), human.getFriends(), human.getProfessionalAssociates());
                            worshippers = 0;
                            tempwealthA = 0.0;
                            friends = 0;
                            looks = 0;
                            professionalAssociates = 0;
                            influence = 0;


                        }


                    }
                });
            }

                /*informationalTextView.setText(" Options are displayed..Choose ONE..or lose your three wishes completely");
                informationalTextView.setText("(1)Improve your physical Appearance  " + ("\n") +
                        "(2)Increase the number of Worshippers" + ("\n") +
                        "(3)Increase the number of Friends" + ("\n") +
                        "(4)Increase the number of Professional Associates" + ("\n") +
                        "(5)Increase you Influence" + ("\n") +
                        "(6)Increase your Wealth" + ("\n") +
                        "(7)Choose any job you desire" + ("\n") +
                        "(0)Special" + ("\n"));*/


        } else {
            //updatingStateOfHuman(Jobs.NOJOB, 1, 10, 10, 10, 100);
            keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, Jobs.NOJOB,
                    tax, human.getLooks(), human.getWorshippers(), human.getFriends(), human.getProfessionalAssociates());
            informationalTextView.setText("You get a slight boost in wealth, worshippers,friends,looks,professional associates, and influence");
        }
    }

    public void selectingAWish(int numberSelection) {
        //looks=0,worshippers=0,friends=0,professionalAssociates=0,influence=0
        Jobs tempJobA;
        Double tempWealthA;
        switch (numberSelection) {
            case 1:
                //Looks
                looks = 5;
                informationalTextView.setText("Your looks was increased by 5");
                break;

            case 2:
                //Worshipers
                informationalTextView.setText("You got 1000 worshippers");
                worshippers = 1000;

                break;
            case 3:
                //Friends
                informationalTextView.setText("You got 1000 friends");
                friends = 1000;
                break;
            case 4:
                //ProfessionalAssociates
                informationalTextView.setText("You got 1000 professional Associates");
                professionalAssociates = 1000;
                break;
            case 5:
                //Influence
                informationalTextView.setText("You got 1000 Influence");
                influence = 1000;
                break;
            case 6:
                //Wealth
                informationalTextView.setText("You got 100000 wealth");
                tempWealthA = 100000.0;
                break;
            case 7:
                //selectAJob();

                switch (value) {
                    case 0:
                        tempJobA = Jobs.BEGGER;
                        begger = true;
                        break;
                    case 1:
                        tempJobA = Jobs.VAGRANT;
                        vagrant = true;
                        break;
                    case 2:
                        tempJobA = Jobs.INTERN;
                        intern = true;
                        break;
                    case 3:
                        tempJobA = Jobs.PACKINGBOY;
                        packingboy = true;
                        break;
                    case 4:
                        tempJobA = Jobs.FIREFIGHTER;
                        firefighter = true;
                        break;
                    case 5:
                        tempJobA = Jobs.BANKTER;
                        banker = true;
                        break;
                    case 6:
                        tempJobA = Jobs.SCIENTIST;
                        scientist = true;
                        break;
                    case 7:
                        tempJobA = Jobs.INDEPENDENT;
                        independent = true;
                        break;
                    case 8:
                        tempJobA = Jobs.BUSINESSOWNER;
                        buisnessowner = true;
                        break;
                    case 9:
                        tempJobA = Jobs.KING;
                        king = true;
                        break;
                    case 10:
                        tempJobA = Jobs.SULTAN;
                        sultan = true;
                        god = true;
                        break;
                    case 11:
                        tempJobA = Jobs.GOD;
                        heavenBoolean = true;
                        informationalTextView.setText("You have also gained access to heaven");
                        sultan = true;
                        god = true;
                        break;

                }
                //System.out.print("\nYou are now a(n) " + tempJobA.getName() + ("\n"));
                //keepStatsUpToDate(job, 0, 0, 0, 0, 0);
                //CharDetails();
                //informationalTextView.setText("\n");

                break;
            case 0:
                //Become Omega

                omega = true;
                tempJobA = Jobs.OMEGA;
                System.out.print("\nYou are now an " + tempJobA.getName() + ("\n"));
                heavenBoolean = true;
                informationalTextView.setText("You have also gained access to heaven");
                //keepStatsUpToDate(job, 0, 0, 0, 0, 0);
                //CharDetails();
                informationalTextView.setText("\n");


        }

    }

    //Need to update for the Price to Move to another country
    public void keepStatsUpToDate(Double overallWealthA, int influenceAmountA, Jobs jobA, /*Countries countryA,*/
                                  Double taxA, int looksA, int worshippersA, int friendsA, int professionalAssociatesA) {
        human.setJob(jobA);
        human.setIncome(jobA.getIncome());
        human.setOverAllwealth((human.getOverAllWealth() + jobA.getIncome() + overallWealthA+wealth));
        human.setInfluence((human.getInfluence() + jobA.getInfluence() + influenceAmountA));
        human.setProfessionalAssociates(human.getProfessionalAssociates() + professionalAssociatesA);
        human.setFriends(human.getFriends() + friendsA);
        human.setLooks(human.getLooks() + looksA);
        human.setWorshippers(human.getWorshippers() + worshippersA);
        //human.setCountries(countryA);
        tax = taxA;
        overallWealthTextView.setText(getString(R.string.overallWelathTextView_text) + "\n" + currencyFormat.format(human.getOverAllWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText(getString(R.string.influenceAmountTextView_String) + "\n" + influenceAmountString);
        jobTextView.setText(getString(R.string.getJob_text) + ":" + human.getJob());
        countryTextView.setText(getString(R.string.getCountry_text) + ":" + human.getCountryString());
        taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));


        //This "if" statement is going to see whether our percentage function will give something less than 1...which the progress bars can no handle
        if (overallWealthTextView.getText().length() <= 5 ) {
            calculateTheProgressBarPercentage(1.0, human.getInfluence());


        }
        if (influenceTextView.getText().length() <= 4) {
            calculateTheProgressBarPercentage(human.getOverAllWealth(), 1);

        }

        calculateTheProgressBarPercentage(human.getOverAllWealth(),human.getInfluence());

        //calculateTheProgressBarPercentage(1.0, 1);
        /*neighbourhoodTextView.setText("Neighbourhood:"+human.getNeighborhood());
        userFriendsTextView.setText("Friends:"+human.getFriends());
        professionalAssociatesTextView.setText("Professional Associates:"+human.getProfessionalAssociates());
        looksTextView.setText("Attractiveness:"+human.getLooks());*/

    }

    public void updatingStateOfFamily(double wealthA, int influenceA) {
        family.setFamilyWealth(family.getFamilyWealth() + mother.getIncome() + brother.getIncome() + sister.getIncome() + father.getIncome() + wealthA);
        family.setFamilyInfluence(family.getFamilyInfluence() + influenceA);
        if (family.getFamilyFriends() > countryOfUser.getRequiredFriends() || family.getFamilyWealth() > countryOfUser.getRequireedWealth()
                && family.getFamilyInfluence() > countryOfUser.getRequiredInfluence()) {
            family.setNeighborhood(countryOfUser.getRichNeighborHood());
            human.setNeighborhood(countryOfUser.getRichNeighborHood());


        } else if (family.getFamilyFriends() == countryOfUser.getRequiredFriends() || family.getFamilyWealth() == countryOfUser.getRequireedWealth()
                && family.getFamilyInfluence() == countryOfUser.getRequiredInfluence()) {
            family.setNeighborhood(countryOfUser.getMiddleNeighborHood());
            human.setNeighborhood(countryOfUser.getMiddleNeighborHood());

        } else {
            family.setNeighborhood(countryOfUser.getPoorNeighborHood());
            human.setNeighborhood(countryOfUser.getPoorNeighborHood());
        }
    }


    public void idealLifeParameters() {
        informationalTextView.setText("Choose a job ");
        informationalTextView.setText("\"Choose a job \"Very Low Paying Jobs:(0)Begger (1)Vagrant" + ("\n") +
                "Low Paying Jobs that add greater Influence: (2)Intern" + ("\n") +
                "Average Paying Jobs with low Influence: (3)Packingboy (4)Firefighter (5) Banker" + ("\n") +
                "Medium Paying Jobs with High Influence: (6)Scientist (7)Independent" + ("\n") +
                "Highest Paying Jobs: (8)Business Owner (9)King (10)Sultan" + ("\n") +
                "Divine Jobs: (11)God" + ("\n"));

        final Spinner selectAFamily_spinnnerOne, selectACountry_spinnnerTwo;


        AlertDialog.Builder alertDialogidealLife = new AlertDialog.Builder(MainActivity.this);
        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.ideallife_parameters_layout, null);
        alertDialogidealLife.setView(convertView);


        alertDialogidealLife.setTitle("Set Up the Parameters for your life")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Object checkedItem;

                    }
                })

                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                    }
                })
                .setCancelable(false);


        //Pick A Family
        selectAFamily_spinnnerOne = (Spinner) convertView.findViewById(R.id.selectAFamily_spinnnerOne);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.familyTypes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // Apply the adapter to the spinner


        selectACountry_spinnnerTwo = (Spinner) convertView.findViewById(R.id.selectACountry_spinnnerTwo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTwo.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        // Apply the adapter to the spinner
        //selectAFamily_spinnnerOne.setAdapter(adapter);
        selectACountry_spinnnerTwo.setAdapter(adapterTwo);


        alertDialogidealLife.show();

    }

    public void worshippersFollow(boolean worshippersFollow) {

        if (worshippersFollow && tempNum != age) {

            human.setWorshippers(human.getWorshippers() + 100);
            System.out.println("You attracted 100 worshippers");
            tempNum++;

        }
        if (worshippersFollow && god) {


            human.setWorshippers(human.getWorshippers() + 1000);
            System.out.println("You attracted 1000 worshippers");


        }
    }

    public void selectAJob() {

        professionalAssociates = 0;
        friends = 0;
        //Creating a Layout for the EditTextViews
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#ff8a54"));
        //creating instance of custom view

        //Beggar//1
        final Button jobButtonBeggar = new Button(this);
        jobButtonBeggar.setHint("Beggar");
        //layout.addView(jobButtonBeggar);
        jobButtonBeggar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                professionalAssociates = 0;
                friends = 10;
               human.setJob(Jobs.BEGGER);


            }
        });

        //Vagrant//2
        final Button jobButtonVagrant = new Button(this);
        jobButtonVagrant.setHint("Vagrant");
        //layout.addView(jobButtonVagrant);
        jobButtonVagrant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 0;
                friends = 25;
                human.setJob(Jobs.VAGRANT);

            }
        });

        //Intern//3
        final Button jobButtonIntern = new Button(this);
        jobButtonIntern.setHint("Intern");
        //layout.addView(jobButtonIntern);
        jobButtonIntern.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 500;
                friends = 15;
                human.setJob(Jobs.INTERN);

            }
        });

        //PackingBoy//4
        final Button jobButtonPackingBoy = new Button(this);
        jobButtonPackingBoy.setHint("Packing Boy");
        //layout.addView(jobButtonPackingBoy);
        jobButtonPackingBoy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 50;
                friends = 10;
                human.setJob(Jobs.PACKINGBOY);

            }
        });

        //Firefighter//5
        final Button jobButtonFirefighter = new Button(this);
        jobButtonFirefighter.setHint("Firefighter");
        //layout.addView(jobButtonFirefighter);
        jobButtonFirefighter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 100;
                friends = 25;
                human.setJob(Jobs.FIREFIGHTER);

            }
        });

        //Banker//6
        final Button jobButtonBanker = new Button(this);
        jobButtonBanker.setHint("Banker");
        //layout.addView(jobButtonBanker);
        jobButtonBanker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                professionalAssociates = 200;
                friends = 50;
                human.setJob(Jobs.BANKTER);

            }
        });

        //Scientist//7
        final Button jobButtonScientist = new Button(this);
        jobButtonScientist.setHint("Scientist");
        //layout.addView(jobButtonScientist);
        jobButtonScientist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 200;
                friends = 75;
                human.setJob(Jobs.SCIENTIST);

            }
        });

        //Independent//8
        final Button jobButtonIndependent = new Button(this);
        jobButtonIndependent.setHint("Independent");
        //layout.addView(jobButtonIndependent);
        jobButtonIndependent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 300;
                friends = 100;
                human.setJob(Jobs.INDEPENDENT);

            }
        });

        //Buisness Owner//9
        final Button jobButtonBusinessOwner = new Button(this);
        jobButtonBusinessOwner.setHint("Business Owner");
        //layout.addView(jobButtonBusinessOwner);
        jobButtonBusinessOwner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 500;
                friends = 200;
                human.setJob(Jobs.BUSINESSOWNER);

            }
        });

        //King//10
        final Button jobButtonKing = new Button(this);
        jobButtonKing.setHint("King");
        //layout.addView(jobButtonKing);
        jobButtonKing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 1000;
                friends = 500;
                human.setJob(Jobs.KING);

            }
        });

        //Sultan//11
        final Button jobButtonSultan = new Button(this);
        jobButtonSultan.setHint("Sultan");
        //layout.addView(jobButtonSultan);
        jobButtonSultan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 1500;
                friends = 1000;
                human.setJob(Jobs.SULTAN);

            }
        });

        //Omega//12
        final Button jobButtonOmega = new Button(this);
        jobButtonOmega.setHint("\u03A9" + "mega");
        //layout.addView(jobButtonOmega);
        jobButtonOmega.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates = 3000;
                friends = 2000;
                human.setJob(Jobs.OMEGA);

            }
        });
        //Make the AlertDialog Scrollable


        //If statments to show only certain Buttons
        if (age < 20) {
            layout.addView(jobButtonPackingBoy);//PackingBoy
            layout.addView(jobButtonIntern);//Intern
        } else {
            if (human.getCountry() == Countries.Irada ||
                    human.getCountry() == Countries.Itican ||
                    human.getCountry() == Countries.Albaq) {

                if (begger) { layout.addView(jobButtonBeggar); }//Begger

                if (vagrant) { layout.addView(jobButtonVagrant);}//Vagrant

                if (packingboy) {  layout.addView(jobButtonPackingBoy);  }//PackingBoy

            }
            if (human.getCountry() == Countries.Trinentina ||
                    human.getCountry() == Countries.Albico ||
                    human.getCountry() == Countries.Ugeria ||
                    human.getCountry() == Countries.Portada) {


                if (firefighter) { layout.addView(jobButtonFirefighter);  }//FIREFIGHTER;

                if (banker) { layout.addView(jobButtonBanker);  }//BANKER;

                if (scientist) { layout.addView(jobButtonScientist);  }//Scientist;

            }
            if (human.getCountry() == Countries.Kuwador ||
                    human.getCountry() == Countries.Ukrark ||
                    human.getCountry() == Countries.Rany) {

                if (banker) { layout.addView(jobButtonBanker);   }//BANKER;


                if (scientist) {  layout.addView(jobButtonScientist);   }//Scientist;


                if (independent) {  layout.addView(jobButtonIndependent); }//Independent;


                if (buisnessowner) {  layout.addView(jobButtonBusinessOwner);  }//BusinessOwner;
            }
            if (human.getCountry() == Countries.Heaven) {

                if (independent) {layout.addView(jobButtonIndependent);  }//Independent;


                if (king) {  layout.addView(jobButtonKing);  }//KING;


                if (king && sultan) { layout.addView(jobButtonKing);  }//SULTAN

            }
        }

            final ScrollView scrollView = new ScrollView(this);
            scrollView.addView(layout);

            AlertDialog ad = new AlertDialog.Builder(this)

                    .setMessage("Select A Job. \n Current Country:"+human.getCountry())
                            //.setIcon(R.drawable.ic_launcher)
                    .setTitle("IDEAL")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            jobTextView.setText(human.getJob().getName());
                            age++;
                            age_Turn_textView.setText("Age: " + age);
                            keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, friends, professionalAssociates);


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            selectAJob();
                        }
                    })
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            selectAJob();
                        }
                    })
                    .setCancelable(false)
                    .setView(scrollView)
                    .create();

             //Use this command to control the positioning of your alertDialog
            //ad.getWindow().getAttributes().verticalMargin = 1.1F;
            ad.show();


        }


    public void goToSchool(){
        //Increment and Update the amount of times this user went to school
        schoolAttendanceAmount++;
        age++;
        age_Turn_textView.setText("Age: "+age);
        schoolAttendanceAmountTextView.setText("School Attendance:" + Integer.toString(schoolAttendanceAmount));
        //This String Variable is used to hold all of the messages in one string
        String chainText="";
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })

                .setMessage("Go a school:Raises the amount of professional Associates" + "\n" +
                        "Unlocks jobs" + "\n" +
                        "Raises the amount of friends"+"\n"+
                       "School Attendance:"+schoolAttendanceAmount)
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL:Going to School")
                .setCancelable(true)
                .create();


        //This DIalog is used to show which jobs were unlocked
        AlertDialog adNotifier = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                    }
                })

                .setTitle("IDEAL:Going to School")
                .setCancelable(true)
                .create();


       if (human.getOverAllWealth() >= 1 && human.getInfluence() >= 1 || schoolAttendanceAmount>=0) {
            if (!begger) {
                begger = true;
                chainText+="You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Begger Job";
                adNotifier.setMessage(chainText);
                // public void keepStatsUpToDate(Double overallWealthA, int influenceAmountA, Jobs jobA, /*Countries countryA,*/   Double taxA, int looksA, int worshippersA, int friendsA, int professionalAssociatesA)

                human.setOverAllwealth(human.getOverAllWealth()-2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);
            }
            if (!vagrant) {
                vagrant = true;
                adNotifier.setMessage( "You got charged:$" + 2500 * human.getCountry().getMultiplier()+"\n" + "You have unlocked the Vagrant Job");
                human.setOverAllwealth(human.getOverAllWealth()-2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);

            }
        }if(human.getOverAllWealth()>=10000 && human.getInfluence()>=20000 || schoolAttendanceAmount>=1){
            if(!intern){
                intern=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Intern Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);

            }if(!packingboy){
                packingboy=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Packing boy Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);

            }if(!firefighter){
                firefighter=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Firefighter Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);
            }
        }if(human.getOverAllWealth()>=70000 && human.getInfluence()>=100000 || schoolAttendanceAmount>=2&&socialisingWithFriends>=2
                &&workingOnPhysicalApp>=1){
            if(!banker){
                banker=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Banker Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);

            }if(!scientist){
                scientist=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Scientist Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);
            }if(!independent){
                independent=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Independent Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);

            }if(!firefighter){
                firefighter=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Firefighter Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);
            }
        }if(human.getOverAllWealth()>=1000000 && human.getInfluence()>=100000 && human.getFriends()>10000 || schoolAttendanceAmount>=3&&socialisingWithFriends>=3
                &&workingOnPhysicalApp>=3){
            if(!sultan){
                sultan=true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the Sultan Job");
                human.setOverAllwealth(human.getOverAllWealth() - 2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);


            }
        }if(human.getOverAllWealth()>=10000000 && human.getInfluence()>=10000000 && human.getFriends()>100000 && human.getWorshippers()>10000 ||  schoolAttendanceAmount>=4&&socialisingWithFriends>=4
                &&workingOnPhysicalApp>=4) {
            if (!god) {
                god = true;
                adNotifier.setMessage("You got charged:$" + 2500 * human.getCountry().getMultiplier() + "\n" + "You have unlocked the God Job");
                adNotifier.show();
                human.setOverAllwealth(human.getOverAllWealth()-2500.0 * human.getCountry().getMultiplier());

                keepStatsUpToDate(0.0, 0, human.getJob(), human.getCountry().getTaxes(), 0, 0, 0, 0);


            }

        }
        adNotifier.setCancelable(true);
        ad.show();
        adNotifier.show();

    }

    public void workOnPhysical(){
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })

                .setMessage("Work on your physical Appearance:Raises your looks" + "\n" +
                        "Raises the amount of worshippers" + "\n" +
                        "Raises the amount of influence" + "\n" +
                        "Raises the amount of friends" + "\n" +
                        "How many times you have worked on your physical appearance:" + workingOnPhysicalApp + "\n" +
                        "Your looks got increased by 1" + "\n" +
                        "The amount of worshippers got increased by 100" + "\n" +
                        "Your influence got increased by 1000" + "\n" +
                        "The amount of friends increased by 1000" + "\n" +
                        "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setCancelable(true)
                .create();
                ad.show();
        workingOnPhysicalApp++;
        workingOnPhysicalAppTextView.setText("Physical Appearance:" + Integer.toString(workingOnPhysicalApp));
        human.setLooks(human.getLooks() + 1);
        human.setWorshippers(human.getWorshippers() + 100);
        human.setInfluence(human.getInfluence() + 1000);
        human.setFriends(human.getFriends() + 1000);
        human.setOverAllwealth(human.getOverAllWealth() - 100 * human.getCountry().getMultiplier());
        age++;
        age_Turn_textView.setText("Age: "+age);
        keepStatsUpToDate(0.0,0,human.getJob(),human.getCountry().getTaxes(),0,0,0,0);
        //System.out.println("Your looks got increased by 1");
        //System.out.println("The amount of worshippers got increased by 100");
        //System.out.println("Your influence got increased by 1000");
        //System.out.println("The amount of friends increased by 1000");
        //System.out.println("You got charged:$" + 100 * human.getCountry().getMultiplier());

    }


    public void socializeWithPeople(){
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })

                .setMessage("How many times you socialised with people:" + socialisingWithFriends + "\n" +

                        "The amount of worshippers got increased by 100" + "\n" +
                        "Your influence got increased by 1000" + "\n" +
                        "The amount of friends increased by 1000" + "\n" +
                        "Your professional Associates got increased by 1000" + "\n" +
                        "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setCancelable(true)
                .create();
        ad.show();

        socialisingWithFriends++;
        socialisingWithFriendsTextView.setText("Socialize Amount:" + Integer.toString(socialisingWithFriends));
        System.out.println("How many times you socialised with people:" + socialisingWithFriends);
        human.setWorshippers(human.getWorshippers() + 10);
        human.setInfluence(human.getInfluence() + 100);
        human.setFriends(human.getFriends() + 10000);
        human.setProfessionalAssociates(human.getProfessionalAssociates() + 1000);
        human.setOverAllwealth(human.getOverAllWealth() - 50 * human.getCountry().getMultiplier());
        keepStatsUpToDate(0.0,0,human.getJob(),human.getCountry().getTaxes(),0,0,0,0);


        age++;
        age_Turn_textView.setText("Age: "+age);


    }

    public void tutorialWithFamily(){
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confrim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })

                .setTitle("IDEAL:Tutorial With Family/Initial State")

                .setCancelable(false)
                .create();

        //To display to the use that the application is in the Tutorial State
        ad.setMessage("Now that you have a family; it is time to start your new IDEAL life" + "\n"
                + "Every turn represents an age" + "\n"
                + "Once age 20 is reached; the player will be removed from the family and has to choose a starting location");
        ad.show();

        //First alertDialog for the Tutorial
        ad.setMessage("Family Wealth: " + family.getFamilyWealth() + "|" + "Country: " + human.getCountry().getName() + "Taxes: " + human.getCountry().getTaxes());
        ad.show();

        //Turn
        age=1;
        do {
            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }

            ad.setMessage("Turn: " + age + "\n" + "|Family" + "|" + family.getFamilyWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes());
            ad.show();
            updatingStateOfFamily(wealth,influence);
            //keepStatsUpToDate(human.getJob(), looks, worshippers, friends, professionalAssociates, influence);
            //FamilyDetails();

            age++;
            age_Turn_textView.setText("Age: "+age);

        }while(age<20);
        //Once this human reaches 20 years of age then the family's wealth will be given to the human
        human.setWealth(family.getFamilyWealth());
        human.setOverAllwealth(family.getFamilyWealth() + human.getOverAllWealth());

    }

    //Code for getting and shrinking picture selected my the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                }
        }
    }

    private Bitmap decodeUri(Uri selectedImage) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);

    }

//Calling a Thread for the application
    public class IDEALLifeProgram implements Runnable{

    @Override
    public void run(){

               try {
                   age=0;

                   if(age==0) {


                       init();
                       makeYourName();
                       selectAFamilyType();
                          selectACountry();
                       age++;

                   }else if(age<20 && age>0){
                       tutorialWithFamily();

                   }










            } catch (Throwable t) {
               System.out.println("halted due to an error" + t);
            }




        }

}

    //These methods is for when the application is paused or temporally closed
    public void onResume() {
        super.onResume();
        //mBackgroundSound.execute(null);
    }

    public void onPause() {
        super.onPause();
        //mBackgroundSound.cancel(true);
    }


    }



