package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
//Buttons
    private Button changeCountryButton,selectAJobButton,schoolButton,workOnPhysicalAppearanceButton,socializeWithPeopleButton,continueButton;
//ProfileImages
    private ImageView profileImage,profileImageViewProfileDialog;
//Separate Dialogs
    private Dialog alertDialogProfile;

    private Human human;
    private int age = 0, schoolAttendanceAmount = 0, socialisingWithFriends = 0, workingOnPhysicalApp = 0;
    private int value, looks = 0, worshippers = 0, friends = 0, professionalAssociates = 0;
    private double wealth = 0.0, tax = 0.0;

    private Family family;
    private Countries countryOfUser;
    private Jobs job=Jobs.NOJOB;
    private FamilyMember mother, sister, brother, father;
    private String familyName, firstNamePart, lastNamePart,chainString="";
    Random rand = new Random();
    int randomNum = rand.nextInt((50 - 1) + 1);
    private Boolean banker = false, independent = false, businessowner = false, king = false, intern = false,shown=false,shownOne=false;
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
//This boolean is to keep track whether the user is done initializing their IDEAL Life
    boolean dialogShown=true;
//For selecting and image
    private static final int SELECT_PHOTO = 100;
    public String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.family_adult_mainpage_layout);

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

        continueButton=(Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (human.getOverAllWealth() < 10000000 && human.getInfluence() < 2000000) {
                    new Thread(new IDEALLifeProgram()).run();
                }
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
                /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                profileImage.setImageURI(photoPickerIntent.getData());*/
                // in onCreate or any event where your want the user to
                // select a file
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, SELECT_PHOTO);
                return false;
            }
        });

        //Progress Bars
        overallWealthProgressBar = (ProgressBar) findViewById(R.id.overallWealthProgressBar);
        influenceProgressBar = (ProgressBar) findViewById(R.id.influenceProgressBar);
        mHandler = new Handler();

        age_Turn_textView.setText("Age/Turn:" + Integer.toString(age));
        workingOnPhysicalAppTextView.setText(Integer.toString(workingOnPhysicalApp));
        socialisingWithFriendsTextView.setText( Integer.toString(socialisingWithFriends));
        schoolAttendanceAmountTextView.setText( Integer.toString(schoolAttendanceAmount));
         //informationalTextView.setText("Welcome and Good Luck!");



        buttonActivation(true);
        new Thread(new IDEALLifeProgram()).run();


    }

///->TOP RIGHT SIDE MENU FUNCTIONALITY
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

        }if(id==R.id.familyTutorial){
            profilePictureDialogFamilyTutorial();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
    }
///->


