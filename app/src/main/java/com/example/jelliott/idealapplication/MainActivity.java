package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener  {

    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static double maxOverallWealth = 10000000.0;
    private static int maxInfluence = 2000000;
    //private int influenceAmountDefault = 500000; //May use at a later date
    //private double overallWealthDefault = 5000000.0;
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
    private TextView objectivesNavigationTextView, wealthGoalNavigationTextView, influenceGoalNavigationTextView;
    //For the Progress Bars
//private static final int PROGRESS = 0x1;//May use
    private Handler mHandler;
    private int mProgressStatusOverAllWealth = 0, mProgressStatusInfluence = 0;
    private ProgressBar overallWealthProgressBar;
    private ProgressBar influenceProgressBar;
    //Health
    private TextView healthTextView;
    private ProgressBar healthProgressBar;
//Buttons
    private Button changeCountryButton,selectAJobButton,schoolButton,workOnPhysicalAppearanceButton,socializeWithPeopleButton,continueButton;
//ProfileImages
    private ImageView profileImage,profileImageViewProfileDialog;
//Separate Dialogs
    private Dialog alertDialogProfile;
    private View header;
    private Human human;
    private int age = 1, schoolAttendanceAmount = 0, socialisingWithFriends = 0, workingOnPhysicalApp = 0 , wishes = 0;
    private int looks = 0, worshippers = 0, friends = 0, professionalAssociates = 0;
    private double wealth = 0.0, tax = 0.0;
    private Button countryButtonIrada;//Irada
    private Button countryButtonItican;//Itican
    private Button countryButtonAlbaq;//Albaq
    private Button countryButtonTrinentina;//Trinentina
    private Button countryButtonAlbico;//Albico
    private Button countryButtonUgeria;//Ugeria;
    private Button countryButtonPortada;  //Portada;
    private Button countryButtonKuwador;//Kuwador;
    private Button countryButtonUkrark;  //Ukrark;
    private Button countryButtonRany;  //Rany;
    private Button countryButtonHeaven; //Heaven
    private Button jobButtonBeggar; //Begger
    private Button jobButtonVagrant;//Vagrant
    private Button jobButtonIntern;//Intern
    private Button jobButtonPackingBoy;//PackingBoy
    private Button jobButtonFirefighter;//Firefighter
    private Button jobButtonBanker;//BANKER;
    private Button jobButtonScientist;  //Scientist;
    private Button jobButtonBusinessOwner;//BusinessOwner;
    private Button jobButtonIndependent;  //Independent;
    private Button jobButtonKing;  //KING;
    private Button jobButtonSultan; //SULTAN
    private Button jobButtonGod;//GOD
    private Button jobButtonOmega;  //OMEGA
    private Family family;
    private Countries countryOfUser;
    private Jobs job=Jobs.NOJOB;
    private FamilyMember mother, sister, brother, father;
    private String familyName="", firstNamePart, lastNamePart,chainString="";
    private Random rand = new Random();
    private int randomNum = rand.nextInt((50 - 1) + 1);
    private Boolean banker = false, independent = false, businessowner = false, king = false, intern = false,shown=false,shownOne=false,genie=false/*Genie boolean will only be used in the jobButton command which will allow the user to choose any job*/;
    private Boolean begger = false;
    private Boolean vagrant = false;
    private Boolean packingboy = false;
    private Boolean firefighter = false;
    private Boolean scientist = false;
    private Boolean god = false;
    private Boolean sultan = false;
    private Boolean omega = false;
    private Boolean worshippersFollow = false;
    private Boolean influencActivation = false;
    private Boolean heavenBoolean = false;
    private int level = 1;
    private final ViewGroup nullParent = null;//This is to remove warning on the convertView
    private ActionBarDrawerToggle actionBarDrawerToggle;
   //For selecting and image
   private static final int SELECT_PHOTO = 100, SELECT_LEVEL = 101;
    //Saving Data
    private String file_name = "dataOne";
    private boolean reset =false;
    private boolean actionItemTaken= false;//If an Action item was taken right before the User closes the game then it needs to be saved to determine


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.family_adult_mainpage_layout);
        //setContentView(R.layout.activity_main);
        //Before ANYTHING CREATE A HUMAN TO SET THE BASIC VARIABLES OF A HUMAN
            human = new Human();
            family =new Family();
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
                    actionItemTaken=true;
                    saveUserProfile();
                    selectACountry();
                }
            });
            //Select A Job Button
            selectAJobButton = (Button) findViewById(R.id.jobButton);
            selectAJobButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    selectAJob();
                }
            });

            selectAJobButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    actionItemTaken=true;
                    saveUserProfile();
                    availableJobs();
                    return false;
                }
            });
            //Go To School Button
            schoolButton = (Button) findViewById(R.id.schoolButton);
            schoolButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    actionItemTaken=true;
                    saveUserProfile();
                    goToSchool();
                }
            });
            //Work on Physical Appearance
            workOnPhysicalAppearanceButton = (Button) findViewById(R.id.physicalButton);
            workOnPhysicalAppearanceButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    actionItemTaken=true;
                    workOnPhysical();
                }
            });
            //Socialize with People
            socializeWithPeopleButton = (Button) findViewById(R.id.socializeButton);
            socializeWithPeopleButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    actionItemTaken=true;
                    saveUserProfile();
                    socializeWithPeople();
                }
            });

            continueButton = (Button) findViewById(R.id.continueButton);
            continueButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    age++;
                    age_Turn_textView.setText("Age: " + age);
                    if (human.getOverAllWealth() < maxOverallWealth && human.getInfluence() < maxInfluence) {
                        IDEALLifeProgram();
                        if(age>=20) {actionItemTaken = false; }//This should only be active at and after age 20
                    } else {
                        actionItemTaken=true;
                        ///The game is finished at this point
                        informationalTextView.setText("You have created your ideal life at age:" + age + "  CONGRATULATIONS");
                        //Disable everything
                        continueButton.setEnabled(false);
                        //continueButton.setVisibility(View.INVISIBLE);
                        buttonActivation(false);
                        //countryButtonActivation(false,false,false,false,false,false,false,false,false,false,false);
                        //jobButtonActivation(false,false,false,false,false,false,false,false,false,false,false,false,false);
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

            //Health ProgressBar
            healthProgressBar = (ProgressBar) findViewById(R.id.healthProgressBar);
            healthTextView = (TextView) findViewById(R.id.healthTextView);
            healthProgressBar.setProgress(100);
            //Health ProgressBar
            mHandler = new Handler();
            age_Turn_textView.setText("Age/Turn:" + Integer.toString(age));
            workingOnPhysicalAppTextView.setText(Integer.toString(workingOnPhysicalApp));
            socialisingWithFriendsTextView.setText(Integer.toString(socialisingWithFriends));
            schoolAttendanceAmountTextView.setText(Integer.toString(schoolAttendanceAmount));

////------------------------------------------------------------------------------------------->//Drawer
        initInstancesDrawer();
////------------------------------------------------------------------------------------------------------->
        buttonActivation(false);
        readFromFile();
        IDEALLifeProgram();
    }

    private void gridViewOnCreate() {
//        Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
        startActivityForResult(intent, SELECT_LEVEL);
    }
///->TOP RIGHT SIDE MENU FUNCTIONALITY
///----------------------------------->Drawer
private void initInstancesDrawer() {

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final DrawerLayout androidDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_design_support_layout);
    actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, androidDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    androidDrawerLayout.addDrawerListener(actionBarDrawerToggle);
    final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
    //Objectives for Navigation_header Layout
    header = navigationView.getHeaderView(0);
    objectivesNavigationTextView = (TextView) header.findViewById(R.id.objectivesNavigationTextView);
    wealthGoalNavigationTextView = (TextView) header.findViewById(R.id.wealthGoalNavigationTextView);
    influenceGoalNavigationTextView = (TextView) header.findViewById(R.id.influenceGoalNavigationTextView);
    objectivesNavigationTextView.setText("Level:" + level);
    wealthGoalNavigationTextView.setText(currencyFormat.format(maxOverallWealth));
    influenceGoalNavigationTextView.setText(Integer.toString(maxInfluence));
    //TextViews on Navigation Slider
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.mainGame_menu_Item:
                    Toast.makeText(getApplicationContext(), "You are already on the Main Activity", Toast.LENGTH_SHORT).show();
                    androidDrawerLayout.closeDrawers();
                    break;
                case R.id.level_menu_Item:
                    Toast.makeText(getApplicationContext(), "Levels", Toast.LENGTH_SHORT).show();
                    gridViewOnCreate();
                    androidDrawerLayout.closeDrawers();
                    break;
                case R.id.settings_menu_Item:
                    Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                    init();
                    androidDrawerLayout.closeDrawers();
                    break;
                case R.id.about_menu_Item:
                    Toast.makeText(getApplicationContext(), "About IDEAL", Toast.LENGTH_SHORT).show();
                    androidDrawerLayout.closeDrawers();
                    break;

            }
            return true;
        }
    });

    assert getSupportActionBar() != null;//Make sure the supportBar return not null
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

///------------------------------------->
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return super.onOptionsItemSelected(item);
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            init();
        }
        if (id == R.id.familyTutorial) {
            profilePictureDialogFamilyTutorial();
        }
        if (id == R.id.action_reset) {
            reset = true;
            calculateTheProgressBarPercentage(0.0, 0);
            saveUserProfile();
            readFromFile();
            shown = false;//Display text for Family Mode and Adult Mode in Game
            shownOne = false;
            IDEALLifeProgram();
            informationalTextView.setText("Press the Continue Button to Start a New Game");
           /* File dir = getFilesDir();//This Reset works but causing two instances of the AsynTask to play...Not Good
            File file = new File(dir, file_name);
            boolean deleted = file.delete();
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);*/
        }
        return true;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
    }
///->

