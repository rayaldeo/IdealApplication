package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final double maxOverallWealth = 10000000.0;
    private static final double maxInfluence = 2000000.0;


    private double influenceAmountDefault = 500000.0;
    private double overallWealthDefault = 5000000.0;
    private double influence;

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
    /*private TextView friendsAmountTextViewFamily;
     private TextView influenceAmountTextViewFamily;
     private TextView wealthAmountTextViewFamily;
     private TextView looksTextView;*/
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
    private int mProgressStatusOverAllWealth = 0,mProgressStatusInfluence=0;



    /*//TextViews on the FamilyType Dialog
    private EditText friendsAmountEditText;
    private EditText influenceAmountEditText;
    private EditText wealthAmountEditText;*/


    private ProgressBar overallWealthProgressBar;
    private ProgressBar influenceProgressBar;

    private Button changeCountryButton, customFamilyButton;

    private ImageView profileImage;

    //Separate Dialogs
    private Dialog alertDialogProfile,alertDialogJob;
    //private Dialog alertDialog;

    /* array list to save the indexes of the array items*/
    private List<String> selectedItemIndexList;
    private int turn = 0;

    /* public interface to communicate with the host activity*/
    public interface multiChoiceListDialogListener {
        void onOkay(ArrayList<Integer> arrayList);

        void onCancel();
    }

    private Human human;
    private Jobs job;
    //private Neighborhood neighborhood;
    private int age = 0, schoolAttendanceAmount=0, socialisingWithFriends=0, workingOnPhysicalApp=0;
    private int value, looks = 0, worshippers = 0, friends = 0, professionAssocites = 0;
    private double wealth = 0.0, tax = 0.0;
    private Boolean banker = false, independent = false, buisnessowner = false, king = false, intern = false, male;
    private Family family;
    private Countries countryOfUser;
    //private Scanner scanner=new Scanner(System.in);
    private FamilyMember mother, sister, brother, father;
    private String familyName, firstNamePart, lastNamePart;
    Random rand = new Random();
    int randomNum = rand.nextInt((50 - 1) + 1);
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
    boolean dialogShown;
    private  int tempNum =age-10;

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
        workingOnPhysicalAppTextView= (TextView) findViewById(R.id.workingOnPhysicalAppTextView);
        socialisingWithFriendsTextView= (TextView) findViewById(R.id.socialisingWithFriendsTextView);
        schoolAttendanceAmountTextView= (TextView) findViewById(R.id.schoolAttendanceAmountTextView);
        priceToMoveTextView= (TextView)findViewById(R.id.priceToMoveTextView);

        //Buttons
        changeCountryButton = (Button) findViewById(R.id.moveToButton);
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
        overallWealthProgressBar = (ProgressBar) findViewById(R.id.overallWealthProgressBar);
        influenceProgressBar = (ProgressBar) findViewById(R.id.influenceProgressBar);
        mHandler = new Handler();
        //Setting the Progress Bars
        //calculateTheProgressBarPercentage(maxOverallWealth,maxInfluence);



        //Setting Values of all fields
        //Setting the overallWealth from java to XML
        //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, human.getJob(), human.getCountry(), human.getCountry().getTaxes());
        age_Turn_textView.setText("Age:" + Integer.toString(age));
        workingOnPhysicalAppTextView.setText("Physical Appearance:" + Integer.toString(workingOnPhysicalApp));
        socialisingWithFriendsTextView.setText("Socialize Amount:"+Integer.toString(socialisingWithFriends));
        schoolAttendanceAmountTextView.setText("School Attendance:"+Integer.toString(schoolAttendanceAmount));
        priceToMoveTextView.setText("Price:" + Double.toString(0.0));
        informationalTextView.setText("Welcome and Good Luck!");



        init();
        makeYourName();
        selectAFamilyType();
        selectACountry();
        idealLifeParameters();


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



    private void calculateTheProgressBarPercentage(Double overallWealthA, Double influenceAmountA) {


        /*
        Calculating the percentage of the wealth/Influence first and then figuring out if they are <=0 before updating the actual progress Bar...
        So that no number less than Zero is placed into the progressBar and potentially crashing the program
         */
        //Calculate the OverAllWealth Percentage
        double overallwealthvalue = ((overallWealthA/ maxOverallWealth)*100);//Complete the whole percentage equation and then convert number to Int for the Progress Bars
        //Calculate the influence Percentage
         double influencevalue = ((influenceAmountA / maxInfluence)*100);
        //Don't need to get the product of (human.getOverAllWealth() / maxOverallWealth) and 100 because the percentFormat already multiplies product

       if (overallWealthA <= 1 || overallwealthvalue <= 1) {
            overallWealthProgressBar.setProgress(1);
            overallWealthPercentageTextView.setText("<"+percentFormat.format(0.01));

        }
        if (influenceAmountA <= 1 || influencevalue <= 1) {

            influenceProgressBar.setProgress(1);
            influencePercentageTextView.setText("<"+percentFormat.format(0.01));
        } else {
            // mProgressStatusOverAllWealth = 0,getmProgressStatusInfluence=0
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
                            Thread.sleep(450);
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
                    mProgressStatusInfluence+=1;//Progress Bars can not accept a value less than one..so 0.5 will not work

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("A");
                            influenceProgressBar.setProgress(mProgressStatusInfluence );
                            influencePercentageTextView.setText(mProgressStatusInfluence+ "%");
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(450);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }).start();

            //influenceProgressBar.setProgress((int) influencevalue );
             informationalTextView.setText("InfluenceProgress"+influenceProgress+" WealthProgress "+overAllWealthProgress+" overallwealthvalue: "+overallwealthvalue+" influencevalue "+influencevalue);
            //String valueString;
            //influencePercentageTextView.setText(valueString = percentFormat.format(influencevalue) );
            //overallWealthProgressBar.setProgress((int) overallwealthvalue);

            //overallWealthPercentageTextView.setText(valueString = percentFormat.format(overallwealthvalue) );


        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) { }



    public void init() {

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

    public void makeYourName() {
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
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        firstNamePart = userNameFirst.getText().toString();
                        playerNameTextView.setText(firstNamePart);
                        lastNamePart = userNameFamily.getText().toString();


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
                .setView(layout)
                .create();

        ad.show();
    }

    public void selectACountry() {
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
                        countryOfUser=getThisCountry(checkedItem.toString());
                } catch (Exception e) {
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

    public void CreateFamily() {
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


    public void selectAFamilyType() {

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
                                        //Constructor being called:public Family( double familyWealthA,double influenceA,int friendsA)
                                        family = new Family(Double.parseDouble(wealthAmountEditText.getText().toString()), Double.parseDouble(influenceAmountEditText.getText().toString()),
                                                Integer.parseInt(friendsAmountEditText.getText().toString()));
                                        keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), job, countryOfUser,
                                                tax);
                                        human.setFriends(Integer.parseInt(friendsAmountEditText.getText().toString()));

                                    } catch (Exception e) {
                                        Toast.makeText(MainActivity.this, "A Custom Family with <=0 values",
                                                Toast.LENGTH_LONG).show();
                                        selectAFamilyType();

                                    }
                                } else {


                                    //This  try/catch block code catches the (Null Pointer) exception if nothing is selected
                                    try {

                                        checkedItem = familyListView.getAdapter().getItem(familyListView.getCheckedItemPosition());

                                        //Getting the selected string Country from Counties Class
                                        String selectedFamilyType = checkedItem.toString();
                                        Toast.makeText(MainActivity.this, "You have selected " + selectedFamilyType,
                                                Toast.LENGTH_LONG).show();
                                        switch (selectedFamilyType) {
                                            case "Rich Family":
                                                father = new FamilyMember(true, familyName, Jobs.BUSINESSOWNER, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                                mother = new FamilyMember(false, familyName, Jobs.BANKTER, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                                sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                                brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                                family = new Family(familyName, brother, sister, father, mother);
                                                keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), job, countryOfUser,
                                                        tax);

                                                break;
                                            case "Middle Family":
                                                father = new FamilyMember(true, familyName, Jobs.FIREFIGHTER, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                                mother = new FamilyMember(false, familyName, Jobs.FIREFIGHTER, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                                sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                                brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 150, (int) (randomNum * 1.5));
                                                family = new Family(familyName, brother, sister, father, mother);
                                                keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), job, countryOfUser,
                                                        tax);

                                                break;
                                            default:
                                                father = new FamilyMember(true, familyName, Jobs.BEGGER, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                                mother = new FamilyMember(false, familyName, Jobs.PACKINGBOY, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                                sister = new FamilyMember(false, familyName, Jobs.INTERN, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                                brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 75, (int) (randomNum * 1.5));
                                                family = new Family(familyName, brother, sister, father, mother);
                                                keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), job, countryOfUser,
                                                        tax);

                                                break;
                                        }

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ///If you want to test any specific chance of life just place in the case number into switch bracket..the program will only run that switch
        switch (randomNum) {

            case 0:
                //Move to another country with higher or lower income
                //This affects your wealth with the family in the tutorial
                //This will affect your overall wealth in th later game

                informationalTextView.setText("You decided to move;so here is the  opportunity to choose a different country!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                selectACountry();
                //CountryDetails();

                break;
            case 1:

                //informationalTextView.setText("2");
                if (age < 20) {
                    if (family.getFamilyFriends() > 1000) {
                        informationalTextView.setText("Your family is very famous and this allows you to earn $500");
                        family.setFamilyWealth(human.getOverAllWealth() + 500);
                        informationalTextView.setText("This character's wealth is $" + family.getFamilyWealth());

                        //CharDetails();
                    } else if (family.getFamilyFriends() > 500) {
                        informationalTextView.setText("Your family is Famous and this allows you to earn $100");
                        family.setFamilyWealth(human.getOverAllWealth() + 100);
                        informationalTextView.setText("This character's wealth is $" + family.getFamilyWealth());

                        //CharDetails();
                    } else {
                        informationalTextView.setText("Your family is not very popular;it is beneficial to have more friends!!");
                    }

                } else {
                    if (human.getFriends() > 1000) {
                        informationalTextView.setText("You are very famous and this allows you to earn $500");
                        human.setOverAllwealth(human.getOverAllWealth() + 500);
                        informationalTextView.setText("This character's wealth is $" + human.getOverAllWealth());

                        //CharDetails();
                    } else if (human.getFriends() > 500) {
                        informationalTextView.setText("You are famous  and this allows you to earn $100");
                        human.setOverAllwealth(human.getOverAllWealth() + 100);
                        informationalTextView.setText("This character's wealth is $" + human.getOverAllWealth());

                        //CharDetails();
                    } else {
                        informationalTextView.setText("You are not very popular;it is beneficial to have more friends!!");
                    }

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case 2:

                //informationalTextView.setText("3");
                if (human.getInfluence() > 100000 || human.getInfluence() + family.getFamilyInfluence() > 100000) {
                    informationalTextView.setText("Your family has a high influence level and is able to get you to the best schools and mentors" + "\n" + "You have will have access to the intern job "
                            + "\n" + "You have will have access to the Banker job "
                            + "\n" + "You have will have access to the Business Owner job ");

                    intern = true;
                    banker = true;
                    buisnessowner = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    informationalTextView.setText("Your family's influence is not very high;try increasing it!!");
                }


                break;
            case 3:

                //informationalTextView.setText("4");
                if (human.getFriends() > 100000 || human.getFriends() + family.getFamilyFriends() > 100000) {
                    informationalTextView.setText("Having many friends make ways for more friends.You gained 500 friends");
                    human.setFriends(human.getFriends() + 500);
                    //CharDetails();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (human.getFriends() > 1000 || human.getFriends() + family.getFamilyFriends() > 1000) {
                    informationalTextView.setText("You don't have much friends but you still get a bonus.You gained 100 friends");
                    human.setFriends(human.getFriends() + 100);
                    //CharDetails();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    informationalTextView.setText("You do not have enough friends to access this bonus");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case 4:

                //informationalTextView.setText("5");
                informationalTextView.setText("A storm came over and destroyed your car.You lose some of your money in the process of replacing it");

                if (age > 20) {
                    human.setOverAllwealth(human.getOverAllWealth() - 10000);
                    informationalTextView.setText("You got charged:10000");
                    //CharDetails();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    wealth = -10000;
                    updatingStateOfFamily(wealth, 0);
                    wealth = 0;
                    informationalTextView.setText("Your family got charged $10000");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                break;
            case 5:
                //informationalTextView.setText("6");

                if (human.getLooks() > 1 && human.getLooks() < 5) {
                    informationalTextView.setText("You are not very good looking and people do not resonate with you very well.What a shallow world where people judge based on your looks \n You gain nothing");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else if (human.getLooks() == 5) {
                    informationalTextView.setText("You are average looking so very few people take you on face value.What a shallow world where people judge based on your looks \n You gain $100 \n" +
                            "You gain 100 influence" +
                            "\n You gain 100 friends");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    human.setOverAllwealth(human.getOverAllWealth() + 100);
                    human.setInfluence(human.getInfluence() + 100);
                    human.setFriends(human.getFriends() + 100);


                } else if (human.getLooks() < 15) {
                    informationalTextView.setText("People automatically love you at face value.What a shallow world where people judge based on your looks \n You gain $500 \n" +
                            "You gain 500 influence" +
                            "\n You gain 500 friends");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    human.setOverAllwealth(human.getOverAllWealth() + 500);
                    human.setInfluence(human.getInfluence() + 500);
                    human.setFriends(human.getFriends() + 500);


                }
                //CharDetails();
                break;
            case 6:
                //informationalTextView.setText("7");
                if (god && age > 20) {
                    informationalTextView.setText("You have surpass the earthly domain and is ready to create your own heaven");
                    human.setJob(Jobs.GOD);
                    worshippersFollow = true;
                    heavenBoolean = true;
                    informationalTextView.setText("You have also gained access to heaven");
                    //CharDetails();


                } else if (age > 20) {
                    informationalTextView.setText("You inherited Godly talent! You begin to attract worshippers");
                    worshippersFollow = true;
                } else {
                    informationalTextView.setText("You need to be outside the family tutorial to access this benefit");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 7:
                genieDoSomething();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;

            case 8:
                //informationalTextView.setText("9");
                if (age > 20) {
                    informationalTextView.setText("You won the lottery so you will gain $500000");
                    human.setOverAllwealth(human.getOverAllWealth() + 500000);
                    informationalTextView.setText(Double.toString(human.getOverAllWealth()));

                } else {
                    wealth = 500000;
                    updatingStateOfFamily(wealth, 0);
                    informationalTextView.setText("Your family won the lottery,gained $500000");
                    wealth = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;


            case 9:
                //informationalTextView.setText("10");
                informationalTextView.setText("Your recent trip to IDEAL Center of Academics added professional Associates to call onto later in life");
                human.setProfessionalAssociates(human.getProfessionalAssociates() + 500);
                //CharDetails();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 10:

                //informationalTextView.setText("11");
                informationalTextView.setText("You get a boost in influence, wealth, and friends!You are very lucky");
                human.setOverAllwealth(human.getOverAllWealth() + 500000);
                human.setFriends(human.getFriends() + 50000);
                human.setInfluence(human.getInfluence() + 5000);
                //CharDetails();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 11:

                //informationalTextView.setText("12");
                informationalTextView.setText("You get a boost in professional associates, looks, and worshippers !You are very lucky");
                human.setProfessionalAssociates(human.getProfessionalAssociates() + 5000);
                human.setLooks(human.getLooks() + 2);
                human.setWorshippers(human.getWorshippers() + 5000);
                //CharDetails();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;

            case 12:

                //informationalTextView.setText("13");
                informationalTextView.setText("You lose in  influence, wealth, and friends!You are very unlucky");
                human.setOverAllwealth(human.getOverAllWealth() - 5000);
                human.setFriends(human.getFriends() - 500);
                human.setInfluence(human.getInfluence() - 500);
                //CharDetails();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case 13:

                // informationalTextView.setText("14");
                informationalTextView.setText("You lose in professional associates, looks, and worshippers !You are very unlucky");
                human.setProfessionalAssociates(human.getProfessionalAssociates() - 500);
                human.setLooks(human.getLooks() - 2);
                human.setWorshippers(human.getWorshippers() + 50);
                //CharDetails();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;


        }

        informationalTextView.setText("\n" + "/--------------------------------------------------------------------------------------/");
        try {
            Thread.sleep(2000);
            informationalTextView.setText("No chances of life this turn...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    public void genieDoSomething() {

        Jobs temjobA = human.getJob(); ///I must undo any changes if the user doesn't comply with my instructions
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

                        //This "try" statement is used so that if the user presses the "Yes" button without selecting something..the application would not call a Null Pointer/Crash
                        try {
                            //This returns the object attached to to selected item in the listView
                            checkedItem = countrieslistView.getAdapter().getItem(countrieslistView.getCheckedItemPosition());


                            Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString(),
                                    Toast.LENGTH_LONG).show();
                            //Once this  country is valid for use by the user, and it exist...then set the other variables
                            //Update Stats accordingly
                            selectingAWish(value);
                            keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, job, countryOfUser, countryOfUser.getTaxes());
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "You forfeit your wish",
                                    Toast.LENGTH_LONG).show();
                            chancesOfLife();///I may need this..I am not sure right now though


                            updatingStateOfHuman(job, looks, worshippers, friends, professionAssocites, influence);
                            worshippers = 0;
                            wealth = 0;
                            friends = 0;
                            looks = 0;
                            professionAssocites = 0;
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
            updatingStateOfHuman(Jobs.NOJOB, 1, 10, 10, 10, 100);
            informationalTextView.setText("You get a slight boost in wealth, worshippers,friends,looks,professional associates, and influence");
        }
    }
    public void selectingAWish(int numberSelection) {
        //looks=0,worshippers=0,friends=0,professionAssocites=0,influence=0

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
                professionAssocites = 1000;
                break;
            case 5:
                //Influence
                informationalTextView.setText("You got 1000 Influence");
                influence = 1000;
                break;
            case 6:
                //Wealth
                informationalTextView.setText("You got 100000 wealth");
                wealth = 100000;
                break;
            case 7:
                //selectAJob();

                switch (value) {
                    case 0:
                        job = Jobs.BEGGER;
                        begger = true;
                        break;
                    case 1:
                        job = Jobs.VAGRANT;
                        vagrant = true;
                        break;
                    case 2:
                        job = Jobs.INTERN;
                        intern = true;
                        break;
                    case 3:
                        job = Jobs.PACKINGBOY;
                        packingboy = true;
                        break;
                    case 4:
                        job = Jobs.FIREFIGHTER;
                        firefighter = true;
                        break;
                    case 5:
                        job = Jobs.BANKTER;
                        banker = true;
                        break;
                    case 6:
                        job = Jobs.SCIENTIST;
                        scientist = true;
                        break;
                    case 7:
                        job = Jobs.INDEPENDENT;
                        independent = true;
                        break;
                    case 8:
                        job = Jobs.BUSINESSOWNER;
                        buisnessowner = true;
                        break;
                    case 9:
                        job = Jobs.KING;
                        king = true;
                        break;
                    case 10:
                        job = Jobs.SULTAN;
                        sultan = true;
                        god = true;
                        break;
                    case 11:
                        job = Jobs.GOD;
                        heavenBoolean = true;
                        informationalTextView.setText("You have also gained access to heaven");
                        sultan = true;
                        god = true;
                        break;

                }
                System.out.print("\nYou are now a(n) " + job.getName() + ("\n"));
                updatingStateOfHuman(job, 0, 0, 0, 0, 0);
                //CharDetails();
                informationalTextView.setText("\n");

                break;
            case 0:
                //Become Omega

                omega = true;
                job = Jobs.OMEGA;
                System.out.print("\nYou are now an " + job.getName() + ("\n"));
                heavenBoolean = true;
                informationalTextView.setText("You have also gained access to heaven");
                updatingStateOfHuman(job, 0, 0, 0, 0, 0);
                //CharDetails();
                informationalTextView.setText("\n");


        }

    }

    //Need to update for the Price to Move to another country
    public void updatingStateOfHuman(Jobs job, int looksA, int worshippersA, int friendsA, int professionalAssociatesA, double influenceA) {

        human.setJob(job);
        human.setIncome(job.getIncome());
        human.setOverAllwealth((human.getOverAllWealth() + job.getIncome() + wealth));
        human.setInfluence(((human.getInfluence() + job.getInfluence() + influenceA)));
        human.setProfessionalAssociates(human.getProfessionalAssociates() + professionalAssociatesA);
        human.setFriends(human.getFriends() + friendsA);
        human.setLooks(human.getLooks() + looksA);
        human.setWorshippers(human.getWorshippers() + worshippersA);
        if (human.getFriends() > countryOfUser.getRequiredFriends() || human.getOverAllWealth() > countryOfUser.getRequireedWealth()
                && human.getInfluence() > countryOfUser.getRequiredInfluence()) {
            human.setNeighborhood(countryOfUser.getRichNeighborHood());


        } else if (human.getFriends() == countryOfUser.getRequiredFriends() || human.getOverAllWealth() == countryOfUser.getRequireedWealth()
                && human.getInfluence() == countryOfUser.getRequiredInfluence()) {
            human.setNeighborhood(countryOfUser.getMiddleNeighborHood());

        } else {
            human.setNeighborhood(countryOfUser.getPoorNeighborHood());
        }

    }

    public void keepStatsUpToDate(Double overallWealthA, Double influenceAmountA, Jobs jobA, Countries countryA,
                                  Double taxA) {
        human.setOverAllwealth(overallWealthA);
        human.setInfluence(influenceAmountA);
        human.setJob(jobA);
        human.setCountries(countryA);
        tax = taxA;
        overallWealthTextView.setText(getString(R.string.overallWelathTextView_text) + "\n" + currencyFormat.format(human.getOverAllWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText(getString(R.string.influenceAmountTextView_String) + "\n" + influenceAmountString);
        jobTextView.setText(getString(R.string.getJob_text) + human.getJob());
        countryTextView.setText(getString(R.string.getCountry_text) + ":" + human.getCountryString());
        taxTextView.setText(getString(R.string.getTax_text) + ":" + currencyFormat.format(tax));
        calculateTheProgressBarPercentage(overallWealthA, influenceAmountA);
        /*neighbourhoodTextView.setText("Neighbourhood:"+human.getNeighborhood());
        userFriendsTextView.setText("Friends:"+human.getFriends());
        professionalAssociatesTextView.setText("Professional Associates:"+human.getProfessionalAssociates());
        looksTextView.setText("Attractiveness:"+human.getLooks());*/

    }


    public void updatingStateOfFamily(double wealthA, double influenceA) {
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


    public void idealLifeParameters(){
        informationalTextView.setText("Choose a job ");
        informationalTextView.setText("\"Choose a job \"Very Low Paying Jobs:(0)Begger (1)Vagrant" + ("\n") +
                "Low Paying Jobs that add greater Influence: (2)Intern" + ("\n") +
                "Average Paying Jobs with low Influence: (3)Packingboy (4)Firefighter (5) Banker" + ("\n") +
                "Medium Paying Jobs with High Influence: (6)Scientist (7)Independent" + ("\n") +
                "Highest Paying Jobs: (8)Business Owner (9)King (10)Sultan" + ("\n") +
                "Divine Jobs: (11)God" + ("\n"));

        final Spinner selectAFamily_spinnnerOne,selectACountry_spinnnerTwo;


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
        selectAFamily_spinnnerOne =(Spinner)convertView.findViewById(R.id.selectAFamily_spinnnerOne);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.familyTypes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // Apply the adapter to the spinner



        selectACountry_spinnnerTwo =(Spinner)convertView.findViewById(R.id.selectACountry_spinnnerTwo);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTwo.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        // Apply the adapter to the spinner
        selectAFamily_spinnnerOne.setAdapter(adapter);
        selectACountry_spinnnerTwo.setAdapter(adapterTwo);






        alertDialogidealLife.show();

    }

    public void worshippersFollow(boolean worshippersFollow) {

        if(worshippersFollow && tempNum !=age) {

            human.setWorshippers(human.getWorshippers()+100);
            System.out.println("You attracted 100 worshippers");
            tempNum++;

        }if(worshippersFollow && god){


            human.setWorshippers(human.getWorshippers()+1000);
            System.out.println("You attracted 1000 worshippers");


        }
    }

    public void getAJob() {
        //Scanner and the if statement will always be Loading for a wrong option while everything so if the user places anything outside the desired options then that user will be  placed back
        //to the top of case 1
        informationalTextView.setText("Get a job: Raises you overall wealth" + "\n" +
                "Raises the amount of professional Associates" + "\n" +
                "Raises your influence" + "\n" +
                "Raises your amount of friends slightly");

    }




}