///->ALL 5 BUTTON FUNCTIONALITY IN LIST
    public void selectAJob() {
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

                professionalAssociates += 0;
                friends += 10;
               job=Jobs.BEGGER;


            }
        });

        //Vagrant//2
        final Button jobButtonVagrant = new Button(this);
        jobButtonVagrant.setHint("Vagrant");
        //layout.addView(jobButtonVagrant);
        jobButtonVagrant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 0;
                friends += 25;
                job=Jobs.VAGRANT;

            }
        });

        //Intern//3
        final Button jobButtonIntern = new Button(this);
        jobButtonIntern.setHint("Intern");
        //layout.addView(jobButtonIntern);
        jobButtonIntern.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 500;
                friends += 15;
                job=Jobs.INTERN;

            }
        });

        //PackingBoy//4
        final Button jobButtonPackingBoy = new Button(this);
        jobButtonPackingBoy.setHint("Packing Boy");
        //layout.addView(jobButtonPackingBoy);
        jobButtonPackingBoy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 50;
                friends += 10;
                job=Jobs.PACKINGBOY;

            }
        });

        //Firefighter//5
        final Button jobButtonFirefighter = new Button(this);
        jobButtonFirefighter.setHint("Firefighter");
        //layout.addView(jobButtonFirefighter);
        jobButtonFirefighter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 100;
                friends += 25;
                job=Jobs.FIREFIGHTER;

            }
        });

        //Banker//6
        final Button jobButtonBanker = new Button(this);
        jobButtonBanker.setHint("Banker");
        //layout.addView(jobButtonBanker);
        jobButtonBanker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                professionalAssociates += 200;
                friends += 50;
                job=Jobs.BANKER;

            }
        });

        //Scientist//7
        final Button jobButtonScientist = new Button(this);
        jobButtonScientist.setHint("Scientist");
        //layout.addView(jobButtonScientist);
        jobButtonScientist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 200;
                friends += 75;
                job=Jobs.SCIENTIST;

            }
        });

        //Independent//8
        final Button jobButtonIndependent = new Button(this);
        jobButtonIndependent.setHint("Independent");
        //layout.addView(jobButtonIndependent);
        jobButtonIndependent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 300;
                friends += 100;
                job=Jobs.INDEPENDENT;

            }
        });

        //Buisness Owner//9
        final Button jobButtonBusinessOwner = new Button(this);
        jobButtonBusinessOwner.setHint("Business Owner");
        //layout.addView(jobButtonBusinessOwner);
        jobButtonBusinessOwner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 500;
                friends += 200;
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
                professionalAssociates += 1000;
                friends += 500;
                job=Jobs.KING;

            }
        });

        //Sultan//11
        final Button jobButtonSultan = new Button(this);
        jobButtonSultan.setHint("Sultan");
        //layout.addView(jobButtonSultan);
        jobButtonSultan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 1500;
                friends += 1000;
                job=Jobs.SULTAN;

            }
        });

        //Omega//12
        final Button jobButtonOmega = new Button(this);
        jobButtonOmega.setHint("\u03A9" + "mega");
        //layout.addView(jobButtonOmega);
        jobButtonOmega.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 3000;
                friends += 2000;
                job=Jobs.OMEGA;

            }
        });
        //Make the AlertDialog Scrollable


        //If statements to show only certain Buttons
        if (age < 20 || schoolAttendanceAmount<1) {
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


                if (businessowner) {  layout.addView(jobButtonBusinessOwner);  }//BusinessOwner;
            }
            if (human.getCountry() == Countries.Heaven) {

                if (independent) {layout.addView(jobButtonIndependent);  }//Independent;


                if (king) {  layout.addView(jobButtonKing);  }//KING;


                if (king && sultan) { layout.addView(jobButtonSultan);  }//SULTAN

            }
        }

        final ScrollView scrollView = new ScrollView(this);
        scrollView.addView(layout);

        AlertDialog ad = new AlertDialog.Builder(this)

                .setMessage("Select A Job. \n Current Country:" + human.getCountry())
                .setTitle("IDEAL")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        new Thread(new IDEALLifeProgram()).run();

                    }
                })
                .setNeutralButton("Cancel", null)
                .setIcon(R.drawable.ic_stat_name)
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
        schoolAttendanceAmountTextView.setText(Integer.toString(schoolAttendanceAmount));
        //This String Variable is used to hold all of the messages in one string
        String chainText="";
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Cancel", null)

                .setMessage("Go a school:Raises the amount of professional Associates" + "\n" +
                        "Unlocks jobs" + "\n" +
                        "Raises the amount of friends")

                .setTitle("IDEAL:Going to School")
                .setIcon(R.drawable.ic_stat_name)
                .setCancelable(true)
                .create();


        //This Dialog is used to show which jobs were unlocked
        AlertDialog adNotifier = new AlertDialog.Builder(this)
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        wealth+=-2500.0 * human.getCountry().getMultiplier();
                        new Thread(new IDEALLifeProgram()).run();
                   }
                })
                .setTitle("IDEAL:Going to School")
                .setCancelable(true)
                .create();


        if ( schoolAttendanceAmount>=1) {
                begger = true;
                chainText+="You have unlocked the Beggar Job";

                vagrant = true;
                chainText+="\n"+"You have unlocked the Vagrant Job";
        }if( schoolAttendanceAmount>=2){
                intern=true;
                chainText+="\n"+"You have unlocked the Intern Job";
                packingboy=true;
                chainText+= "\n" + "You have unlocked the Packing boy Job";
                firefighter=true;
                chainText+="\n" + "You have unlocked the Firefighter Job";

        }if( schoolAttendanceAmount>=2&&socialisingWithFriends>=2 && workingOnPhysicalApp>=1) {
            banker = true;
            chainText += "\n" + "You have unlocked the Banker Job";
            scientist = true;
            chainText += "\n" + "You have unlocked the Scientist Job";
            independent = true;
            chainText += "\n" + "You have unlocked the Independent Job";
            firefighter = true;
            chainText += "\n" + "You have unlocked the Firefighter Job";

        }if( schoolAttendanceAmount>=2&&socialisingWithFriends>=3 && workingOnPhysicalApp>=1) {
            king = true;
            chainText += "\n" + "You have unlocked the King Job";

        }if(schoolAttendanceAmount>=3&&socialisingWithFriends>=3 &&workingOnPhysicalApp>=3){
                 sultan=true;
                chainText+="\n" + "You have unlocked the Sultan Job";

        }if( schoolAttendanceAmount>=4&&socialisingWithFriends>=4 &&workingOnPhysicalApp>=4) {
                god = true;
                chainText+="\n" + "You have unlocked the God Job";

        }
        //There should be at least 6 words in the chainText and if not then the user has not unlocked any job

        adNotifier.setMessage(chainText);
        adNotifier.setCancelable(true);
        ad.show();
        adNotifier.show();

    }
    public void workOnPhysical(){
        workingOnPhysicalApp++;
        workingOnPhysicalAppTextView.setText(Integer.toString(workingOnPhysicalApp));
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        wealth+=-100 * human.getCountry().getMultiplier();
                        new Thread(new IDEALLifeProgram()).run();

                    }
                })

                .setMessage(
                        "Your looks got increased by 1" + "\n" +
                                "The amount of worshippers got increased by 100" + "\n" +
                                "Your influence got increased by 1000" + "\n" +
                                "The amount of friends increased by 1000" + "\n" +
                                "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setIcon(R.drawable.ic_stat_name)
                .setCancelable(true)
                .create();
        ad.show();
      }
    public void socializeWithPeople(){
        socialisingWithFriends++;
        socialisingWithFriendsTextView.setText(Integer.toString(socialisingWithFriends));
        AlertDialog ad = new AlertDialog.Builder(this)
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        wealth+=-50 * human.getCountry().getMultiplier();
                        new Thread(new IDEALLifeProgram()).run();

                    }
                })

                .setMessage("The amount of worshippers got increased by 100" + "\n" +
                        "Your influence got increased by 1000" + "\n" +
                        "The amount of friends increased by 1000" + "\n" +
                        "Your professional Associates got increased by 1000" + "\n" +
                        "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setIcon(R.drawable.ic_stat_name)
                .setCancelable(true)
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
        alertDialog.setIcon(R.drawable.ic_stat_name);
        alertDialog.setTitle("Choose a Country");

        final ListView countrieslistView = (ListView) convertView.findViewById(R.id.countriesListXML);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, countries);
        //Disable certain Items on the Countries List View
        //countrieslistView.getChildAt(9).setEnabled(false);
        //countrieslistView.getChildAt(9).setBackgroundColor(Color.parseColor("#d3d3d3"));

        if(age>1) {
            alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
        }
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
                   if(age>1) {
                        keepStatsUpToDate(-(human.getCountry().getTaxes() + 100 * human.getCountry().getMultiplier()), 0, job, human.getCountry().getTaxes(), 0, 0, 0, 0);
                        new Thread(new IDEALLifeProgram()).run();
                    }

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "A Country is needed", Toast.LENGTH_LONG).show();
                    selectACountry();
                }

            }
        })

                .setCancelable(false);

        countrieslistView.setAdapter(adapter);
        alertDialog.show();


    }