///->ALL 5 BUTTON FUNCTIONALITY IN LIST
    private void selectAJob() {
        final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        dialog.setContentView(R.layout.countrieslayout);
        dialog.setTitle("Select a Job");
        //Initializing Buttons
        final Button selectCountryButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button cancelCountrySelectionButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Initializing Text Views
        final TextView countryDetailTitle = (TextView) dialog.findViewById(R.id.countryDetailTitle);//Define the Text Box
        countryDetailTitle.setVisibility(GONE);
        final TextView jobDetailTitle = (TextView) dialog.findViewById(R.id.countryDetailTax);//Define the Text Box
        jobDetailTitle.setText(getString(R.string.getJob_text));
        final TextView jobDetailIncome = (TextView) dialog.findViewById(R.id.countryDetailRequireWealth);//Define the Text Box
        jobDetailIncome.setText(getString(R.string.income_String) );
        final TextView jobDetailInfluence = (TextView) dialog.findViewById(R.id.countryDetailRequiredInfluence);//Define the Text Box
        jobDetailInfluence.setText(getString(R.string.influenceAmountTextView_String));
        final TextView countryDetailRequireFriends = (TextView) dialog.findViewById(R.id.countryDetailRequireFriends);//Define the Text Box
        countryDetailRequireFriends.setVisibility(GONE);

        //Beggar//1
        jobButtonBeggar =(Button) dialog.findViewById(R.id.buttonOne);
        jobButtonBeggar.setText(Jobs.BEGGER.getName());
        //jobButtonBeggar.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.signatureColorOne));
        jobButtonBeggar.setBackgroundResource(R.drawable.button_style);
        jobButtonBeggar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 0;
                friends += 10;
               job=Jobs.BEGGER;
            /*private void jobButtonActivation(boolean beggerA,boolean vagrantA,boolean internA,boolean packingboyA,boolean firefighterA,boolean bankerA,boolean scientistA
                    ,boolean businessowerA,boolean independentA, boolean kingA, boolean sultanA,boolean godA,boolean omeageA){*/
                jobButtonActivation(false,true,true,true,true,true,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.BEGGER.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.BEGGER.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.BEGGER.getInfluence()));
                        }
        });
        //Vagrant//2
        jobButtonVagrant = (Button) dialog.findViewById(R.id.buttonTwo);
        jobButtonVagrant.setText(Jobs.VAGRANT.getName());
        jobButtonVagrant.setBackgroundResource(R.drawable.button_style);
        jobButtonVagrant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 0;
                friends += 25;
                job=Jobs.VAGRANT;
                jobButtonActivation(true,false,true,true,true,true,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.VAGRANT.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.VAGRANT.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.VAGRANT.getInfluence()));
            }
        });
        //Intern//3
       jobButtonIntern = (Button) dialog.findViewById(R.id.buttonThree);
        jobButtonIntern.setText(Jobs.INTERN.getName());
       jobButtonIntern.setBackgroundResource(R.drawable.button_style);
       jobButtonIntern.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 500;
                friends += 15;
                job=Jobs.INTERN;
                jobButtonActivation(true,true,false,true,true,true,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.INTERN.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.INTERN.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.INTERN.getInfluence()));
            }
        });
        //PackingBoy//4
        jobButtonPackingBoy = (Button) dialog.findViewById(R.id.buttonFour);
        jobButtonPackingBoy.setText(Jobs.PACKINGBOY.getName());
        jobButtonPackingBoy.setVisibility(GONE);
        jobButtonPackingBoy.setBackgroundResource(R.drawable.button_style);
        jobButtonPackingBoy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 50;
                friends += 10;
                job=Jobs.PACKINGBOY;
                jobButtonActivation(true,true,true,false,true,true,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.PACKINGBOY.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.PACKINGBOY.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.PACKINGBOY.getInfluence()));
            }
        });
        //Firefighter//5
        jobButtonFirefighter = (Button) dialog.findViewById(R.id.buttonFive);
        jobButtonFirefighter.setText(Jobs.FIREFIGHTER.getName());
        jobButtonFirefighter.setBackgroundResource(R.drawable.button_style);
        jobButtonFirefighter.setVisibility(GONE);
        jobButtonFirefighter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 100;
                friends += 25;
                job=Jobs.FIREFIGHTER;
                jobButtonActivation(true,true,true,true,false,true,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.FIREFIGHTER.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.FIREFIGHTER.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.FIREFIGHTER.getInfluence()));

            }
        });
        //Banker//6
        jobButtonBanker =(Button) dialog.findViewById(R.id.buttonSix);
        jobButtonBanker.setText(Jobs.BANKER.getName() );
        jobButtonBanker.setBackgroundResource(R.drawable.button_style);
        jobButtonBanker.setVisibility(GONE);
        jobButtonBanker.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 200;
                friends += 50;
                job=Jobs.BANKER;
                jobButtonActivation(true,true,true,true,true,false,true,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.BANKER.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.BANKER.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.BANKER.getInfluence()));
            }
        });
        //Scientist//7
        jobButtonScientist = (Button) dialog.findViewById(R.id.buttonSeven);
        jobButtonScientist.setText(Jobs.SCIENTIST.getName());
        jobButtonScientist.setVisibility(GONE);
        jobButtonScientist.setBackgroundResource(R.drawable.button_style);
        jobButtonScientist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 200;
                friends += 75;
                job=Jobs.SCIENTIST;
                jobButtonActivation(true,true,true,true,true,true,false,true,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.SCIENTIST.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.SCIENTIST.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.SCIENTIST.getInfluence()));
            }
        });
        //Independent//8
        jobButtonIndependent = (Button) dialog.findViewById(R.id.buttonEight);
        jobButtonIndependent.setText(Jobs.INDEPENDENT.getName());
        jobButtonIndependent.setVisibility(GONE);
        jobButtonIndependent.setBackgroundResource(R.drawable.button_style);
        jobButtonIndependent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 300;
                friends += 100;
                job=Jobs.INDEPENDENT;
                jobButtonActivation(true,true,true,true,true,true,true,true,false,true,true,true,true);
                jobDetailTitle.setText(Jobs.INDEPENDENT.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.INDEPENDENT.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.INDEPENDENT.getInfluence()));
            }
        });
        //Buisness Owner//9
         jobButtonBusinessOwner = (Button) dialog.findViewById(R.id.buttonNine);
        jobButtonBusinessOwner.setText(Jobs.BUSINESSOWNER.getName());
        jobButtonBusinessOwner.setVisibility(GONE);
        jobButtonBusinessOwner.setBackgroundResource(R.drawable.button_style);
        jobButtonBusinessOwner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 500;
                friends += 200;
                human.setJob(Jobs.BUSINESSOWNER);
                jobButtonActivation(true,true,true,true,true,true,true,false,true,true,true,true,true);
                jobDetailTitle.setText(Jobs.BUSINESSOWNER.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.BUSINESSOWNER.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.BUSINESSOWNER.getInfluence()));
            }
        });
        //King//10
         jobButtonKing = (Button) dialog.findViewById(R.id.buttonTen);
        jobButtonKing.setText(Jobs.KING.getName() );
        jobButtonKing.setVisibility(GONE);
        jobButtonKing.setBackgroundResource(R.drawable.button_style);
        jobButtonKing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 1000;
                friends += 500;
                job=Jobs.KING;
                jobButtonActivation(true,true,true,true,true,true,true,true,true,false,true,true,true);
                jobDetailTitle.setText(Jobs.KING.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.KING.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.KING.getInfluence()));
            }
        });
        //Sultan//11
        jobButtonSultan =(Button) dialog.findViewById(R.id.buttonEleven);
        jobButtonSultan.setText(Jobs.SULTAN.getName() );
        jobButtonSultan.setVisibility(GONE);
        jobButtonSultan.setBackgroundResource(R.drawable.button_style);
        jobButtonSultan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 1500;
                friends += 1000;
                job=Jobs.SULTAN;
                jobButtonActivation(true,true,true,true,true,true,true,true,true,true,false,true,true);
                jobDetailTitle.setText(Jobs.SULTAN.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.SULTAN.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.SULTAN.getInfluence()));
            }
        });
        //God
        jobButtonGod=(Button) dialog.findViewById(R.id.buttonTwelve);
        jobButtonGod.setText(Jobs.GOD.getName());
        jobButtonGod.setVisibility(GONE);
        jobButtonGod.setBackgroundResource(R.drawable.button_style);
       jobButtonGod.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 1500;
                friends += 1000;
                job = Jobs.GOD;
                jobButtonActivation(true,true,true,true,true,true,true,true,true,true,true,false,true);
                jobDetailTitle.setText(Jobs.GOD.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.GOD.getIncome()) );
                jobDetailInfluence.setText(Double.toString(Jobs.GOD.getInfluence()));
            }
        });
        //Omega//12
        jobButtonOmega = (Button) dialog.findViewById(R.id.buttonThirteen);
        jobButtonOmega.setText("\u03A9" + "mega" );
        jobButtonOmega.setBackgroundResource(R.drawable.button_style);
        jobButtonOmega.setVisibility(GONE);
        jobButtonOmega.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 3000;
                friends += 2000;
                job=Jobs.OMEGA;
                jobButtonActivation(true,true,true,true,true,true,true,true,true,true,true,true,false);
                jobDetailTitle.setText(Jobs.OMEGA.getName());
                jobDetailIncome.setText(currencyFormat.format(Jobs.OMEGA.getIncome()));
                jobDetailInfluence.setText(Double.toString(Jobs.OMEGA.getInfluence()));
            }
        });
        //If statements to show only certain Buttons
        if(genie) {//If the genie is active..this will show up if the jobButton is selected..Genie can not be activated until the user is >=20
            jobButtonBeggar.setVisibility(View.VISIBLE); //Begger
            jobButtonVagrant.setVisibility(View.VISIBLE);//Vagrant
            jobButtonIntern.setVisibility(View.VISIBLE);//Intern
            jobButtonPackingBoy.setVisibility(View.VISIBLE);//PackingBoy
            jobButtonFirefighter.setVisibility(View.VISIBLE);//Firefighter
            jobButtonBanker.setVisibility(View.VISIBLE);//BANKER;
            jobButtonScientist.setVisibility(View.VISIBLE);  //Scientist;
            jobButtonBusinessOwner.setVisibility(View.VISIBLE);//BusinessOwner;
            jobButtonIndependent.setVisibility(View.VISIBLE);  //Independent;
            jobButtonKing.setVisibility(View.VISIBLE);  //KING;
            jobButtonSultan.setVisibility(View.VISIBLE); //SULTAN
            jobButtonGod.setVisibility(View.VISIBLE);//GOD
            jobButtonOmega.setVisibility(View.VISIBLE);  //OMEGA
        } else {
            if (packingboy){ jobButtonPackingBoy.setVisibility(View.VISIBLE);}//PackingBoy
            if (intern){ jobButtonIntern.setVisibility(View.VISIBLE);}//Intern;}//Intern

            if (human.getCountry() == Countries.Irada ||
                    human.getCountry() == Countries.Itican ||
                    human.getCountry() == Countries.Albaq) {
                if (begger) { jobButtonBeggar.setVisibility(View.VISIBLE);
                }//Begger
                if (vagrant) { jobButtonVagrant.setVisibility(View.VISIBLE);}//Vagrant
                if (packingboy) {  jobButtonPackingBoy.setVisibility(View.VISIBLE);  }//PackingBoy
            }
            if (human.getCountry() == Countries.Trinentina ||
                    human.getCountry() == Countries.Albico ||
                    human.getCountry() == Countries.Ugeria ||
                    human.getCountry() == Countries.Portada) {
                if (firefighter) { jobButtonFirefighter.setVisibility(View.VISIBLE);  }//FIREFIGHTER;
                if (banker) { jobButtonBanker.setVisibility(View.VISIBLE);  }//BANKER;
                if (scientist) { jobButtonScientist.setVisibility(View.VISIBLE);  }//Scientist;
            }
            if (human.getCountry() == Countries.Kuwador ||
                    human.getCountry() == Countries.Ukrark ||
                    human.getCountry() == Countries.Rany) {
                if (banker) { jobButtonBanker.setVisibility(View.VISIBLE);   }//BANKER;
                if (scientist) {  jobButtonScientist.setVisibility(View.VISIBLE);   }//Scientist;
                if (independent) {  jobButtonIndependent.setVisibility(View.VISIBLE); }//Independent;
                if (businessowner) {  jobButtonBusinessOwner.setVisibility(View.VISIBLE);  }//BusinessOwner;
                if(sultan){jobButtonSultan.setVisibility(View.VISIBLE);}//Sultan
            }
            if (human.getCountry() == Countries.Heaven) {
                if (independent) {jobButtonIndependent.setVisibility(View.VISIBLE);  }//Independent;
                if (king) {  jobButtonKing.setVisibility(View.VISIBLE);  }//KING;
                if (king && sultan) { jobButtonSultan.setVisibility(View.VISIBLE);  }//SULTAN
                if(god){ jobButtonGod.setVisibility(View.VISIBLE);}//GOD
                if(god&&sultan&&king){jobButtonOmega.setVisibility(View.VISIBLE);}  //OMEGA
            }
        }
        selectCountryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (genie) {
                    wishes++;
                    //selectAJobButton.setEnabled(false);
                    System.out.println("wishes:" + wishes);
                    Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                            Toast.LENGTH_LONG).show();
                    selectAJobButton.setEnabled(false);
                    genieDoSomething();
                } else if (job == human.getJob()) {
                    //User will not lose a turn if the current job is the same as the selected job
                    buttonActivation(true);
                } else {
                    buttonActivation(false);
                }
            }
        });
        cancelCountrySelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void availableJobs(){
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.SplashTheme);
        AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
        ad
                .setMessage("Available Jobs."  + "\n" +

                        "Beggar :" + begger.toString() + "\n" +
                        "Vagrant :" + vagrant.toString() + "\n" +
                        "PackingBoy :" + packingboy.toString() + "\n" +
                        "Intern :" + intern.toString() + "\n" +
                        "Firefighter :" + firefighter.toString() + "\n" +
                        "Banker :" + banker.toString() + "\n" +
                        "Independent :" + independent.toString() + "\n" +
                        "Scientist :" + scientist.toString() + "\n" +
                        "Business Owner :" + businessowner.toString() + "\n" +
                        "King :" + king.toString() + "\n" +
                        "God :" + god.toString() + "\n" +
                        "Sultan :" + sultan.toString() + "\n" +
                        "Omega :" + omega.toString()
                )
                .setTitle("IDEAL")
                .setNeutralButton("Cancel", null)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .create();


        ad.show();
    }
    private void goToSchool(){
        //This number will keep track if a job is unlocked or not...if this number is still equal to 0 at the end of this function then no job was unlocked
            int unlockjobInt=0;
        //Call the XML List view and set it with  the countries array
        //LayoutInflater inflater = getLayoutInflater();
        //View convertView = inflater.inflate(R.layout.alertdialog_layout, null, false);
        //Increment and Update the amount of times this user went to school
        schoolAttendanceAmount++;
        schoolAttendanceAmountTextView.setText(String.format(Locale.US,"%02d",schoolAttendanceAmount));
        //schoolAttendanceAmountTextView.setText(Integer.toString(schoolAttendanceAmount));
        //This String Variable is used to hold all of the messages in one string
        String chainText="";
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.message_dialog);
        //Initializing Buttons
        final Button initSelectionButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button initCancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Set up First Name Text View
        final TextView tittleTextView = (TextView) dialog.findViewById(R.id.dialog_Title);
        //Setup the Last Name Text View
        final TextView messageTextView = (TextView) dialog.findViewById(R.id.dialog_Details);
        initSelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genie) {
                    wishes++;
                    schoolButton.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                            Toast.LENGTH_LONG).show();
                    genieDoSomething();
                } else {
                    wealth += -2500.0 * human.getCountry().getMultiplier();
                    buttonActivation(false);
                    continueButton.setEnabled(true);
                }
                dialog.dismiss();
            }
        });
        initCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        messageTextView.setText("Go a school:Raises the amount of professional Associates" + "\n" +
                        "Unlocks jobs" + "\n" +
                "Raises the amount of friends");
        tittleTextView.setText("IDEAL:Going to School");
        if ( schoolAttendanceAmount>=1) {
            if(!begger) {
                begger = true;
                chainText += "You have unlocked the Beggar Job";
                unlockjobInt++;
            }if(!vagrant) {
                vagrant = true;
                chainText += "\n" + "You have unlocked the Vagrant Job";
                unlockjobInt++;
            }
        }if( schoolAttendanceAmount>=4){
            if(!intern) {
                intern = true;
                chainText += "\n" + "You have unlocked the Intern Job";
                unlockjobInt++;
            }if(!packingboy) {
                packingboy = true;
                chainText += "\n" + "You have unlocked the Packing boy Job";
                unlockjobInt++;
            }if(!firefighter) {
                firefighter = true;
                chainText += "\n" + "You have unlocked the Firefighter Job";
                unlockjobInt++;
            }

        }if( schoolAttendanceAmount>=5&&socialisingWithFriends>=2 && workingOnPhysicalApp>=1) {
            if(!banker) {
                banker = true;
                chainText += "\n" + "You have unlocked the Banker Job";
                unlockjobInt++;
            }if(!scientist) {
                scientist = true;
                chainText += "\n" + "You have unlocked the Scientist Job";
                unlockjobInt++;
            }if(!independent) {
                independent = true;
                chainText += "\n" + "You have unlocked the Independent Job";
                unlockjobInt++;
            }if(!firefighter) {
                firefighter = true;
                chainText += "\n" + "You have unlocked the Firefighter Job";
                unlockjobInt++;
            }

        }if( schoolAttendanceAmount>=6&&socialisingWithFriends>=3 && workingOnPhysicalApp>=1&&!king) {

                king = true;
                chainText += "\n" + "You have unlocked the King Job";
                unlockjobInt++;


        }if(schoolAttendanceAmount>=7&&socialisingWithFriends>=3 &&workingOnPhysicalApp>=3&&!sultan){
                 sultan=true;
                chainText+="\n" + "You have unlocked the Sultan Job";
                unlockjobInt++;

        }if( schoolAttendanceAmount>=8&&socialisingWithFriends>=4 &&workingOnPhysicalApp>=4&&!god) {
                god = true;
                chainText+="\n" + "You have unlocked the God Job";
             unlockjobInt++;

        }if(schoolAttendanceAmount>=10&&socialisingWithFriends>=10 &&workingOnPhysicalApp>=10&&!omega){
            omega = true;
            chainText+="\n" + "You have unlocked the Omega Job";
            unlockjobInt++;
            if(!heavenBoolean){
                heavenBoolean=true;
                chainText+="\n" + "You have unlocked Heaven";
            }
        }
        //There should be at least 6 words in the chainText and if not then the user has not unlocked any job

        if(unlockjobInt==0){
            messageTextView.setText("No job was unlocked.You need more schooling");
        }else {
            messageTextView.setText(chainText);
        }
        dialog.show();
    }
    private void workOnPhysical(){
        workingOnPhysicalApp++;
        //workingOnPhysicalAppTextView.setText(Integer.toString(workingOnPhysicalApp));
        workingOnPhysicalAppTextView.setText(String.format(Locale.US,"%02d",workingOnPhysicalApp));
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.message_dialog);
        //Initializing Buttons
        final Button initSelectionButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button initCancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Set up First Name Text View
        final TextView tittleTextView = (TextView) dialog.findViewById(R.id.dialog_Title);
        //Setup the Last Name Text View
        final TextView messageTextView = (TextView) dialog.findViewById(R.id.dialog_Details);
        initSelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genie) {
                    wishes++;
                    Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                            Toast.LENGTH_LONG).show();
                    workOnPhysicalAppearanceButton.setEnabled(false);
                    influence += 1000;
                    friends += 1000;
                    worshippers += 100;
                    looks += 1;
                    genieDoSomething();

                } else {
                    buttonActivation(false);
                    wealth += -100 * human.getCountry().getMultiplier();
                    influence += 1000;
                    friends += 1000;
                    worshippers += 100;
                    looks += 1;
                    if (randomNum > 25) {
                        healthUpdater(5);
                    }
                }
                dialog.dismiss();
            }
        });
        initCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                workingOnPhysicalApp--;
                workingOnPhysicalAppTextView.setText(String.format(Locale.US, "%02d", workingOnPhysicalApp));
                dialog.dismiss();
            }
        });
        messageTextView.setText(
                "Looks +1" + "\n" +
                                "There is a chance your health will get boosted"+ "\n" +
                        " Worshippers +100" +/*'\&#x25b2;'+*/ "\n" +
                        "Influence +1000" + "\n" +
                        "Friends +1000" + "\n" +
                        "Wealth - $" + 100 * human.getCountry().getMultiplier());

        tittleTextView.setText("IDEAL:Work on your Physical");
        dialog.show();
      }
    private void socializeWithPeople(){
        socialisingWithFriends++;
        socialisingWithFriendsTextView.setText(String.format(Locale.US,"%02d",socialisingWithFriends));
        //socialisingWithFriendsTextView.setText(Integer.toString(socialisingWithFriends));
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.message_dialog);
        //Initializing Buttons
        final Button initSelectionButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button initCancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Set up First Name Text View
        final TextView tittleTextView = (TextView) dialog.findViewById(R.id.dialog_Title);
        //Setup the Last Name Text View
        final TextView messageTextView = (TextView) dialog.findViewById(R.id.dialog_Details);
        initSelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (genie) {
                    wishes++;
                    Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                            Toast.LENGTH_LONG).show();
                    socializeWithPeopleButton.setEnabled(false);
                    influence += 1000;
                    professionalAssociates += 1000;
                    friends += 1000;
                    genieDoSomething();
                } else {
                    wealth += -50 * human.getCountry().getMultiplier();
                    influence += 1000;
                    professionalAssociates += 1000;
                    friends += 1000;
                    buttonActivation(false);
                }
                dialog.dismiss();
            }
        });
        initCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                socialisingWithFriends--;
                socialisingWithFriendsTextView.setText(String.format(Locale.US, "%02d", socialisingWithFriends));
                dialog.dismiss();
            }
        });
        messageTextView.setText("Worshippers +100" + "\n" +
                "Influence +1000" + "\n" +
                "Friends +1000" + "\n" +
                "Professional Associates +1000" + "\n" +
                "Wealth - $" + 100 * human.getCountry().getMultiplier());

        tittleTextView.setText("IDEAL:Socialize with People");
        dialog.show();
    }
    private void selectACountry(){
        final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
        dialog.setContentView(R.layout.countrieslayout);
        dialog.setTitle("Select a Country");
        //Initializing Buttons
        final Button selectCountryButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button cancelCountrySelectionButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Initializing Text Views
        final TextView countryDetailTitle = (TextView) dialog.findViewById(R.id.countryDetailTitle);//Define the Text Box
        countryDetailTitle.setText(getString(R.string.getCountry_text));
        final TextView countryDetailTax = (TextView) dialog.findViewById(R.id.countryDetailTax);//Define the Text Box
        countryDetailTax.setText(getString(R.string.tax_text));
        final TextView countryDetailRequireWealth = (TextView) dialog.findViewById(R.id.countryDetailRequireWealth);//Define the Text Box
        countryDetailRequireWealth.setText(getString(R.string.overallWealthRequiredView_text));
        final TextView countryDetailRequiredInfluence = (TextView) dialog.findViewById(R.id.countryDetailRequiredInfluence);//Define the Text Box
        countryDetailRequiredInfluence.setText(getString(R.string.influenceRequiredAmountTextView_String));
        final TextView countryDetailRequireFriends = (TextView) dialog.findViewById(R.id.countryDetailRequireFriends);//Define the Text Box
        countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String));

        countryButtonIrada = (Button) dialog.findViewById(R.id.buttonOne);
        countryButtonIrada.setText(Countries.Irada.getName());
        //countryButtonIrada.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.signatureColorOne));
        countryButtonIrada.setVisibility(View.VISIBLE);
        countryButtonIrada.setBackgroundResource(R.drawable.button_style);
        countryButtonIrada.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               countryOfUser=(Countries.Irada);
            //countryButtonActivation(boolean irada,boolean itican,boolean albaq,boolean trinentina, boolean alibico, boolean ugeria, boolean portada,
                                                //boolean kuwador,boolean ukrark,boolean rany, boolean heaven){
                countryButtonActivation(false,true,true,true,true,true,true,true,true,true,true);
                if(human.getCountryString().equals(Countries.Irada.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Irada.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Irada.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Irada.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Irada.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Irada.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Irada.getRequiredFriends());

            }
        });
        //Itican
        countryButtonItican = (Button) dialog.findViewById(R.id.buttonTwo);
        countryButtonItican.setText(Countries.Itican.getName());
        countryButtonItican.setVisibility(View.VISIBLE);
        countryButtonItican.setBackgroundResource(R.drawable.button_style);
        countryButtonItican.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Itican);
                if(human.getCountryString().equals(Countries.Itican.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Itican.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Itican.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Itican.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Itican.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Itican.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Itican.getRequiredFriends());
                countryButtonActivation(true,false,true,true,true,true,true,true,true,true,true);

            }
        });
        //Albaq
        countryButtonAlbaq = (Button) dialog.findViewById(R.id.buttonThree);
        countryButtonAlbaq.setText(Countries.Albaq.getName());
        countryButtonAlbaq.setVisibility(View.GONE);
        countryButtonAlbaq.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonIntern);
        countryButtonAlbaq.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Albaq);
                if(human.getCountryString().equals(Countries.Albaq.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Albaq.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Albaq.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Albaq.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Albaq.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Albaq.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Albaq.getRequiredFriends());
                countryButtonActivation(true,true,false,true,true,true,true,true,true,true,true);
            }
        });
        //Trinentina
        countryButtonTrinentina = (Button) dialog.findViewById(R.id.buttonFour);
        countryButtonTrinentina.setText(Countries.Trinentina.getName() );
        countryButtonTrinentina.setVisibility(View.GONE);
         countryButtonTrinentina.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonPackingBoy);
        countryButtonTrinentina.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=Countries.Trinentina;
                if(human.getCountryString().equals(Countries.Trinentina.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Trinentina.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Trinentina.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Trinentina.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Trinentina.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Trinentina.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Trinentina.getRequiredFriends());
                countryButtonActivation(true,true,true,false,true,true,true,true,true,true,true);
            }
        });
        //Albico
        countryButtonAlbico = (Button) dialog.findViewById(R.id.buttonFive);
        countryButtonAlbico.setText(Countries.Albico.getName());
        countryButtonAlbico.setVisibility(View.GONE);
        countryButtonAlbico.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonFirefighter);
        countryButtonAlbico.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Albico);
                if(human.getCountryString().equals(Countries.Albico.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Albico.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Trinentina.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Albico.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Albico.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Albico.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Albico.getRequiredFriends());
               countryButtonActivation(true,true,true,true,false,true,true,true,true,true,true);
            }
        });
        //Ugeria
         countryButtonUgeria = (Button) dialog.findViewById(R.id.buttonSix);
        countryButtonUgeria.setText(Countries.Ugeria.getName());
        countryButtonUgeria.setVisibility(View.GONE);
        countryButtonUgeria.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonBanker);
        countryButtonUgeria.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Ugeria);
                if(human.getCountryString().equals(Countries.Ugeria.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Ugeria.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Ugeria.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Ugeria.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Ugeria.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Ugeria.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Ugeria.getRequiredFriends());
                countryButtonActivation(true,true,true,true,true,false,true,true,true,true,true);
            }
        });
        //Portada
        countryButtonPortada = (Button) dialog.findViewById(R.id.buttonSeven);
        countryButtonPortada.setText(Countries.Portada.getName());
        countryButtonPortada.setVisibility(View.GONE);
        countryButtonPortada.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonScientist);
        countryButtonPortada.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Portada);
                if(human.getCountryString().equals(Countries.Portada.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Portada.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Portada.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Portada.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Portada.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Portada.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Portada.getRequiredFriends());
                countryButtonActivation(true,true,true,true,true,true,false,true,true,true,true);

            }
        });
        //Kuwador
        countryButtonKuwador = (Button) dialog.findViewById(R.id.buttonEight);
        countryButtonKuwador.setText(Countries.Kuwador.getName());
        countryButtonKuwador.setVisibility(View.GONE);
        countryButtonKuwador.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonIndependent);
        countryButtonKuwador.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Kuwador);
                if(human.getCountryString().equals(Countries.Kuwador.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Kuwador.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Kuwador.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Kuwador.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Kuwador.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Kuwador.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Kuwador.getRequiredFriends());
                countryButtonActivation(true,true,true,true,true,true,true,false,true,true,true);
            }
        });
        //Ukrark
        countryButtonUkrark = (Button) dialog.findViewById(R.id.buttonNine);
        countryButtonUkrark.setText(Countries.Ukrark.getName());
        countryButtonUkrark.setVisibility(View.GONE);
        countryButtonUkrark.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonBusinessOwner);
        countryButtonUkrark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Ukrark);
                if(human.getCountryString().equals(Countries.Ukrark.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Ukrark.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Ukrark.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Ukrark.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Ukrark.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Ukrark.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Ukrark.getRequiredFriends());
               countryButtonActivation(true,true,true,true,true,true,true,true,false,true,true);
            }
        });
        //Rany
       countryButtonRany = (Button) dialog.findViewById(R.id.buttonTen);
        countryButtonRany.setText(Countries.Rany.getName());
        countryButtonRany.setVisibility(View.GONE);
        countryButtonRany.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonKing);
        countryButtonRany.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=(Countries.Rany);
                if(human.getCountryString().equals(Countries.Rany.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Rany.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Rany.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Rany.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Rany.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Rany.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Rany.getRequiredFriends());
               countryButtonActivation(true,true,true,true,true,true,true,true,true,false,true);
            }
        });
        //Heaven
        countryButtonHeaven = (Button) dialog.findViewById(R.id.buttonEleven);
        countryButtonHeaven.setText(Countries.Heaven.getName());
        countryButtonHeaven.setVisibility(View.GONE);
         countryButtonHeaven.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonSultan);
        countryButtonHeaven.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=Countries.Heaven;
                if(human.getCountryString().equals(Countries.Heaven.getName())) {//TO show the User that they are in this Country
                    countryDetailTitle.setText(getString(R.string.currentCountry_String)+Countries.Heaven.getName());
                    selectCountryButton.setVisibility(View.INVISIBLE);
                }else{
                    countryDetailTitle.setText(Countries.Heaven.getName());
                    selectCountryButton.setVisibility(View.VISIBLE);
                }
                countryDetailTax.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(Countries.Heaven.getTaxes()));
                countryDetailRequireWealth.setText(getString(R.string.overallWelathTextView_text) + ":" + currencyFormat.format(Countries.Heaven.getRequireedWealth()));
                countryDetailRequiredInfluence.setText(getString(R.string.influenceAmountTextView_String) + ":" + Countries.Heaven.getRequiredInfluence());
                countryDetailRequireFriends.setText(getString(R.string.friendsAmountTextView_String) + ":" + Countries.Heaven.getRequiredFriends());
                countryButtonActivation(true,true,true,true,true,true,true,true,true,true,false);
            }
        });
        //If statements to show only certain Buttons
        if(genie) {//If the genie is active..this will show up if the jobButton is selected..Genie can not be activated until the user is >=20
            countryButtonActivation(true,true,true,true,true,true,true,true,true,true,true);
        }else {
            /*if (human.getOverAllWealth() >= Countries.Irada.getRequiredFriends() && human.getFriends() >= Countries.Irada.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Irada.getRequiredInfluence()) {
                layout.addView(countryButtonIrada);
            }
            if (human.getOverAllWealth() >= Countries.Itican.getRequiredFriends() && human.getFriends() >= Countries.Itican.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Itican.getRequiredInfluence()) {
                layout.addView(countryButtonItican);
            }*/
            if (human.getOverAllWealth() >= Countries.Albaq.getRequiredFriends() && human.getFriends() >= Countries.Albaq.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Albaq.getRequiredInfluence()) {
                //layout.addView(countryButtonAlbaq);//Albaq
                countryButtonAlbaq.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Trinentina.getRequiredFriends() && human.getFriends() >= Countries.Trinentina.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Trinentina.getRequiredInfluence()) {
                //layout.addView(countryButtonTrinentina);//Trinentina
                countryButtonTrinentina.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Albico.getRequiredFriends() && human.getFriends() >= Countries.Albico.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Albico.getRequiredInfluence()) {
                //layout.addView(countryButtonAlbico);//Albico
                countryButtonAlbico.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Ugeria.getRequiredFriends() && human.getFriends() >= Countries.Ugeria.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Ugeria.getRequiredInfluence()) {
                //layout.addView(countryButtonUgeria);//Ugeria;
                countryButtonUgeria.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Portada.getRequiredFriends() && human.getFriends() >= Countries.Portada.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Portada.getRequiredInfluence()) {
                //layout.addView(countryButtonPortada);  //Portada;
                countryButtonPortada.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Kuwador.getRequiredFriends() && human.getFriends() >= Countries.Kuwador.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Kuwador.getRequiredInfluence()) {
                //layout.addView(countryButtonKuwador);//Kuwador;
                countryButtonKuwador.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Ukrark.getRequiredFriends() && human.getFriends() >= Countries.Ukrark.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Ukrark.getRequiredInfluence()) {
                //layout.addView(countryButtonUkrark);  //Ukrark;
                countryButtonUkrark.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Rany.getRequiredFriends() && human.getFriends() >= Countries.Rany.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Rany.getRequiredInfluence()) {
                //layout.addView(countryButtonRany);  //Rany;
                countryButtonRany.setVisibility(View.VISIBLE);
            }
            if (human.getOverAllWealth() >= Countries.Heaven.getRequiredFriends() && human.getFriends() >= Countries.Heaven.getRequiredFriends() &&
                    human.getInfluence() >= Countries.Heaven.getRequiredInfluence()) {
                //layout.addView(countryButtonHeaven); //Heaven
                countryButtonHeaven.setVisibility(View.VISIBLE);
            }
        }

        selectCountryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(genie) {
                    wishes++;
                    Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                            Toast.LENGTH_LONG).show();
                    changeCountryButton.setEnabled(false);
                    genieDoSomething();
                }else{
                    human.setCountries(countryOfUser);
                    countryTextView.setText("Country:" + human.getCountry());
                    //Update Tax
                    //You can chain .get commands as long as that .get returns a class/object with its own getters
                    taxTextView.setText("Tax:" + currencyFormat.format(human.getCountry().getTaxes()));
                    tax = human.getCountry().getTaxes();
                    //keepStatsUpToDate(-(human.getCountry().getTaxes() + 100 * human.getCountry().getMultiplier()), 0, job, human.getCountry().getTaxes(), 0, 0, 0, 0);
                    wealth+=-(human.getCountry().getTaxes() + 100 * human.getCountry().getMultiplier());
                    buttonActivation(false);
                }

            }
        });

        cancelCountrySelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });

        dialog.show();
    }