///->

///->UPDATING STATS
   private void calculateTheProgressBarPercentage(Double overallWealthA, int influenceAmountA) {//The Influence Progress Bar Percentage is not very accurate...May not be a big issue

        /*
        Calculating the percentage of the wealth/Influence first and then figuring out if they are <=0 before updating the actual progress Bar...
        So that no number less than Zero is placed into the progressBar and potentially crashing the program
         */
    //Calculate the OverAllWealth Percentage
    final double overallwealthvalue = ((overallWealthA / maxOverallWealth) * 100);//Complete the whole percentage equation and then convert number to Int for the Progress Bars
    //Calculate the influence Percentage
    final double influencevalue = ((influenceAmountA * 1.0) / (maxInfluence * 1.0) * 100);
    //Don't need to get the product of (human.getOverAllWealth() / maxOverallWealth) and 100 because the percentFormat already multiplies product

    //This will allow the progress Bars to decrease in value //DECREMENT PROGRESSBAR
    if(overallWealthProgressBar.getProgress()> overallwealthvalue){
        overallWealthProgressBar.setProgress((int) overallwealthvalue);
        overallWealthPercentageTextView.setText((int) overallwealthvalue + "%");


    }
    if(influenceProgressBar.getProgress()> influencevalue){
        influenceProgressBar.setProgress((int) influencevalue);
        influencePercentageTextView.setText((int) influencevalue + "%");

    }

    //INCREMENT PROGRESSBAR
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

    System.out.println("Influence Parameter:" + influenceAmountA + "InfluenceProgress" + influencevalue + " WealthProgress " + overAllWealthProgress + " overallwealthvalue: " + overallwealthvalue + " influencevalue " + influencevalue);

}
    //Need to update for the Price to Move to another country
    public void keepStatsUpToDate(Double overallWealthA, int influenceAmountA, Jobs jobA, /*Countries countryA,*/
                                  Double taxA, int looksA, int worshippersA, int friendsA, int professionalAssociatesA) {

        human.setJob(jobA);
        human.setIncome(jobA.getIncome());
        human.setOverAllwealth((human.getOverAllWealth() + jobA.getIncome() + overallWealthA));
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
        jobTextView.setText(getString(R.string.getJob_text) + ":" +"\n"+ human.getJob());
        countryTextView.setText(getString(R.string.getCountry_text) + ":" +"\n"+ human.getCountryString());
        taxTextView.setText(getString(R.string.tax_text) + ":" +"\n"+ currencyFormat.format(tax));


        //This "if" statement is going to see whether our percentage function will give something less than 1...which the progress bars can no handle
        if (overallWealthTextView.getText().length() <= 5 ) {
            calculateTheProgressBarPercentage(1.0, human.getInfluence());


        }
        if (influenceTextView.getText().length() <= 4) {
            calculateTheProgressBarPercentage(human.getOverAllWealth(), 1);

        }

        calculateTheProgressBarPercentage(human.getOverAllWealth(), human.getInfluence());

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
///->

///->IDEAL APPLICATION USER INITIALIZATION CODE
    public void init() {

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
                        if (dialogShown) {
                            new Thread(new IDEALLifeProgram()).run();
                        }
                        /*try {
                            tutorialWithFamily();

                        } catch (NullPointerException e) {
                            Toast.makeText(MainActivity.this, "Ooops,something went wrong.Let's try again!",
                                    Toast.LENGTH_LONG).show();
                            tutorialWithFamily();
                        }*/
                        dialogShown = false;

                    }
                })
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
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        firstNamePart = userNameFirst.getText().toString();
                        playerNameTextView.setText(firstNamePart);
                        lastNamePart = userNameFamily.getText().toString();


                    }
                })
                .setCancelable(false)
                .setView(layout)
                .create();

        ad.show();
    }
    //SELECT COUNTRY IS BOTH INITIALIZATION AND A BUTTON FUNCTIONALITY
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
                                        //Constructor being called:public Family( double familyWealthA,double influenceA,int friendsA)NOTE:This constructor was upgraded because of the family update method calling the indiviual family members
                                        father = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                        mother = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                        sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                        brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
                                        //Cusom Family Constructor
                                        //public Family( double familyWealthA,int influenceA,int friendsA,String familyNameA,FamilyMember brotherA, FamilyMember sisterA, FamilyMember fatherA, FamilyMember motherA){
                                        family = new Family(Double.parseDouble(wealthAmountEditText.getText().toString()), Integer.parseInt(influenceAmountEditText.getText().toString()),
                                                Integer.parseInt(friendsAmountEditText.getText().toString()), familyName, brother, sister, father, mother);

                                        keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                                tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                        human.setFriends(Integer.parseInt(friendsAmountEditText.getText().toString()));
                                        //Null point
                                        Toast.makeText(MainActivity.this, "You have selected a Custom Family" + family,
                                                Toast.LENGTH_LONG).show();
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
                                            mother = new FamilyMember(false, familyName, Jobs.BANKER, countryOfUser, randomNum * 250, (int) (randomNum * 1.5));
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

                                    Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString() + family,
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
///->

///->GETTING AND SETTING THE IMAGE FOR THE APPLICATION
    //Code for getting and shrinking picture selected my the user
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        try {
            // When an Image is picked
            if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK
                    && null != imageReturnedIntent) {
                // Get the Image from data

                Uri selectedImage = imageReturnedIntent.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                selectedImagePath = cursor.getString(columnIndex);
                cursor.close();
                //ImageView imgView = (ImageView) findViewById(R.id.profileImageView);
                // Set the Image in ImageView after decoding the String
                profileImage.setImageBitmap(BitmapFactory
                        .decodeFile(selectedImagePath));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
//->

///->Family and Player Profile Dialogs
   public void profilePictureDialog() {


    alertDialogProfile = new Dialog(MainActivity.this, android.R.style.Theme_Black);
    //
       /*Window window = alertDialogProfile.getWindow();
       window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       window.setGravity(Gravity.CENTER);*/
    //AlertDialog.Builder alertDialogProfile = new AlertDialog.Builder(MainActivity.this);

    //Call the XML List view and set it with  the countries array
    //LayoutInflater inflater = getLayoutInflater();
    //View convertView = inflater.inflate(R.layout.profileimagedialog, null);
    //alertDialogProfile.setView(convertView);
    alertDialogProfile.setContentView(R.layout.profileimagedialog);

    //keepStatsUpToDate(human.getOverAllWealth(), human.getInfluence(), human.getJob(),human.getCountry(),tax);
    //userNameTextView.setText(firstNamePart);
    alertDialogProfile.setTitle(firstNamePart + " " + lastNamePart + "'s Profile");


    profileImageViewProfileDialog=(ImageView) alertDialogProfile.findViewById(R.id.profileImageViewProfileDialog);
    Bitmap bitmap = ((BitmapDrawable)profileImage.getDrawable()).getBitmap();
    profileImageViewProfileDialog.setImageBitmap(bitmap);

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

        alertDialogProfile = new Dialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog);

        /*Window window = alertDialogProfile.getWindow();
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);*/

        alertDialogProfile.setContentView(R.layout.profileimagedialog);

        alertDialogProfile.setTitle(lastNamePart + "'s Family Profile");

        profileImageViewProfileDialog=(ImageView) alertDialogProfile.findViewById(R.id.profileImageViewProfileDialog);
        Bitmap bitmap = ((BitmapDrawable)profileImage.getDrawable()).getBitmap();
        profileImageViewProfileDialog.setImageBitmap(bitmap);

        playerNameTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.playerNameTextViewprofileDialog);
        playerNameTextViewprofileDialog.setText(lastNamePart);

        overAllWealthTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.overAllWealthTextViewprofileDialog);
        overAllWealthTextViewprofileDialog.setText("Wealth:\n" + currencyFormat.format(family.getFamilyWealth()));

        influenceTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText("Influence:\n" + family.getFamilyInfluence());

        jobTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setEnabled(false);

        countryTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText("Country:" + family.getFamilyCountry());

        neighbourhoodTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText("Neighbourhood:" + family.getFamilyNeighborhood());

        taxTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog);
        taxTextViewprofileDialog.setText("Tax:" + currencyFormat.format(family.getFamilyCountry().getTaxes()));

        userfriendsTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText("Friends:" + family.getFamilyFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setEnabled(false);

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText("Worshippers:" + family.getFamilyWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setEnabled(false);

      alertDialogProfile.show();


    }
///->

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

    public void chancesOfLife() {
        ///If you want to test any specific chance of life just place in the case number into switch bracket..the program will only run that switch
        //7randomNum
        switch (randomNum) {

            case 0:
                //Move to another country with higher or lower income
                //This affects your wealth with the family in the tutorial
                //This will affect your overall wealth in th later game
                if(age>20) {
                    chainString += "\n" + "You decided to move;so here is the  opportunity to choose a different country!";
                    selectACountry();
                }else{
                    chainString += "\n" + "Your Family got an influence boost";
                    influence+=1000;
                }


                break;
            case 1:

                //ad.setMessage("2");
                if (age < 20) {
                    if (family.getFamilyFriends() > 1000) {
                        chainString+="Your family is very famous and this allows you to earn $500";
                        wealth+= 500;
                        chainString+="\n"+"The Family's wealth is $" + family.getFamilyWealth();

                        //CharDetails();
                    } else if (family.getFamilyFriends() > 500) {
                        chainString+="\n"+"Your family is Famous and this allows you to earn $100";
                        wealth+= 100;
                        chainString+="\n"+"Your wealth is $" + family.getFamilyWealth();

                        //CharDetails();
                    } else {
                        chainString+="\n"+"Your family is not very popular;it is beneficial to have more friends!!";
                    }

                } else {
                    if (human.getFriends() > 1000) {
                        chainString+="\n"+"You are very famous and this allows you to earn $500";
                        wealth+=500;
                        chainString+="\n"+"Your wealth is $" + human.getOverAllWealth();

                        //CharDetails();
                    } else if (human.getFriends() > 500) {
                        chainString+="\n"+"You are famous  and this allows you to earn $100";
                        wealth+= 100;
                        chainString+="\n"+"Your wealth is $" + human.getOverAllWealth();

                        //CharDetails();
                    } else {
                        chainString+="\n"+"You are not very popular;it is beneficial to have more friends!!";
                    }

                }


                break;
            case 2:

                // ad.setMessage("3");
                if (human.getInfluence() > 100000 || human.getInfluence() + family.getFamilyInfluence() > 100000) {
                    chainString+="\n"+"Your family has a high influence level and is able to get you to the best schools and mentors" + "\n" + "You have will have access to the intern job "
                            + "\n" + "You have will have access to the Banker job "
                            + "\n" + "You have will have access to the Business Owner job ";

                    intern = true;
                    banker = true;
                    businessowner = true;

                } else {
                    chainString+="\n"+"Your family's influence is not very high;try increasing it!!";
                }


                break;
            case 3:

                // ad.setMessage("4");
                if (human.getFriends() > 100000 || human.getFriends() + family.getFamilyFriends() > 100000) {
                    chainString+="\n"+"Having many friends make ways for more friends.You gained 500 friends";
                   friends+=500;
                    //CharDetails();

                } else if (human.getFriends() > 1000 || human.getFriends() + family.getFamilyFriends() > 1000) {
                    chainString+="\n"+"You don't have much friends but you still get a bonus.You gained 100 friends";
                   friends+=100;

                } else {
                    chainString+="\n"+"You do not have enough friends to access this bonus";

                }

                break;
            case 4:

                // ad.setMessage("5");
                chainString+="\n"+"A storm came over and destroyed your car.You lose some of your money in the process of replacing it";

                if (age > 20) {
                    wealth+= - 10000;
                    chainString+="\n"+"You got charged:10000";
                    //CharDetails();


                } else {
                    wealth += -10000;
                    updatingStateOfFamily(wealth, 0);
                    chainString+="\n"+"Your family got charged $10000";


                }

                break;
            case 5:
                // ad.setMessage("6");

                if (human.getLooks() > 1 && human.getLooks() < 5) {
                    chainString+="\n"+"You are not very good looking and people do not resonate with you very well.What a shallow world where people judge based on your looks \n You gain nothing";


                } else if (human.getLooks() == 5) {
                    chainString+="\n"+"You are average looking so very few people take you on face value.What a shallow world where people judge based on your looks \n You gain $100 \n" +
                            "You gain 100 influence" +
                            "\n You gain 100 friends";


                    wealth+= 100;
                   influence+=100;
                  friends+=100;


                } else if (human.getLooks() < 15) {
                    chainString+="\n"+"People automatically love you at face value.What a shallow world where people judge based on your looks \n You gain $500 \n" +
                            "You gain 500 influence" +
                            "\n You gain 500 friends";

                    wealth+=500;
                   influence+= 500;
                   friends+= 500;

                }
                //CharDetails();
                break;
            case 6:
                // ad.setMessage("7");
                if (god && age > 20) {
                    job=Jobs.GOD;
                    worshippersFollow = true;
                    heavenBoolean = true;
                    chainString+="\n"+"You have surpass the earthly domain and is ready to create your own heaven"+"\n"+"You have also gained access to heaven";


                } else if (age > 20) {
                    chainString+="\n"+"You inherited Godly talent! You begin to attract worshippers";
                    worshippersFollow = true;
                } else {
                    chainString+="\n"+"You need to be outside the family tutorial to access this benefit";
                }
                 break;

            case 7:
                //genieDoSomething();
                informationalTextView.setText("This is the genie");
                break;

            case 8:
                // ad.setMessage("9");
                if (age > 20) {
                    wealth+= 500000;
                    chainString+="\n"+"You won the lottery so you will gain $500000";

                } else {
                    wealth+= 500000;
                    updatingStateOfFamily(wealth, 0);
                    chainString+="\n"+"Your family won the lottery,gained $500000";
                    wealth = 0;
                }

                break;


            case 9:
                // ad.setMessage("10");
                chainString+="\n"+"Your recent trip to IDEAL Center of Academics added professional Associates to call onto later in life";
                professionalAssociates= 500;
                //CharDetails();


                break;

            case 10:

                // ad.setMessage("11");
                chainString+="\n"+"You get a boost in influence, wealth, and friends!You are very lucky";
               wealth= 500000;
               friends=50000;
               influence=5000;
               

                break;

            case 11:

                // ad.setMessage("12");
                chainString+="\n"+"You get a boost in professional associates, looks, and worshippers !You are very lucky";
               professionalAssociates= 5000;
               looks= 2;
               worshippers= 5000;

                break;

            case 12:

                // ad.setMessage("13");
                chainString+="\n"+"You lose in  influence, wealth, and friends!You are very unlucky";
                wealth=- 5000;
                friends= - 500;
                influence= - 500;
                break;

            case 13:
                chainString+="\n"+"You lose in professional associates, looks, and worshippers !You are very unlucky";
                 professionalAssociates= - 500;
                looks= - 2;
                worshippers= 50;
                    break;


        }


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
                            //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, temjobA,
                                    //tax,human.getLooks(),human.getWorshippers(),human.getFriends(),human.getProfessionalAssociates());
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "You forfeit your wish",
                                    Toast.LENGTH_LONG).show();
                            chancesOfLife();///I may need this..I am not sure right now though


                            //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, temjobA,
                                    //tax, human.getLooks(), human.getWorshippers(), human.getFriends(), human.getProfessionalAssociates());
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
            //keepStatsUpToDate(overallWealthDefault, influenceAmountDefault, Jobs.NOJOB,
                    //tax, human.getLooks(), human.getWorshippers(), human.getFriends(), human.getProfessionalAssociates());
            informationalTextView.setText("You get a slight boost in wealth, worshippers,friends,looks,professional associates, and influence");
        }
    }

    public void selectingAWish(int numberSelection) {
        //looks=0,worshippers=0,friends=0,professionalAssociates=0,influence=0
      
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
                wealth = 100000.0;
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
                        job = Jobs.BANKER;
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
                        businessowner = true;
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
                //System.out.print("\nYou are now a(n) " + tempJobA.getName() + ("\n"));
                //keepStatsUpToDate(job, 0, 0, 0, 0, 0);
                //CharDetails();
                //informationalTextView.setText("\n");

                break;
            case 0:
                //Become Omega
                omega = true;
                job = Jobs.OMEGA;
                heavenBoolean = true;
                informationalTextView.setText("You have also gained access to heaven");
      
        }

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