///->

///->UPDATING STATS
    private void healthUpdater(final int healthA) {
         final int intTemp =1;
        if (healthA < 0) {
            new Thread(new Runnable() {
                public void run() {
                    for (int  i=0;i>healthA;i--) {
                        int delayhealthProgress=healthProgressBar.getProgress();//This will make a delay effect on the secondary progresss
                         // Update the progress bar
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                 healthProgressBar.setProgress(healthProgressBar.getProgress() - intTemp);
                                healthTextView.setText(healthProgressBar.getProgress() - intTemp + "%");
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                             Thread.sleep(225);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        healthProgressBar.setSecondaryProgress(delayhealthProgress-intTemp);
                    }

                }

            }).start();
        } else {
            final int additionalHealth = healthProgressBar.getProgress()+healthA;
           new Thread(new Runnable() {
                public void run() {
                   for (int i=healthProgressBar.getProgress() ;i< additionalHealth;i++) {
                        // Update the progress bar
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                healthProgressBar.setProgress(healthProgressBar.getProgress()+1);
                                healthTextView.setText((healthProgressBar.getProgress()+1) + "%");
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                           Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }
    }
    private void calculateTheProgressBarPercentage(Double overallWealthA, int influenceAmountA) {//The Influence Progress Bar Percentage is not very accurate...May not be a big issue

        /*
        Calculating the percentage of the wealth/Influence first and then figuring out if they are <=0 before updating the actual progress Bar...
        So that no number less than Zero is placed into the progressBar and potentially crashing the program
         */
    //Calculate the OverAllWealth Percentage
    final double overallwealthvalue = ((overallWealthA / maxOverallWealth)*100);//Complete the whole percentage equation and then convert number to Int for the Progress Bars
    //Calculate the influence Percentage
    final double influencevalue = ((influenceAmountA * 1.0) / (maxInfluence * 1.0)*100);
    //Don't need to get the product of (human.getOverAllWealth() / maxOverallWealth) and 100 because the percentFormat already multiplies product

    //This will allow the progress Bars to decrease in value //DECREMENT PROGRESSBAR
    if(overallWealthProgressBar.getProgress()> overallwealthvalue){
        overallWealthProgressBar.setProgress((int) overallwealthvalue);
        overallWealthPercentageTextView.setText((int) overallwealthvalue+"%");
    }
    if(influenceProgressBar.getProgress()> influencevalue){
        influenceProgressBar.setProgress((int) influencevalue);
        influencePercentageTextView.setText((int) influencevalue +"%");
    }

    //INCREMENT PROGRESSBAR
    if ( overallwealthvalue*100 <= 1 ) {
        overallWealthProgressBar.setProgress(1);
        overallWealthPercentageTextView.setText("<" + percentFormat.format(0.01));

    }
    if ( influencevalue*100 <= 1 ) {
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
                        overallWealthPercentageTextView.setText(mProgressStatusOverAllWealth+"%");
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
                        influencePercentageTextView.setText(mProgressStatusInfluence+"%");
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
    private void keepStatsUpToDate(Double overallWealthA, int influenceAmountA, Jobs jobA, /*Countries countryA,*/
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
        overallWealthTextView.setText(currencyFormat.format(human.getOverAllWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText(influenceAmountString);
        jobTextView.setText(getString(R.string.getJob_text) + ":"  + human.getJob());
        countryTextView.setText(getString(R.string.getCountry_text) + ":" +  human.getCountryString());
        taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));


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
    private void updatingStateOfFamily(double wealthA, int influenceA) {
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
        //update View on the screen

        tax = family.getFamilyCountry().getTaxes();
        overallWealthTextView.setText(currencyFormat.format(family.getFamilyWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText(influenceAmountString);
        countryTextView.setText(getString(R.string.getCountry_text) + ":" +  family.getFamilyCountry().getName());
        taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));
        //This "if" statement is going to see whether our percentage function will give something less than 1...which the progress bars can no handle
        if (overallWealthTextView.getText().length() <= 5 ) {
            calculateTheProgressBarPercentage(1.0, family.getFamilyInfluence());


        }
        if (influenceTextView.getText().length() <= 4) {
            calculateTheProgressBarPercentage(family.getFamilyWealth(), 1);

        }

        calculateTheProgressBarPercentage(family.getFamilyWealth(), family.getFamilyInfluence());
    }
///->

///->IDEAL APPLICATION USER INITIALIZATION CODE
private void initDialog(final double maxOverAllWealthA, final int maxInfluenceA) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.initilization_layout);
        //Initializing Buttons
        final Button initSelectionButton = (Button) dialog.findViewById(R.id.buttonSelect);
        initSelectionButton.setVisibility(View.INVISIBLE);
        final Button initCancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
    //TextViews
    final TextView wealthGoalTextView = (TextView) dialog.findViewById(R.id.wealthGoalTextView);
    wealthGoalTextView.setText(currencyFormat.format(maxOverAllWealthA));
    final TextView influenceGoalTextView = (TextView) dialog.findViewById(R.id.influenceGoalTextView);
    influenceGoalTextView.setText(Integer.toString(maxInfluenceA));
        //Set up First Name Text View
        final EditText userNameFirst = (EditText) dialog.findViewById(R.id.firstNameEditText);
        //Setup the Last Name Text View
        final EditText userNameFamily =(EditText) dialog.findViewById(R.id.lastNameEditText);

        //Country Buttons
        final Button countryButtonOne = (Button) dialog.findViewById(R.id.buttonOne);
        countryButtonOne.setText(Countries.Irada.getName() + '\n' + "Taxes:$" + Countries.Irada.getTaxes() + "\n Required Influence: " + Countries.Irada.getRequiredInfluence()
                +"\n Required Wealth: "+Countries.Irada.getRequireedWealth()+"\nRequired Friends: "+Countries.Irada.getRequiredInfluence());

        final Button countryButtonTwo = (Button) dialog.findViewById(R.id.buttonTwo);
        countryButtonTwo.setText(Countries.Itican.getName() + '\n' + "Taxes:$" + Countries.Itican.getTaxes() + "\n Required Influence: " + Countries.Itican.getRequiredInfluence()
                +"\n Required Wealth: "+Countries.Itican.getRequireedWealth()+"\nRequired Friends: "+Countries.Itican.getRequiredInfluence());

        countryButtonOne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=Countries.Irada;
                countryButtonOne.setEnabled(false);
                countryButtonTwo.setEnabled(true);
                initSelectionButton.setVisibility(View.VISIBLE);
            }
        });
        countryButtonTwo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countryOfUser=Countries.Itican;
                countryButtonOne.setEnabled(true);
                countryButtonTwo.setEnabled(false);
                initSelectionButton.setVisibility(View.VISIBLE);
            }
        });

       //Family
        //Get the arrayLIst from the String xml
        final List<String> familyTypes = Arrays.asList(getResources().getStringArray(R.array.familyTypes));
        final ListView familyListView = (ListView) dialog.findViewById(R.id.familyChoiceList);
        final EditText friendsAmountEditText = (EditText) dialog.findViewById(R.id.friendsAmountEditText);
        final EditText influenceAmountEditText = (EditText) dialog.findViewById(R.id.influenceAmountEditText);
        final EditText wealthAmountEditText = (EditText) dialog.findViewById(R.id.wealthAmountEditText);
        friendsAmountEditText.setVisibility(GONE);
        influenceAmountEditText.setVisibility((GONE));
        wealthAmountEditText.setVisibility(GONE);
        final Switch customFamilySwitch = (Switch) dialog.findViewById(R.id.customFamilySwitch);
        //customFamilySwitch.getBackground().setAlpha(0);
        customFamilySwitch.setText("Select A Family");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, familyTypes);

        customFamilySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    customFamilySwitch.setText(getString(R.string.cusomFamilyToggle_String));
                    // The toggle is enabled
                    //Toast.makeText(MainActivity.this, "All Values need to be greater than 0 or you will have to create a new Family",
                    //Toast.LENGTH_LONG).show();
                    familyListView.setEnabled(false);
                    familyListView.setVisibility(GONE);
                    familyListView.setItemChecked(0, false);
                    familyListView.setItemChecked(1, false);
                    familyListView.setItemChecked(2, false);
                    familyListView.setItemChecked(3, false);
                    //Show TextView
                    friendsAmountEditText.setVisibility(View.VISIBLE);
                    influenceAmountEditText.setVisibility(View.VISIBLE);
                    wealthAmountEditText.setVisibility(View.VISIBLE);

                } else {
                    customFamilySwitch.setText("Select A Family");
                    // The toggle is disabled
                    //isChecked=false;
                    //selectAFamilyType();
                    familyListView.setEnabled(true);
                    familyListView.setVisibility(View.VISIBLE);
                    familyListView.setItemChecked(0, false);
                    familyListView.setItemChecked(1, false);
                    familyListView.setItemChecked(2, false);
                    familyListView.setItemChecked(3, false);
                    //Remove TextViews
                    friendsAmountEditText.setVisibility(GONE);
                    influenceAmountEditText.setVisibility(GONE);
                    wealthAmountEditText.setVisibility(GONE);

                }
            }
        });

        initSelectionButton.setOnClickListener(new OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       firstNamePart = userNameFirst.getText().toString();
                                                       playerNameTextView.setText(firstNamePart);
                                                       lastNamePart = userNameFamily.getText().toString();
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

                                                               //keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                                               //tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                                               updatingStateOfFamily(family.getFamilyWealth(), family.getFamilyInfluence());
                                                               human.setFriends(Integer.parseInt(friendsAmountEditText.getText().toString()));
                                                               //Null point
                                                               Toast.makeText(MainActivity.this, "You have selected a Custom Family" + family,
                                                                       Toast.LENGTH_LONG).show();
                                                           } catch (Exception e) {
                                                               Toast.makeText(MainActivity.this, "A Custom Family with <=0 values",
                                                                       Toast.LENGTH_LONG).show();
                                                               initDialog(maxOverallWealth, maxInfluence);

                                                           }
                                                       } else {
                                                           try {
                                                               checkedItem = familyListView.getAdapter().getItem(familyListView.getCheckedItemPosition());
                                                               //Getting the String from the selected item from the Family List View
                                                               String selectedFamilyType = checkedItem.toString();
                                                               //This  try/catch block code catches the (Null Pointer) exception if nothing is selected


                                                               switch (selectedFamilyType) {

                                                                   case "Rich Family":
                                                                       father = new FamilyMember(true, familyName, Jobs.BUSINESSOWNER, countryOfUser, randomNum * 250, (int) (randomNum * 2.5));
                                                                       mother = new FamilyMember(false, familyName, Jobs.BANKER, countryOfUser, randomNum * 250, (int) (randomNum * 2.5));
                                                                       sister = new FamilyMember(false, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 2.5));
                                                                       brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 250, (int) (randomNum * 2.5));
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
                                                                       father = new FamilyMember(true, familyName, Jobs.BEGGER, countryOfUser, randomNum * 75, randomNum);
                                                                       mother = new FamilyMember(false, familyName, Jobs.PACKINGBOY, countryOfUser, randomNum * 75, randomNum);
                                                                       sister = new FamilyMember(false, familyName, Jobs.INTERN, countryOfUser, randomNum * 75, randomNum);
                                                                       brother = new FamilyMember(true, familyName, Jobs.NOJOB, countryOfUser, randomNum * 75, randomNum);
                                                                       family = new Family(familyName, brother, sister, father, mother);

                                                                       break;
                                                               }
                                                               //keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                                               //tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                                               updatingStateOfFamily(family.getFamilyWealth(), family.getFamilyInfluence());


                                                               Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString() + family,
                                                                       Toast.LENGTH_LONG).show();

                                                           } catch (Exception e) {
                                                               Toast.makeText(MainActivity.this, "A Family Type is needed",
                                                                       Toast.LENGTH_LONG).show();
                                                               initDialog(maxOverallWealth, maxInfluence);

                                                           }
                                                       }
                dialog.dismiss();

            }
        });
        initCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        familyListView.setAdapter(adapter);
        dialog.show();

    }

    private void init() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.message_dialog);
        //Initializing Buttons
        final Button initSelectionButton = (Button) dialog.findViewById(R.id.buttonSelect);
        final Button initCancelButton = (Button) dialog.findViewById(R.id.buttonCancel);
        //Set up First Name Text View
        final TextView tittleTextView = (TextView) dialog.findViewById(R.id.dialog_Title);
        //Setup the Last Name Text View
        final TextView messageTextView = (TextView) dialog.findViewById(R.id.dialog_Details);
        messageTextView.setText("Welcome!The purpose of this Android Application is to create your fantasy ideal life.." +
                ("\n") + "Create a character at the bottom of society." +
                ("\n") + "Progress this character through the world and accumulate influence, wealth, and associates." +
                ("\n") + "Don't hold back,accumulating everything the world has the offer is the key of winning the game" +
                ("\n") + "Good Luck!");
        //.setIcon(R.drawable.ic_launcher)
        tittleTextView.setText("IDEAL");
        initSelectionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        initCancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String selectedImagePath = cursor.getString(columnIndex);
                cursor.close();
                //ImageView imgView = (ImageView) findViewById(R.id.profileImageView);
                // Set the Image in ImageView after decoding the String
                profileImage.setImageURI(selectedImage);
            }
            if (requestCode == SELECT_LEVEL) {
                level = 1;//Reset Level so that it does not go above the Levels Adapter Levels
                maxOverallWealth = 10000000.0;//Reset these values so that the amount does no stack
                maxInfluence = 2000000;
                Boolean levelClicked = imageReturnedIntent.getExtras().getBoolean("intentFromIevelActivity");
                int levelSelected = imageReturnedIntent.getExtras().getInt("levelID");
                level = level + levelSelected;//Levels starts from Zero ,so 1 is added to each level
                maxOverallWealth = maxOverallWealth * level;
                maxInfluence = maxInfluence * level;
                Toast.makeText(MainActivity.this, "MainActivity:This Level was selected: " + level, Toast.LENGTH_SHORT).show();
                newLevel();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong" + e, Toast.LENGTH_LONG)
                    .show();
        }

    }
///->Family and Player Profile Dialogs
   private void profilePictureDialog() {


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
    overAllWealthTextViewprofileDialog.setText(getString(R.string.overallWelathTextView_text)+"\n"+currencyFormat.format(human.getOverAllWealth()));

    influenceTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
    influenceTextViewprofileDialog.setText(getString(R.string.influenceAmountTextView_String)+"\n"+Integer.toString(human.getInfluence()));

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
    private void profilePictureDialogFamilyTutorial() {

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
        overAllWealthTextViewprofileDialog.setText(getString(R.string.overallWelathTextView_text)+"\n"+currencyFormat.format(family.getFamilyWealth()));

        influenceTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.influenceTextViewprofileDialog);
        influenceTextViewprofileDialog.setText(getString(R.string.influenceAmountTextView_String)+"\n"+Integer.toString(family.getFamilyInfluence()));

        jobTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.jobTextViewprofileDialog);
        jobTextViewprofileDialog.setText("N/A");
        jobTextViewprofileDialog.setTextColor(888);

        countryTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.countryTextViewprofileDialog);
        countryTextViewprofileDialog.setText("Country:" + family.getFamilyCountry());

        neighbourhoodTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.neighbourhoodTextViewprofileDialog);
        neighbourhoodTextViewprofileDialog.setText("Neighbourhood:" + family.getFamilyNeighborhood());

        taxTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.taxTextViewprofileDialog);
        taxTextViewprofileDialog.setText("Tax:" + currencyFormat.format(family.getFamilyCountry().getTaxes()));

        userfriendsTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.userfriendsTextViewprofileDialog);
        userfriendsTextViewprofileDialog.setText(getResources().getString(R.string.friendsTextView_text)+":" + family.getFamilyFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText(getResources().getString(R.string.professionalAssociatesTextView_text)+ ":" +family.getFamilyProfessionalAssociates());


        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText(getResources().getString(R.string.worshippersTextView_text)+":" + family.getFamilyWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText("N/A");
        looksTextViewprofileDialog.setTextColor(888);

      alertDialogProfile.show();


    }
///->

    private Countries getThisCountry(String countryName) {

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

        } else if (countryName.equals(Countries.Heaven.getName())) {
            countryOfUser = Countries.Rany;

        } else {

            countryOfUser = Countries.NoCountry;

        }
        return countryOfUser;

    }

    private void chancesOfLife() {
        ///If you want to test any specific chance of life just place in the case number into switch bracket..the program will only run that switch
        //7randomNum
        switch (randomNum) {
            case 0:
              if(age<20){
                  chainString+="Your family got robbed";
                  wealth+=-50000;
                  influence+=-5000;

              }else{
                  chainString+="You got robbed";
                  wealth+=-50000;
                  influence+=-5000;

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


                        //CharDetails();
                    } else {
                        chainString+="\n"+"Your family is not very popular;it is beneficial to have more friends!!";
                    }

                } else {
                    if (human.getFriends() > 1000) {
                        chainString+="\n"+"You are very famous and this allows you to earn $500";
                        wealth+=500;


                        //CharDetails();
                    } else if (human.getFriends() > 500) {
                        chainString+="\n"+"You are famous  and this allows you to earn $100";
                        wealth+= 100;


                        //CharDetails();
                    } else {
                        chainString+="\n"+"You are not very popular;it is beneficial to have more friends!!";
                    }

                }


                break;
            case 2:
                // ad.setMessage("3");
                if (age < 20) {
                    if (family.getFamilyInfluence() > 100000) {
                        chainString+="\n"+"Your family has a high influence level which gives you access to the Banker and Business Owner jobs";


                        banker = true;
                        businessowner = true;
                    }else {
                        chainString += "\n" + "Your family's influence is not very high;try increasing it!!";
                    }

                }else {
                    if (human.getInfluence() > 100000) {
                        chainString += "\n" + "Your high influence level gives you access to the Independent and Sultan jobs" ;

                        independent = true;
                        sultan = true;
                        businessowner = true;

                    } else {
                        chainString += "\n" + "Your influence is not very high;try increasing it!!";
                    }
                }


                break;
            case 3:

                // ad.setMessage("4");
                if(socialisingWithFriends>4) {
                    chainString+="\n"+"You gained some friends because you have worked on your social skills";
                    friends+=100;
                }else if (socialisingWithFriends>7) {
                    chainString+="\n"+"Having many friends make ways for more friends.You gained 500 friends";
                   friends+=500;
                    //CharDetails();

                } else if ( socialisingWithFriends>9) {
                    chainString+="\n"+"Your chrisma naturally attracts people towards you.You gain 1000 friends";
                   friends+=1000;

                } else {
                    chainString+="\n"+"You have not socialize with enough people to access this bonus";

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


                } else if (human.getLooks() > 15) {
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
                if ( age > 20) {
                    healthUpdater(-10);
                    chainString+="\n"+"You got a life threatening disease and it takes away from  you life source";



                } else {
                    if (family.getFamilyWealth() < 100000) {
                        healthUpdater(-1);
                        chainString += "\n" + "Your family does not have the ability to feed you will and your health suffers for it";

                    }else if (family.getFamilyWealth() < 500000) {
                        healthUpdater(1);
                        chainString += "\n" + "Your family is doing well and this gives you a tiny health bonus";
                    }else{
                        healthUpdater(10);
                        chainString += "\n" + "Your family's wealth allows you access the best that life as to offer.You get a big health bonus";
                    }

                }
                 break;

            case 7:

                if(age<20){
                    chainString+="\n"+"Your parents got a promotion";
                    wealth+=500;
                    influence+=50;
                }else{
                    if(age%4==0&&age%6==0) {
                        chainString += "\n" + "You magically found a genie and he is able to grant you three wishes!";
                        genie = true;
                        System.out.println("Genie" + genie);
                     genieDoSomething();
                        informationalTextView.setText("This is the genie");
                    }
                    chainString += "\n" + "You magically found a genie but he got away from you";
                }

                break;

            case 8:
                // ad.setMessage("9");
                if (age > 20) {
                    wealth+= 500000;
                    chainString+="\n"+"You won the lottery so you will gain $500000";

                } else {
                    wealth+= 500000;
                   chainString+="\n"+"Your family won the lottery,gained $500000";

                }

                break;


            case 9:
                // ad.setMessage("10");
                chainString+="\n"+"Your recent trip to IDEAL Center of Academics added professional Associates to call onto later in life";
                professionalAssociates+= 500;
                //CharDetails();


                break;

            case 10:

                // ad.setMessage("11");
                chainString+="\n"+"You get a boost in influence, wealth, and friends!You are very lucky";
               wealth+= 500000;
               friends+=50000;
               influence+=5000;


                break;

            case 11:

                // ad.setMessage("12");
                chainString+="\n"+"You get a boost in professional associates, looks, and worshippers !You are very lucky";
               professionalAssociates+= 5000;
               looks+= 2;
               worshippers+= 5000;

                break;

            case 12:

                // ad.setMessage("13");
                chainString+="\n"+"You lose in  influence, wealth, and friends!You are very unlucky";
                wealth+=- 5000;
                friends+= - 500;
                influence+= - 500;
                break;

            case 13:
                chainString+="\n"+"You lose in professional associates, looks, and worshippers !You are very unlucky";
                 professionalAssociates= - 500;
                looks+= - 2;
                worshippers+= 50;
                    break;


        }


    }