///->Two Different Stages of IDEAL
    public void tutorialWithFamily(){

            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }



    }
    public void grownUpHuman(){
               //First alertDialog for the Tutorial
              if(human.getOverAllWealth()< 10000000 && human.getInfluence()<2000000) {


            ///This is so that the user will make a decision  happens every four turns and NOT every turn
            /*if ((age % 4) == 0) {
                //-->makeLifeDecisions();
                //countries.setTaxes(countries.getMultiplier() * countries.getTaxes());
                buttonActivation(true);

            }else{
                buttonActivation(false);
            }*/
            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }


            System.out.println("Turn: " + age + "\n" + "|Adult" + "|" + human.getOverAllWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes());


            //Once the player reaches 100000 worshippers, the player will become a god
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
            if(human.getWorshippers()>100000) {
                worshippersActivation=true;

                //job = Jobs.GOD;
               job= Jobs.GOD;
                heavenBoolean=true;
                chainString+="You have earned enough worshippers to become a God.Rule over all!!" + "\n" + "You have also gained access to heaven";
            }


            //Once the player reaches 100000 in influence, the player will get paid $100000 every turn
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
            if(human.getInfluence()>100000|| human.getProfessionalAssociates()>=10000 && !influencActivation
                    ||human.getFriends()>=100000){
                influencActivation=true;
                chainString+="\n" +"You have gained 100000 in wealth";
                chainString+="\n" + "You have gained enough power,you will now gain a steady source of money";
                wealth += 100000;

            }

        }else {
            ///The game is finished at this point
             chainString+="\n" +"You have created your ideal life at age:" + age + "  CONGRATULATIONS";
            buttonActivation(false);

        }




    }