///->Two Different Stages of IDEAL
    private void tutorialWithFamily(){
            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }else{
        chainString+="\n Nothing Happened this time";
    }
    }
    private void grownUpHuman(){
            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }else{
                chainString+="\n Nothing Happened this time";
            }
            System.out.println("Turn: " + age + "\n" + "|Adult" + "|" + human.getOverAllWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes());
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
//            if(human.getWorshippers()>maxOverallWealth&&workingOnPhysicalApp>=10&&socialisingWithFriends>=10&&schoolAttendanceAmount>=10) {
//                worshippersFollow = true;
//                worshippersFollow(true);
//                //job = Jobs.GOD;
//                heavenBoolean=true;
//                //schoolButton.setText(Html.fromHtml("<font color='red'>First line</font><br/><font color='blue'>Second line</font>"));
//                chainString+="\n"+"You have gained access to heaven and will gain worshippers for the next 10 years";
//            }
            //Once the player reaches 100000 in influence, the player will get paid $100000 every turn
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
//            if(human.getInfluence()>7000000&& human.getProfessionalAssociates()>=200000 && !influencActivation
//                    ||human.getFriends()>=1000000){
//                influencActivation=true;
//                chainString+="\n" +"You have gained 50000 in wealth";
//                chainString+="\n" + "You have gained enough Influence and Professional Associates that you will now gain a steady source of money";
//                wealth += 50000;
//            }
    }
///->

///->Calling a Thread for the application
    //Genie Thread
    private void genieDoSomething(){
            //chainString+="You magically found a genie and he is able to grant you three wishes!";//When this String is Displayed the genie would have already been completed
            LinearLayout linearLayout =(LinearLayout)findViewById(R.id.footer);
            linearLayout.setBackgroundColor(Color.parseColor("#3f3f3f"));
            continueButton.setEnabled(false);
            //continueButton.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.signatureColorOne)));
            //If there are no more wishes or all of the buttons are disabled either because the MAX was reached or the button was used..enable the user to go back to IDEALLifeProgram thread
            if(wishes==3||!changeCountryButton.isEnabled()&&!selectAJobButton.isEnabled()&&!schoolButton.isEnabled()&&!workOnPhysicalAppearanceButton.isEnabled()&&!socializeWithPeopleButton.isEnabled()){
                genie=false;
                wishes=0;
                chainString+="\n"+"Genie Left";
                //continueButton.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.purplebackground_color)));
                continueButton.setEnabled(true);
                buttonActivation(true);
                linearLayout.setBackgroundColor(Color.parseColor("#3f1930"));

            }
    }

    //MainThread
    private void IDEALLifeProgram(){
        //Animation
        final Animation animationFade = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animationFade.setDuration(500); // duration - half a second
        animationFade.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animationFade.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animationFade.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        // finally i use this code to execute the animation
        Animation animationShake = AnimationUtils.loadAnimation(this,R.anim.shake);
        animationShake.setDuration(250); // duration
        animationShake.setRepeatCount(Animation.INFINITE);//Repeat animation infinitely
        animationShake.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        RotateAnimation animRotate = new RotateAnimation(0f, 350f, 15f, 15f);
        animRotate.setInterpolator(new LinearInterpolator());
        animRotate.setRepeatCount(Animation.INFINITE);
        animRotate.setDuration(700);
        try {
            saveUserProfile();//Try to Save Profile
                 //Tutorial for the first 5 years;
                   if(age==1) {
                       initDialog(maxOverallWealth, maxInfluence);
                       /*init();
                       makeYourName();
                       selectAFamilyType();
                       selectACountry();*/
                       continueButton.startAnimation(animationFade);
                       informationalTextView.setText("Press the 'Continue Button' to begin the game");
                   } else if (age == 2) {
                       continueButton.clearAnimation();
                       //profileImage.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                       profileImage.startAnimation(animationFade);
                       informationalTextView.setText("Holding down the Image View will give you access to change your profile picture.Tapping it will send you to your Profile Information Dialog ");
                   }else if(age==3){
                       profileImage.clearAnimation();//Clear Previous Animation..Then Start the next
                       age_Turn_textView.startAnimation(animationShake);
                       informationalTextView.setText("Your turn or age. ");
                   }else if(age==4){
                       age_Turn_textView.clearAnimation();//Clear Previous Animation..Then Start the next
                       healthProgressBar.startAnimation(animationFade);
                       informationalTextView.setText("After age 20;your health will gradually decrese. Once your health reaches 0 before you have created  your ideal life, you lose the game ");
                   }else if(age==5){
                       healthProgressBar.clearAnimation();//Clear Previous Animation..Then Start the next
                       informationalTextView.setText("These are 'ACTION BUTTONS'.In the family mode 'ACTION BUTTONS'  are disabled" +
                                       ",but once age 20 is reached then you will have access to one 'ACTION'" +
                                       "per turn.Note:The 'Job Button' can be held down to see the available jobs ");
                       changeCountryButton.startAnimation(animationShake);
                       selectAJobButton.startAnimation(animationShake);
                       schoolButton.startAnimation(animationShake);
                       workOnPhysicalAppearanceButton.startAnimation(animationShake);
                       socializeWithPeopleButton.startAnimation(animationShake);
                   } else if (age < 19 && age > 5) {
                       changeCountryButton.clearAnimation();
                       selectAJobButton.clearAnimation();
                       schoolButton.clearAnimation();
                       workOnPhysicalAppearanceButton.clearAnimation();
                       socializeWithPeopleButton.clearAnimation();//Clear Previous Animation
                       informationalTextView.setText("IDEAL:Tutorial With Family/Initial State" + "\n" + "This is the Family View where until 20,random chances will occur .");
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
                           tax = family.getFamilyCountry().getTaxes();
                           overallWealthTextView.setText(currencyFormat.format(family.getFamilyWealth()));
                           String influenceAmountString = Double.toString(family.getFamilyInfluence());
                           influenceTextView.setText(influenceAmountString);
                       }else{
                          informationalTextView.setText("Turn: " + age + "\n" + "|Family" + "|Wealth:" + family.getFamilyWealth() + "|Influence:" +family.getFamilyInfluence()+ "|Friends:" + family.getFamilyFriends() + "|" + "|Worshippers: " + family.getFamilyWorshippers()
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
                          System.out.println("Shown is false");
                          //buttonActivation(true);
                          if (human.getCountry().equals(Countries.NoCountry)) {//If the game has just started, the human should not have a country and therefore I manually update the human Country
                              human.setCountries(family.getFamilyCountry());
                          }
                           keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), job, family.getFamilyCountry().getTaxes()
                                      , 0, family.getFamilyWorshippers(), family.getFamilyFriends(), 0);
                          informationalTextView.setText("IDEAL:Adult Life" + "\n" + "You are now an adult an inherited everything from your family. Now you will have to work on your own to secure your IDEAL Life" + "\n"
                                  + "If you can reach $10,000,000 in wealth or 2,000,000 in influence;you would have won the game!!"
                                  + "\n There are also many other features to unlock in the game"
                                  + "\n Only one Action can be done per turn so choose wisely"
                                  + "\n Create your IDEAL Life");
                           shownOne = true;
                       }else{
                          System.out.println("Shown is true");
                          if(healthProgressBar.getProgress()>=1) {
                              //Lose One Health Every Turn
                              healthUpdater(-1);
                          }else{
                              chainString="\n You have died";
                              buttonActivation(false);
                              continueButton.setEnabled(false);
                          }
                          //healthProgressBar.setProgress(100-age);
                          //System.out.println("Progress Bar:"+healthProgressBar.getProgress());
                          //When the user did these action 10 times;the buttons should be disabled and set the text of button to MAX..The user must reach max numbers in socialisingWithFriends and workingOnPhysicalApp before disabling the school Button
                          //because if school reaches max first then the user may not be able to access all of the jobs
                           //Let the User make a life Decision//Disable after footer button press
                            buttonActivation(true);
                            grownUpHuman();
                          keepStatsUpToDate(wealth*1.0,influence,job,human.getCountry().getTaxes(),looks,worshippers,friends,professionalAssociates);
                          //Reset stats so that there are no further stacking up
                          //Chances of Life-->Recommendation
                          informationalTextView.setText(
                                  chainString+"\n"+
                                    "What you have earned:\n"+
                                     "Wealth: " +wealth +"Income: "+human.getJob().getIncome()+"\n"+
                                     "Influence: " +influence+"Influence from Job: "+human.getJob().getInfluence() +"\n"+
                                      "Friends: " +  friends +"\n"+
                                      "Professional Associates: "+professionalAssociates+"\n"+
                                      "Looks: " +looks +"\n"+
                                      "Worshippers: " +worshippers +"\n"+
                                       "\nRecommendation\n"+
                                  recommendation()
                                  );
                           //Reset the chain so that previous messages are removed
                           chainString="";
                          wealth=0;
                          influence=0;
                          job=human.getJob();
                          looks=0;
                          worshippers=0;
                          friends=0;
                          professionalAssociates=0;
                         }
                   }
            } catch (Throwable t) {
                   System.out.println("Halted due to an error: " + t);
            }

}
    //These methods is for when the application is paused or temporally closed