///->

///->Calling a Thread for the application
    public class IDEALLifeProgram implements Runnable{

    @Override
    public void run(){

               try {
                   if(age==0) {
                       init();
                       makeYourName();
                       selectAFamilyType();
                       selectACountry();

                   } else if (age < 20 && age > 0) {
                       informationalTextView.setText("IDEAL:Tutorial With Family/Initial State" + "\n" + "This is the Family View where the first 20 years will be determined for you.");
                       //Deactivate the buttons
                       //buttonActivation(false);
                       if(!shown) {
                           //To display to the use that the application is in the Tutorial State
                           informationalTextView.setText("This is the Family View where the first 20 years will be determined for you.\n Now that you have a family; it is time to start your new IDEAL life" + "\n"
                                   + "Every turn represents an age" + "\n"
                                   + "Once age 20 is reached; the player will be removed from the family and has to choose a starting location");
                          //Disable the Neutral button so that the user don't end up in a state of nothing going on

                           //Disables a alertDialog Button, though method must be called after dialog is shown

                           shown=true;

                       }else{
                           informationalTextView.setText("Turn: " + age + "\n" + "|Family" + "|" + family.getFamilyWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes()
                                   + "\n" + chainString);
                           //Reset the chain so that previous messages are removed
                           chainString="";

                       }

                    
                       tutorialWithFamily();
                       updatingStateOfFamily(wealth*1.0,influence);
                       //Reset stats so that there are no further stacking up
                       wealth=0;
                       influence=0;
                       job=human.getJob();
                       looks=0;
                       worshippers=0;
                       friends=0;
                       professionalAssociates=0;

                   }else{

                      if(!shownOne) {
                          informationalTextView.setText("IDEAL:Adult Life" + "\n" + "You are now an adult and will have to work on your own now to secure you IDEAL Life" + "\n"
                                  + "If you can reach $10,000,000 in wealth and 2,000,000 in influence;you would have won the game!!"
                                  + "\n There are also many other features to unlock in the game"
                                  + "\n Create your IDEAL Life");

                           shownOne = true;
                       }else{
                          informationalTextView.setText("Adult||Initial Wealth:" + human.getInitialWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes() + "|| Neighborhood: " + human.getNeighborhood()
                                  + "\n" + chainString);
                           //Reset the chain so that previous messages are removed
                           chainString="";
                                    }

                       grownUpHuman();
                       keepStatsUpToDate(wealth*1.0,influence,job,human.getCountry().getTaxes(),looks,worshippers,friends,professionalAssociates);
                       //Reset stats so that there are no further stacking up
                       wealth=0;
                       influence=0;
                       job=human.getJob();
                       looks=0;
                       worshippers=0;
                       friends=0;
                       professionalAssociates=0;
                   }


            } catch (Throwable t) {
               System.out.println("halted due to an error" + t);
            }

        age++;
        age_Turn_textView.setText("Age: " + age);


        }

}
    //These methods is for when the application is paused or temporally closed
///->
    public void onResume() {
        super.onResume();
        //mBackgroundSound.execute(null);
    }

    public void onPause() {
        super.onPause();
        //mBackgroundSound.cancel(true);
    }

    public void buttonActivation(boolean activate){
        changeCountryButton.setEnabled(activate);

        selectAJobButton.setEnabled(activate);

        schoolButton.setEnabled(activate);

        workOnPhysicalAppearanceButton.setEnabled(activate);

        socializeWithPeopleButton.setEnabled(activate);
    }

    }