///->
    @Override
    protected void onResume() {
        super.onResume();//Resume normal game
    }
    @Override
    protected void onPause() {
        //Save File
        saveUserProfile();
        super.onPause();
    }
    @Override
    protected void onStop(){
        //Save File
        saveUserProfile();
        super.onStop();


    }

///-> Enabling and disabling buttons
    private void buttonActivation(boolean activate){
        if(socialisingWithFriends>=10) {
            socializeWithPeopleButton.setEnabled(false);
            //socialisingWithFriendsTextView.setBackgroundColor(Color.parseColor("#FF0000"));
            socialisingWithFriendsTextView.setText("MAX");
        }else {
            socializeWithPeopleButton.setEnabled(activate);
        }
            //HTML may be a limitation in android code
            //socializeWithPeopleButton.setText(R.string.socialize_button + "\n" + Html.fromHtml("<font color='red'>MAX</font>"));
            if (workingOnPhysicalApp >= 10) {
                workOnPhysicalAppearanceButton.setEnabled(false);
                //Set TextView color to Red and TextView to MAX
                //workingOnPhysicalAppTextView.setBackgroundColor(Color.parseColor("#FF0000"));
                workingOnPhysicalAppTextView.setText("MAX");
            }else {
                workOnPhysicalAppearanceButton.setEnabled(activate);
            }
                //workOnPhysicalAppearanceButton.setText(R.string.physical_Button + "\n" + Html.fromHtml("<font color='red'>MAX</font>"));
                if (schoolAttendanceAmount >= 10) {
                    schoolButton.setEnabled(false);
                    //schoolAttendanceAmountTextView.setBackgroundColor(Color.parseColor("#FF0000"));
                    schoolAttendanceAmountTextView.setText("MAX");
                }else {
                    schoolButton.setEnabled(activate);
                }

        changeCountryButton.setEnabled(activate);

        selectAJobButton.setEnabled(activate);

    }
    private void countryButtonActivation(boolean irada,boolean itican,boolean albaq,boolean trinentina, boolean alibico, boolean ugeria, boolean portada,
    boolean kuwador,boolean ukrark,boolean rany, boolean heaven){
       countryButtonIrada.setEnabled(irada); //Irada
       countryButtonItican.setEnabled(itican);//Itican
       countryButtonAlbaq.setEnabled(albaq);//Albaq
       countryButtonTrinentina.setEnabled(trinentina);//Trinentina
       countryButtonAlbico.setEnabled(alibico);//Albico
       countryButtonUgeria.setEnabled(ugeria);//Ugeria;
       countryButtonPortada.setEnabled(portada);  //Portada;
       countryButtonKuwador.setEnabled(kuwador);//Kuwador;
       countryButtonUkrark.setEnabled(ukrark);  //Ukrark;
       countryButtonRany.setEnabled(rany); //Rany;
       countryButtonHeaven.setEnabled(heaven); //Heaven

    }//Deactivate Pressed button to show a selection was made
    private void jobButtonActivation(boolean beggerA,boolean vagrantA,boolean internA,boolean packingboyA,boolean firefighterA,boolean bankerA,boolean scientistA
    ,boolean businessowerA,boolean independentA, boolean kingA, boolean sultanA,boolean godA,boolean omeageA){
       jobButtonBeggar.setEnabled(beggerA); //Begger
       jobButtonVagrant.setEnabled(vagrantA);//Vagrant
       jobButtonIntern.setEnabled(internA);//Intern
       jobButtonPackingBoy.setEnabled(packingboyA);//PackingBoy
       jobButtonFirefighter.setEnabled(firefighterA);//Firefighter
       jobButtonBanker.setEnabled(bankerA);//BANKER;
       jobButtonScientist.setEnabled(scientistA);  //Scientist;
       jobButtonBusinessOwner.setEnabled(businessowerA);//BusinessOwner;
       jobButtonIndependent.setEnabled(independentA);  //Independent;
       jobButtonKing.setEnabled(kingA);  //KING;
       jobButtonSultan.setEnabled(sultanA); //SULTAN
       jobButtonGod.setEnabled(godA);//GOD
       jobButtonOmega.setEnabled(omeageA);  //OMEGA
    }
///->
    private String recommendation(){
        String recommendation="";

//User should be working on gatting a job by the age of 20
        if(human.getJob()==Jobs.NOJOB && age>20){
            recommendation+="\n You need to get a job to earn income on very turn.Note:Only Certain jobs are unlocked for each Country |";
        }

//If the User does not have a certain amount of wealth then suggest something useful to the User
        if(human.getOverAllWealth()<maxInfluence&&age>40){
            recommendation+="\n|Your wealth is very low|";
        }else{
            recommendation+="\n|Your wealth is very low;try to limit your actions and skip actions.|";
        }
//The User should be working out
        if(workingOnPhysicalApp==0&&age>25){
            recommendation += "\n|You need to work Out|";
        }else if(workingOnPhysicalApp < schoolAttendanceAmount &&workingOnPhysicalApp>=10 &&schoolAttendanceAmount>=10){
            recommendation += "\n|You need to work-out to unlock certain jobs as well.Physical Health is important|";
        }
//The User should Socilaize with People
        if(socialisingWithFriends>0&&age>25){
            recommendation+="\n|Socializing with people is a great skill to cultivate|";
        }else if (socialisingWithFriends<schoolAttendanceAmount&&socialisingWithFriends>=10 &&schoolAttendanceAmount>=10){
                recommendation+="\n|Academics is great but working on your Social skills is important|";
        }
//The User should improve looks to get a better chance of life
        if(human.getLooks()>3&& age>40){
            recommendation+="\n|Working out will improve you physical Appearance.|";
        }
//Warn user about their health
        if(healthProgressBar.getProgress()<40){
            recommendation+= "\n|Your health is getting low.Choose your actions wisely.|";
        }
        return recommendation;
    }

///->Save and Read User Profile
    private void saveUserProfile(){
        String data;
        /*if(reset){
            firstNamePart="NOName";                                     //First Name
            lastNamePart="NOName";                                      //lastName
            data= Integer.toString(100) + '\n' +//Health Bar Progress
                    family.getFamilyCountry().getName() + '\n' +                 //Family Country
                    Integer.toString(0) + '\n' +        //Family Friends
                    Integer.toString(0) + '\n' +//Family Professional Associates
                    Integer.toString(0) + '\n' +       //FamilyWorshippers
                    Double.toString(0) + '\n' +             //Family Wealth
                    Integer.toString(0) + '\n' +         //Family Influence
                    Integer.toString(0) + '\n' +              //School Attendance
                    Integer.toString(0) + '\n' +                //Physical Appearance
                    Integer.toString(0) + '\n' +             //Socialize
                    //firstNamePart + '\n' +                                        //First Name
                    //lastNamePart + '\n' +                                         //Last Name
                    Jobs.NOJOB.getName() + '\n' +                               //Job
                    human.getCountryString() + '\n' +                                //Human Country
                    Integer.toString(0) + '\n' +                     //Human Friends
                    Integer.toString(0) + '\n' +      //Human Professional Associates
                    Integer.toString(0) + '\n' +                 //Human Worshippers
                    Integer.toString(0) + '\n' +                       //Human Looks
                    Double.toString(0) + '\n' +                //Human Wealth
                    Integer.toString(0) + '\n' +                   //Human Influence
                    Integer.toString(0)     + '\n' +      // +'\n'+                                   //Age
                    Boolean.toString(actionItemTaken)                ///Keep Track on whether an Action Button was taken or not
            //"DONE"
            ;
            reset=false;
        }else {*/
            data= Integer.toString(healthProgressBar.getProgress()) + '\n' +//Health Bar Progress
                    family.getFamilyCountry().getName() + '\n' +                 //Family Country
                    Integer.toString(family.getFamilyFriends()) + '\n' +        //Family Friends
                    Integer.toString(family.getFamilyProfessionalAssociates()) + '\n' +//Family Professional Associates
                    Integer.toString(family.getFamilyWorshippers()) + '\n' +       //FamilyWorshippers
                    Double.toString(family.getFamilyWealth()) + '\n' +             //Family Wealth
                    Integer.toString(family.getFamilyInfluence()) + '\n' +         //Family Influence
                    Integer.toString(schoolAttendanceAmount) + '\n' +              //School Attendance
                    Integer.toString(workingOnPhysicalApp) + '\n' +                //Physical Appearance
                    Integer.toString(socialisingWithFriends) + '\n' +             //Socialize
                    firstNamePart + '\n' +                                        //First Name
                    lastNamePart + '\n' +                                         //Last Name
                    human.getJob().getName() + '\n' +                               //Job
                    human.getCountryString() + '\n' +                                //Human Country
                    Integer.toString(human.getFriends()-family.getFamilyFriends()) + '\n' +                     //Human Friends
                    Integer.toString(human.getProfessionalAssociates()-family.getFamilyProfessionalAssociates()) + '\n' +      //Human Professional Associates
                    Integer.toString(human.getWorshippers()-family.getFamilyWorshippers()) + '\n' +                 //Human Worshippers
                    Integer.toString(human.getLooks()) + '\n' +                       //Human Looks
                    Double.toString(human.getOverAllWealth()-family.getFamilyWealth()) + '\n' +                //Human Wealth
                    Integer.toString(human.getInfluence()-family.getFamilyInfluence()) + '\n' +                   //Human Influence
                    Integer.toString(age)  + '\n' +      // +'\n'+                                   //Age
                    Boolean.toString(actionItemTaken)                ///Keep Track on whether an Action Button was taken or not
                    //"DONE"
                    ;
        //}
        System.out.println("-------------------------------------");
        System.out.println(data);
        System.out.println("-------------------------------------");

        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name,MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            //Toast.makeText(this,"Message Saved", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readFromFile() {
        if(reset){
            System.out.println("Reset");
            age = 1;//The Main Program is based off of age so if the age is 0 then it will start from the beginning overwriting data
            //healthProgressBar.setProgress(100);//Health Bar Progress
            //family.setCountry(getThisCountry(lines.get(1)));               //Family Country
            family.setFamilyFriends(0);       //Family Friends
            family.setFamilyProfessionalAssociates(0); //Family Professional Associates
            family.setFamilyWorshippers(0);      //FamilyWorshippers
            family.setFamilyWealth(0);           //Family Wealth
            family.setFamilyInfluence(0);          //Family Influence
            schoolAttendanceAmount = 0;              //School Attendance
            workingOnPhysicalApp = 0;           //Physical Appearance
            socialisingWithFriends = 0;        //Socialize
            firstNamePart = "";                                   //First Name
            lastNamePart = "";                                   //Last Name
            job = (Jobs.NOJOB);
            human.setJob(job);                        //Job
            human.setCountries(Countries.NoCountry);            //Human Country
            human.setFriends(0);              //Human Friends
            human.setProfessionalAssociates(0);   //Human Professional Associates
            human.setWorshippers(0);     //Human Worshippers
            human.setLooks(0);           //Human Looks
            human.setOverAllwealth(0);   //Human Wealth
            human.setInfluence(0);       //Human Influence
           continueButton.setEnabled(true);//If the User last game ended with death the Continue Button must be enabled for the Reset Game
            healthUpdater(100);//To make sure health Progress Bar text match the Progress of the Progress Bar
            socialisingWithFriendsTextView.setText(String.valueOf(socialisingWithFriends));//These values must be set this way because they will retain the old values until the update from the method.
            schoolAttendanceAmountTextView.setText(String.valueOf(schoolAttendanceAmount));
            workingOnPhysicalAppTextView.setText(String.valueOf(workingOnPhysicalApp));
            age_Turn_textView.setText("Age: " + age);
            playerNameTextView.setText(firstNamePart);
            jobTextView.setText(human.getJob().getName());
            tax = human.getCountry().getTaxes();
            overallWealthTextView.setText(currencyFormat.format(human.getOverAllWealth()));
            String influenceAmountString = Double.toString(human.getInfluence());
            influenceTextView.setText(influenceAmountString);
            jobTextView.setText(getString(R.string.getJob_text) + ":"  + human.getJob());
            countryTextView.setText(getString(R.string.getCountry_text) + ":" +  human.getCountryString());
            taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));
             //IDEALLifeProgram();
         }else {
            try {
                String Message;
                ArrayList<String> lines = new ArrayList<>();
                FileInputStream fileInputStream = openFileInput(file_name);
                InputStreamReader inputStreamReader = new InputStreamReader((fileInputStream));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuffer = new StringBuilder();
                int a=0;
                while ((Message = bufferedReader.readLine()) != null) {
                    lines.add(Message);
                    a++;
                    System.out.println("Count:"+ a);

                    //age = Integer.parseInt(lines.get(18));
                    //System.out.println(age);
                    //stringBuffer.append(Message).append("\n");

                    //age= Integer.parseInt(lines[0]);
                    //System.out.println("Age:"+ age);
                    //String[] lines=stringBuffer.append(Message).append("\n");
                /*for(String s: lines){
                    System.out.println("Content = " + s);
                    //System.out.println("Content = " + Message);
                    //System.out.println("Length = " + s.length());
                }*/
                }
                System.out.println(lines.get(1));
                for (int i = 0; i < 21; i++) {
                    //lines.add(bufferedReader.readLine());
                    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$ \n" + lines.get(i) + "\n $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                }
                System.out.println("Array Length"+lines.size());
                healthProgressBar.setProgress(Integer.parseInt(lines.get(0)));//Health Bar Progress
                family.setCountry(getThisCountry(lines.get(1)));               //Family Country
                family.setFamilyFriends(Integer.parseInt(lines.get(2)));       //Family Friends
                family.setFamilyProfessionalAssociates(Integer.parseInt(lines.get(3))); //Family Professional Associates
                family.setFamilyWorshippers(Integer.parseInt(lines.get(4)));      //FamilyWorshippers
                family.setFamilyWealth(Double.parseDouble(lines.get(5)));           //Family Wealth
                family.setFamilyInfluence(Integer.parseInt(lines.get(6)));          //Family Influence
                schoolAttendanceAmount = Integer.parseInt(lines.get(7));              //School Attendance
                workingOnPhysicalApp = Integer.parseInt(lines.get(8));           //Physical Appearance
                socialisingWithFriends = Integer.parseInt(lines.get(9));        //Socialize
                firstNamePart = lines.get(10);                                   //First Name
                lastNamePart = lines.get(11);                                   //Last Name
                job = (getJobFromString(lines.get(12)));                         //Job
                human.setCountries(getThisCountry(lines.get(13)));            //Human Country
                human.setFriends(Integer.parseInt(lines.get(14)));              //Human Friends
                human.setProfessionalAssociates(Integer.parseInt(lines.get(15)));   //Human Professional Associates
                human.setWorshippers(Integer.parseInt(lines.get(16)));     //Human Worshippers
                human.setLooks(Integer.parseInt(lines.get(17)));           //Human Looks
                human.setOverAllwealth((Double.parseDouble(lines.get(18))));   //Human Wealth
                human.setInfluence(influence=(Integer.parseInt(lines.get(19))));       //Human Influence
                age = Integer.parseInt(lines.get(20));                       //Age
                actionItemTaken= Boolean.parseBoolean(lines.get(21));
                if (actionItemTaken && age >= 20 || age < 20) {//If user Made an Action Item before the closing the App..then lock the buttons when the user comes back..if not....buttons activate
                    buttonActivation(false);
                }else{
                    buttonActivation(true);
                }
                //Setting Views on Screen
                age_Turn_textView.setText("Age: " + age);
                playerNameTextView.setText(firstNamePart);
                jobTextView.setText(human.getJob().getName());
                if(age>20) {
                    tax = human.getCountry().getTaxes();
                    overallWealthTextView.setText(currencyFormat.format(human.getOverAllWealth()));
                    String influenceAmountString = Double.toString(human.getInfluence());
                    influenceTextView.setText(influenceAmountString);
                    jobTextView.setText(getString(R.string.getJob_text) + ":" + human.getJob());
                    countryTextView.setText(getString(R.string.getCountry_text) + ":" + human.getCountryString());
                    taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));
                }else{
                    tax = family.getFamilyCountry().getTaxes();
                    overallWealthTextView.setText(currencyFormat.format(family.getFamilyWealth()));
                    String influenceAmountString = Double.toString(family.getFamilyInfluence());
                    influenceTextView.setText(influenceAmountString);

                    job = Jobs.NOJOB;//When Updating Values..these ones should be at default because the human is less then 20 and should not have anything
                    jobTextView.setText(getString(R.string.getJob_text) + ":" + human.getJob());

                    countryOfUser = family.getFamilyCountry();//When Updating Values..these ones should be at default because the human is less then 20 and should not have anything
                    countryTextView.setText(getString(R.string.getCountry_text) + ":" + family.getFamilyCountry().getName());
                    taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));
                }


                //Make sure these values are at least greater than zero before setting them
                if (socialisingWithFriends > 0) {
                    socialisingWithFriendsTextView.setText(String.valueOf(socialisingWithFriends));
                }
                if (schoolAttendanceAmount > 0) {
                    schoolAttendanceAmountTextView.setText(String.valueOf(schoolAttendanceAmount));
                }
                if (workingOnPhysicalApp > 0) {
                    workingOnPhysicalAppTextView.setText(String.valueOf(workingOnPhysicalApp));
                }
          //informationalTextView.setText(stringBuffer.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Reset all the additional values for a  returning and a new reset Game
        System.out.println("Reseting the Values......................................");
        wealth=0;influence=0;tax=0;looks=0;worshippers=0;friends=0;professionalAssociates=0;
        System.out.println(".............................................................");

    }
///->
    private Jobs getJobFromString(String jobA){
        Jobs tempJob=Jobs.NOJOB;
            switch(jobA){
            case "Begger":
                tempJob=Jobs.BEGGER;
                break;
            case "Vagrant":
                tempJob=Jobs.VAGRANT;
                break;
            case "Intern":
                tempJob=Jobs.INTERN;
                break;
            case "Packingboy":
                tempJob=Jobs.PACKINGBOY;
                break;
            case "Firefighter":
                tempJob=Jobs.FIREFIGHTER;
                break;
            case "Banker":
                tempJob=Jobs.BANKER;
                break;
            case "Scientist":
                tempJob=Jobs.SCIENTIST;
                break;
            case "Independent":
                tempJob=Jobs.INDEPENDENT;
                break;
            case "BusinessOwner":
                tempJob=Jobs.BUSINESSOWNER;
                break;
            case "King":
                tempJob=Jobs.KING;
                break;
            case "Sultan":
                tempJob=Jobs.SULTAN;
                break;
            case "GOD":
                tempJob=Jobs.GOD;
                break;
            case "OMEGA":
                tempJob=Jobs.OMEGA;
                break;

        }
        System.out.println("TempJob: "+tempJob);
       return tempJob;
    }

    //This will contain the Code for a Proper Start of a New Level
    private void newLevel() {
        //Reset all the additional values for a  returning and a new reset Game
        System.out.println("Reseting the Values for a New Level......................................");
        healthUpdater(100);//To make sure health Progress Bar text match the Progress of the Progress Bar
        //calculateTheProgressBarPercentage(0.0,0);
        overallWealthProgressBar.setProgress(0);
        influenceProgressBar.setProgress(0);
        wealth = 0;
        influence = 0;
        tax = 0;
        looks = 0;
        worshippers = 0;
        friends = 0;
        professionalAssociates = 0;

        //Family
        tax = family.getFamilyCountry().getTaxes();
        overallWealthTextView.setText(currencyFormat.format(family.getFamilyWealth()));
        String influenceAmountString = Double.toString(family.getFamilyInfluence());
        influenceTextView.setText(influenceAmountString);
        family = new Family();

        //Human
        human = new Human();
        job = Jobs.NOJOB;//When Updating Values..these ones should be at default because the human is less then 20 and should not have anything
        jobTextView.setText(getString(R.string.getJob_text) + ":" + human.getJob());
        human.setJob(job);
        human.setIncome(job.getIncome());
        human.setOverAllwealth(0);
        human.setInfluence(0);
        human.setProfessionalAssociates(0);
        human.setFriends(0);
        human.setLooks(0);
        human.setWorshippers(0);

        countryOfUser = family.getFamilyCountry();//When Updating Values..these ones should be at default because the human is less then 20 and should not have anything
        countryTextView.setText(getString(R.string.getCountry_text) + ":" + family.getFamilyCountry().getName());
        taxTextView.setText(getString(R.string.tax_text) + ":" + currencyFormat.format(tax));

        socialisingWithFriends = 0;
        schoolAttendanceAmount = 0;
        workingOnPhysicalApp = 0;

        workingOnPhysicalAppTextView.setText(String.format(Locale.US, "%02d", workingOnPhysicalApp));
        schoolAttendanceAmountTextView.setText(String.format(Locale.US, "%02d", schoolAttendanceAmount));
        socialisingWithFriendsTextView.setText(String.format(Locale.US, "%02d", socialisingWithFriends));

        System.out.println(".............................................................");
        //Objectives for Navigation_header Layout
        objectivesNavigationTextView = (TextView) header.findViewById(R.id.objectivesNavigationTextView);
        wealthGoalNavigationTextView = (TextView) header.findViewById(R.id.wealthGoalNavigationTextView);
        influenceGoalNavigationTextView = (TextView) header.findViewById(R.id.influenceGoalNavigationTextView);
        objectivesNavigationTextView.setText("Level:" + level);
        wealthGoalNavigationTextView.setText(currencyFormat.format(maxOverallWealth));
        influenceGoalNavigationTextView.setText(Integer.toString(maxInfluence));
        age = 1;
        IDEALLifeProgram();
        saveUserProfile();
    }
//->
 }

