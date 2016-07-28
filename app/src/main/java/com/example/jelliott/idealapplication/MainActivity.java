package com.example.jelliott.idealapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.support.v7.view.ContextThemeWrapper;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
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
import android.widget.ScrollView;
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
    private TextView alertDialog_content_textView;

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
    private int mProgressStatusOverAllWealth = 0, mProgressStatusInfluence = 0,mHealthProgress=0;
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

    private Human human;
    private int age = 0, schoolAttendanceAmount = 0, socialisingWithFriends = 0, workingOnPhysicalApp = 0 , wishes = 0;
    private int value, looks = 0, worshippers = 0, friends = 0, professionalAssociates = 0;
    private double wealth = 0.0, tax = 0.0;

    private Family family;
    private Countries countryOfUser;
    private Jobs job=Jobs.NOJOB;
    private FamilyMember mother, sister, brother, father;
    private String familyName, firstNamePart, lastNamePart,chainString="";
    Random rand = new Random();
    int randomNum = rand.nextInt((50 - 1) + 1);
    private Boolean banker = false, independent = false, businessowner = false, king = false, intern = false,shown=false,shownOne=false,genie=false/*Genie boolean will only be used in the jobButton command which will allow the user to choose any job*/;
    private Boolean begger = false;
    private Boolean vagrant = false;
    private Boolean packingboy = false;
    private Boolean firefighter = false;
    private Boolean scientist = false;
    private Boolean god = false;
    private Boolean sultan = false;
    private Boolean worshippersFollow = false;
    private Boolean omega = false;
    private Boolean worshippersActivation = false;
    private Boolean influencActivation = false;
    private Boolean heavenBoolean = false;
    private int tempNum = age - 10;
    private String savedata="NothingInFile";


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
            selectAJobButton = (Button) findViewById(R.id.jobButton);
            selectAJobButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    selectAJob();
                }
            });

            selectAJobButton.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   availableJobs();
                    return false;
                }
            });
            //Go To School Button
            schoolButton = (Button) findViewById(R.id.schoolButton);
            schoolButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    goToSchool();
                }
            });
            //Work on Physical Appearance
            workOnPhysicalAppearanceButton = (Button) findViewById(R.id.physicalButton);
            workOnPhysicalAppearanceButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    workOnPhysical();
                }
            });
            //Socialize with People
            socializeWithPeopleButton = (Button) findViewById(R.id.socializeButton);
            socializeWithPeopleButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    socializeWithPeople();
                }
            });

            continueButton = (Button) findViewById(R.id.continueButton);
            continueButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (human.getOverAllWealth() < 10000000 && human.getInfluence() < 2000000) {
                        IDEALLifeProgram();
                        continueButton.clearAnimation();
                        /*if(age==2){
                            profileImage.clearAnimation();
                        }else if(age==3){
                            age_Turn_textView.clearAnimation();
                        }else if(age==4){
                            healthProgressBar.clearAnimation();
                        }else{
                            changeCountryButton.clearAnimation();

                            selectAJobButton.clearAnimation();

                            schoolButton.clearAnimation();

                            workOnPhysicalAppearanceButton.clearAnimation();

                            socializeWithPeopleButton.clearAnimation();
                        }*/
                    } else {
                        ///The game is finished at this point
                        informationalTextView.setText("You have created your ideal life at age:" + age + "  CONGRATULATIONS");
                        buttonActivation(false);


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
            //informationalTextView.setText("Welcome and Good Luck!");

            buttonActivation(false);
        if(readFromFile()) {

            //Open the File
            readFromFile();
            System.out.println("Filed has been pulled successfully");
        }else{
            IDEALLifeProgram();
            System.out.println("There is no file");

        }


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

        }if(id==R.id.action_reset){
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
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
        jobButtonBeggar.setText(Jobs.BEGGER.getName() + '\n' + "Income:$" + Jobs.BEGGER.getIncome() + "\n Influence:" + Jobs.BEGGER.getInfluence());
        //"@android:style/Holo.SegmentedButton"
        jobButtonBeggar.setBackgroundResource(R.drawable.button_style);
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
        jobButtonVagrant.setText(Jobs.VAGRANT.getName() +'\n'+"Income:$"+Jobs.VAGRANT.getIncome()+"\n Influence:"+Jobs.VAGRANT.getInfluence());
        jobButtonVagrant.setBackgroundResource(R.drawable.button_style);
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
        jobButtonIntern.setText(Jobs.INTERN.getName() +'\n'+"Income:$"+Jobs.INTERN.getIncome()+"\n Influence:"+Jobs.INTERN.getInfluence());
        jobButtonIntern.setBackgroundResource(R.drawable.button_style);
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
        jobButtonPackingBoy.setText(Jobs.PACKINGBOY.getName() +'\n'+"Income:$"+Jobs.PACKINGBOY.getIncome()+"\n Influence:"+Jobs.PACKINGBOY.getInfluence());
        jobButtonPackingBoy.setBackgroundResource(R.drawable.button_style);
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
        jobButtonFirefighter.setText(Jobs.FIREFIGHTER.getName() +'\n'+"Income:$"+Jobs.FIREFIGHTER.getIncome()+"\n Influence:"+Jobs.FIREFIGHTER.getInfluence());
        jobButtonFirefighter.setBackgroundResource(R.drawable.button_style);
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
        jobButtonBanker.setText(Jobs.BANKER.getName() +'\n'+"Income:$"+Jobs.BANKER.getIncome()+"\n Influence:"+Jobs.BANKER.getInfluence());
        jobButtonBanker.setBackgroundResource(R.drawable.button_style);
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
        jobButtonScientist.setText(Jobs.SCIENTIST.getName() +'\n'+"Income:$"+Jobs.SCIENTIST.getIncome()+"\n Influence:"+Jobs.SCIENTIST.getInfluence());
        jobButtonScientist.setBackgroundResource(R.drawable.button_style);
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
        jobButtonIndependent.setText(Jobs.INDEPENDENT.getName() +'\n'+"Income:$"+Jobs.INDEPENDENT.getIncome()+"\n Influence:"+Jobs.INDEPENDENT.getInfluence());
        jobButtonIndependent.setBackgroundResource(R.drawable.button_style);
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
        jobButtonBusinessOwner.setText(Jobs.BUSINESSOWNER.getName() +'\n'+"Income:$"+Jobs.BUSINESSOWNER.getIncome()+"\n Influence:"+Jobs.BUSINESSOWNER.getInfluence());
        jobButtonBusinessOwner.setBackgroundResource(R.drawable.button_style);
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
        jobButtonKing.setText(Jobs.KING.getName() +'\n'+"Income:$"+Jobs.KING.getIncome()+"\n Influence:"+Jobs.KING.getInfluence());
        jobButtonKing.setBackgroundResource(R.drawable.button_style);
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
        jobButtonSultan.setText(Jobs.SULTAN.getName() +'\n'+"Income:$"+Jobs.SULTAN.getIncome()+"\n Influence:"+Jobs.SULTAN.getInfluence());
        jobButtonSultan.setBackgroundResource(R.drawable.button_style);
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
        jobButtonOmega.setText("\u03A9" + "mega" +'\n'+"Income:$"+Jobs.OMEGA.getIncome()+"\n Influence:"+Jobs.OMEGA.getInfluence());
        jobButtonOmega.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonOmega);
        jobButtonOmega.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                professionalAssociates += 3000;
                friends += 2000;
                job=Jobs.OMEGA;

            }
        });

        final Button jobButtonGod=new Button(this);
        jobButtonGod.setText(Jobs.GOD.getName() +'\n'+"Income:$"+Jobs.GOD.getIncome()+"\n Influence:"+Jobs.GOD.getInfluence());
        jobButtonGod.setBackgroundResource(R.drawable.button_style);
        //layout.addView(jobButtonGod);
        jobButtonGod.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            professionalAssociates += 1500;
            friends += 1000;
            job = Jobs.GOD;

        }
    });
        //Make the AlertDialog Scrollable->Maybe


        //If statements to show only certain Buttons
        if(genie) {//If the genie is active..this will show up if the jobButton is selected..Genie can not be activated until the user is >=20

            layout.addView(jobButtonBeggar); //Begger
            layout.addView(jobButtonVagrant);//Vagrant
            layout.addView(jobButtonIntern);//Intern
            layout.addView(jobButtonPackingBoy);//PackingBoy
            layout.addView(jobButtonFirefighter);//Firefighter
            layout.addView(jobButtonBanker);//BANKER;
            layout.addView(jobButtonScientist);  //Scientist;
            layout.addView(jobButtonBusinessOwner);//BusinessOwner;
            layout.addView(jobButtonIndependent);  //Independent;
            layout.addView(jobButtonKing);  //KING;
            layout.addView(jobButtonSultan); //SULTAN
            layout.addView(jobButtonGod);//GOD
            layout.addView(jobButtonOmega);  //OMEGA

        }else if (age < 20 || schoolAttendanceAmount<1) {
            if (packingboy){layout.addView(jobButtonPackingBoy);}//PackingBoy
            if (intern){layout.addView(jobButtonIntern);}//Intern
        }else{
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

                if(god){ layout.addView(jobButtonGod);}//GOD

                if(god&&sultan&&king){layout.addView(jobButtonOmega);}  //OMEGA

            }

        }

        final ScrollView scrollView = new ScrollView(this);
        scrollView.addView(layout);
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
       ad
                .setMessage("Select A Job. \n Current Country:" + human.getCountry())
                .setTitle("IDEAL")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
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
                })
               .setNeutralButton("Cancel", null)
               .setIcon(R.mipmap.ic_launcher)
               .setCancelable(false)
               .setView(scrollView)
               .create();

        //Use this command to control the positioning of your alertDialog
        //ad.getWindow().getAttributes().verticalMargin = 1.1F;
        ad.show();


    }
    public void availableJobs(){
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
        ad
                .setMessage("Available Jobs. \n Current Country:" + human.getCountry() + "\n" +

                                "Beggar:" + begger.toString() + "\n" +
                                "Vagrant:" + vagrant.toString() + "\n" +
                                "PackingBoy:" + packingboy.toString() + "\n" +
                                "Intern:" + intern.toString() + "\n" +
                                "Firefighter:" + firefighter.toString() + "\n" +
                                "Banker:" + banker.toString() + "\n" +
                                "Independent:" + independent.toString() + "\n" +
                                "Scientist:" + scientist.toString() + "\n" +
                                "Business Owner:" + businessowner.toString() + "\n" +
                                "King" + king.toString() + "\n" +
                                "God:" + god.toString() + "\n" +
                                "Sultan:" + sultan.toString() + "\n" +
                                "Omega:" + omega.toString()
                )
                .setTitle("IDEAL")
                .setNeutralButton("Cancel", null)
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .create();


        ad.show();
    }
    public void goToSchool(){
        //This number will keep track if a job is unlocked or not...if this number is still equal to 0 at the end of this function then no job was unlocked
            int unlockjobInt=0;
        //Call the XML List view and set it with  the countries array
        //LayoutInflater inflater = getLayoutInflater();
        //View convertView = inflater.inflate(R.layout.alertdialog_layout, null, false);
        //Increment and Update the amount of times this user went to school
        schoolAttendanceAmount++;
        schoolAttendanceAmountTextView.setText(Integer.toString(schoolAttendanceAmount));

        //This String Variable is used to hold all of the messages in one string
        String chainText="";
            //This Dialog is used to show which jobs were unlocked
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final AlertDialog.Builder adNotifier = new AlertDialog.Builder(ctw);
         //adNotifier.setView(convertView);
        //alertDialog_content_textView = (TextView) findViewById(R.id.alertDialog_content_textView);
        adNotifier
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

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


                    }
                })
                .setMessage("Go a school:Raises the amount of professional Associates" + "\n" +
                        "Unlocks jobs" + "\n" +
                        "Raises the amount of friends")
                .setTitle("IDEAL:Going to School")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .create();


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

        }if( schoolAttendanceAmount>=6&&socialisingWithFriends>=3 && workingOnPhysicalApp>=1&&king) {

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
            adNotifier.setMessage("No job was unlocked.You need more schooling");
        }else {
            adNotifier.setMessage(chainText);
        }

        //alertDialog_content_textView.setText(chainText);
        adNotifier.show();

    }
    public void workOnPhysical(){
        workingOnPhysicalApp++;
        workingOnPhysicalAppTextView.setText(Integer.toString(workingOnPhysicalApp));
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
    ad
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (genie) {
                            wishes++;
                           Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                                    Toast.LENGTH_LONG).show();
                            workOnPhysicalAppearanceButton.setEnabled(false);
                            influence+=1000;
                            friends+=1000;
                            worshippers+=100;
                            looks+=1;
                         genieDoSomething();

                        }else{
                            buttonActivation(false);
                            wealth+=-100 * human.getCountry().getMultiplier();
                            influence+=1000;
                            friends+=1000;
                            worshippers+=100;
                            looks+=1;
                            if(randomNum>25) {
                                healthUpdater(5);
                            }


                        }



                    }
                })

                .setMessage(
                        "Your looks got increased by 1" + "\n" +
                                "There is a chance your health will get boosted"+ "\n" +
                                "The amount of worshippers got increased by 100" + "\n" +
                                "Your influence got increased by 1000" + "\n" +
                                "The amount of friends increased by 1000" + "\n" +
                                "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .create();
        ad.show();
      }
    public void socializeWithPeople(){
        socialisingWithFriends++;
        socialisingWithFriendsTextView.setText(Integer.toString(socialisingWithFriends));
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
     ad
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
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
                    }
                })

                .setMessage("The amount of worshippers got increased by 100" + "\n" +
                        "Your influence got increased by 1000" + "\n" +
                        "The amount of friends increased by 1000" + "\n" +
                        "Your professional Associates got increased by 1000" + "\n" +
                        "You got charged:$" + 100 * human.getCountry().getMultiplier())

                .setTitle("IDEAL:Work on your Physical")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .create();
        ad.show();
    }
    public void selectACountry() {
              //Get the arrayLIst from the String xml
        final List<String> countries = Arrays.asList(getResources().getStringArray(R.array.countries));
        //Command used to set the theme of the AlertDialog
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.countrieslistlayout, null);
        alertDialog.setView(convertView);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setTitle("Choose a Country");

        final ListView countrieslistView = (ListView) convertView.findViewById(R.id.countriesListXML);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, countries);
        if(age>1) {
            //The user should be able to cancel country selection unless the country previously selected is one that the user don't have access to
            alertDialog.setNeutralButton("Cancel",null);

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

                    switch(checkedItem.toString()) {
                        case "Trinentina":
                            if (!firefighter && !banker && !scientist) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Trinentina;
                            }

                            break;
                        case"Albico":
                            if (!firefighter && !banker && !scientist) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Albico;
                            }

                            break;
                        case"Ugeria":
                            if (!firefighter && !banker && !scientist) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Ugeria;
                            }

                            break;
                        case"Portada":
                            if (!firefighter && !banker && !scientist) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Portada;
                            }
                            break;
                        case"Kuwador":
                            if (!independent && !banker && !scientist&&!businessowner) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Kuwador;
                            }
                            break;
                        case"Ukrark":
                             if (!independent && !banker && !scientist&&!businessowner) {
                                  Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                    Toast.LENGTH_LONG).show();
                               selectACountry();
                                 alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    selectACountry();
                                }
                            });
                            } else{
                              countryOfUser=Countries.Kuwador;
                             }
                        break;

                        case"Rany":
                            if (!independent && !banker && !scientist&&!businessowner) {
                                Toast.makeText(MainActivity.this, "You don't have any jobs available in this country: " + checkedItem.toString(),
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });
                            } else{
                                countryOfUser=Countries.Rany;
                            }
                            break;
                        case"Heaven" :
                            if (heavenBoolean || genie) {
                                countryOfUser = Countries.Heaven;
                            } else {
                                Toast.makeText(MainActivity.this, "You don't have access to heaven because Genie: " + genie + "or Heaven: " + heavenBoolean,
                                        Toast.LENGTH_LONG).show();
                                selectACountry();
                                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        selectACountry();
                                    }
                                });

                            }
                        break;
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
                       if(genie) {
                           wishes++;
                           Toast.makeText(MainActivity.this, "Wishes Left:" + (3 - wishes),
                                   Toast.LENGTH_LONG).show();
                           changeCountryButton.setEnabled(false);
                        genieDoSomething();

                       }else{

                           keepStatsUpToDate(-(human.getCountry().getTaxes() + 100 * human.getCountry().getMultiplier()), 0, job, human.getCountry().getTaxes(), 0, 0, 0, 0);
                           buttonActivation(false);

                       }

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

        //Disable certain Items on the Countries List View
        //countrieslistView.getChildAt(1).setEnabled(false);
        //countrieslistView.getChildAt(9).setBackgroundColor(Color.parseColor("#d3d3d3"));


    }

    /*public void selectACountry() {
        //ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final Dialog alertDialog = new Dialog(MainActivity.this);
        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.countries_job_layout, null);
        alertDialog.setContentView(convertView);

        TextView countryZeroName =(TextView) alertDialog.findViewById(R.id.countryZero_name);
        countryZeroName.setText(Countries.Irada.getName());

        TextView countryZeroTax =(TextView) alertDialog.findViewById(R.id.countryZero_tax);
        countryZeroTax.setText(Double.toString(Countries.Irada.getTaxes()));

        //alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setTitle("Choose a Country");
       // alertDialog.setPositiveButton("Confirm", null);
        //alertDialog.setNeutralButton("Confirm", null);
        //alertDialog.setNegativeButton("Confirm", null);
        alertDialog.show();



    }*/
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
                           Thread.sleep(225);
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
        overallWealthTextView.setText(currencyFormat.format(human.getOverAllWealth()));
        String influenceAmountString = Double.toString(human.getInfluence());
        influenceTextView.setText(influenceAmountString);
        jobTextView.setText(getString(R.string.getJob_text) + ":" + "\n" + human.getJob());
        countryTextView.setText(getString(R.string.getCountry_text) + ":" + "\n" + human.getCountryString());
        taxTextView.setText(getString(R.string.tax_text) + ":" + "\n" + currencyFormat.format(tax));


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
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
       ad
                .setMessage("Welcome!The purpose of this Android Application is to create your fantasy ideal life.." +
                        ("\n") + "Create a character at the bottom of society." +
                        ("\n") + "Progress this character through the world and accumulate influence, wealth, and associates." +
                        ("\n") + "Don't hold back,accumulating everything the world has the offer is the key of winning the game" +
                        ("\n") + "Good Luck!")
                        //.setIcon(R.drawable.ic_launcher)
                .setTitle("IDEAL")
                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        /*if (dialogShown) {
                            IDEALLifeProgram();
                        }*/
                        /*try {
                            tutorialWithFamily();

                        } catch (NullPointerException e) {
                            Toast.makeText(MainActivity.this, "Ooops,something went wrong.Let's try again!",
                                    Toast.LENGTH_LONG).show();
                            tutorialWithFamily();
                        }*/
                        //dialogShown = false;

                    }
                })
                .setCancelable(false)
               .setIcon(R.mipmap.ic_launcher)
                .create();

        ad.show();

    }
    public void makeYourName() {
        //Call the XML List view and set it with  the countries array
        //LayoutInflater inflater = getLayoutInflater();
        //View convertView = inflater.inflate(R.layout.alertdialog_layout, null, false);
        //Creating a Layout for the EditTextViews
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Set up First Name Text View
        final EditText userNameFirst = new EditText(this);
        userNameFirst.setHintTextColor(Color.WHITE);
        userNameFirst.setTextColor(Color.WHITE);
        userNameFirst.setHint("Enter In First Name");
        layout.addView(userNameFirst);

        //Setup the Last Name Text View
        final EditText userNameFamily = new EditText(this);
        userNameFamily.setHintTextColor(Color.WHITE);
        userNameFamily.setTextColor(Color.WHITE);
        userNameFamily.setHint("Enter In Last Name");
        layout.addView(userNameFamily);
        //layout.addView(convertView);
        //Command used to set the theme of the AlertDialog
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        AlertDialog.Builder ad = new AlertDialog.Builder(ctw);
        //AlertDialog ad = new AlertDialog.Builder(this)
        ad
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
                .setIcon(R.mipmap.ic_launcher)
                .setView(layout)
                .create();
        //ad.setView();
        ad.show();
    }
    //SELECT COUNTRY IS BOTH INITIALIZATION AND A BUTTON FUNCTIONALITY
    public void selectAFamilyType() {

        //Get the arrayLIst from the String xml
        final List<String> familyTypes = Arrays.asList(getResources().getStringArray(R.array.familyTypes));
        ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.AlertDialogCutomTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
        //final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Choose A Family");

        //Call the XML List view and set it with  the countries array
        LayoutInflater inflater = getLayoutInflater();
        final View convertView = inflater.inflate(R.layout.familycreationlayout, null, false);
        alertDialog.setView(convertView);
        final ListView familyListView = (ListView) convertView.findViewById(R.id.familyChoiceList);
        final EditText friendsAmountEditText = (EditText) convertView.findViewById(R.id.friendsAmountEditText);
        final EditText influenceAmountEditText = (EditText) convertView.findViewById(R.id.influenceAmountEditText);
        final EditText wealthAmountEditText = (EditText) convertView.findViewById(R.id.wealthAmountEditText);
        //I don't want the TextView to show up when..View.GONE takes care of that
        friendsAmountEditText.setVisibility(View.GONE);
        influenceAmountEditText.setVisibility((View.GONE));
        wealthAmountEditText.setVisibility(View.GONE);
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
                    //Show TextView
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
                    //Remove TextViews
                    friendsAmountEditText.setVisibility(View.GONE);
                    influenceAmountEditText.setVisibility(View.GONE);
                    wealthAmountEditText.setVisibility(View.GONE);

                }
            }
        });
        alertDialog
                .setIcon(R.mipmap.ic_launcher)
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

                                        //keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                                //tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                        updatingStateOfFamily(family.getFamilyWealth(),family.getFamilyInfluence());
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
                                    try {
                                    checkedItem = familyListView.getAdapter().getItem(familyListView.getCheckedItemPosition());
                                    //Getting the String from the selected item from the Family List View
                                    String selectedFamilyType = checkedItem.toString();
                                    //This  try/catch block code catches the (Null Pointer) exception if nothing is selected


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
                                    //keepStatsUpToDate(family.getFamilyWealth(), family.getFamilyInfluence(), human.getJob(),
                                            //tax, human.getLooks(), human.getWorshippers(), family.getFamilyFriends(), human.getProfessionalAssociates());
                                        updatingStateOfFamily(family.getFamilyWealth(),family.getFamilyInfluence());


                                    Toast.makeText(MainActivity.this, "You have selected " + checkedItem.toString() + family,
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
        userfriendsTextViewprofileDialog.setText("Friends:" + family.getFamilyFriends());


        professionalAssociatesTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.professionalAssociatesTextViewprofileDialog);
        professionalAssociatesTextViewprofileDialog.setText("N/A");
        professionalAssociatesTextViewprofileDialog.setTextColor(888);

        worshippersTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.worshippersTextViewprofileDialog);
        worshippersTextViewprofileDialog.setText("Worshippers:" + family.getFamilyWorshippers());

        looksTextViewprofileDialog = (TextView) alertDialogProfile.findViewById(R.id.looksTextViewprofileDialog);
        looksTextViewprofileDialog.setText("N/A");
        looksTextViewprofileDialog.setTextColor(888);

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

    public void worshippersFollow(boolean worshippersFollow) {

        if (worshippersFollow && tempNum != age) {

           worshippers+= 100;
            chainString+="You attracted 100 worshippers";
            tempNum++;

        }
        if (worshippersFollow && god) {


            worshippers += 1000;
            chainString+="You attracted 1000 worshippers";


        }
    }

///->Two Different Stages of IDEAL
    public void tutorialWithFamily(){

            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }else{
        chainString+="\n Nothing Happened this time";
    }



    }
    public void grownUpHuman(){


            randomNum = rand.nextInt(30);
            //System.out.println(randomNum);
            if (randomNum <= 13) {
                chancesOfLife();
            }else{
                chainString+="\n Nothing Happened this time";
            }


            System.out.println("Turn: " + age + "\n" + "|Adult" + "|" + human.getOverAllWealth() + "|" + "|" + human.getCountry().getName() + "|" + "|" + human.getCountry().getTaxes());

            //Once the player reaches 1000000 worshippers, the player will become a god
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
            if(human.getWorshippers()>10000000&&workingOnPhysicalApp>=10&&socialisingWithFriends>=10&&schoolAttendanceAmount>=10) {
                worshippersActivation=true;
                worshippersFollow(worshippersActivation);
                //job = Jobs.GOD;
                heavenBoolean=true;
                //schoolButton.setText(Html.fromHtml("<font color='red'>First line</font><br/><font color='blue'>Second line</font>"));
                chainString+="\n"+Html.fromHtml("<font color='blue'>You have gained access to heaven and will gain worshippers for the next 10 years</font><br/>");
            }


            //Once the player reaches 100000 in influence, the player will get paid $100000 every turn
            //it only needs to be activated once and the benefit should automatically apply without the player being notified again
            //Permanent benefit once it is activated
            if(human.getInfluence()>7000000&& human.getProfessionalAssociates()>=200000 && !influencActivation
                    ||human.getFriends()>=1000000){
                influencActivation=true;
                chainString+="\n" +Html.fromHtml("<font color='blue'>You have gained 50000 in wealth</font><br/>");
                chainString+="\n" + Html.fromHtml("<font color='blue'>You have gained enough Influence and Professional Associates that you will now gain a steady source of money</font><br/>");
                wealth += 50000;

            }





    }
///->

///->Calling a Thread for the application
    //Genie Thread
    public void genieDoSomething(){

      
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
    public void IDEALLifeProgram(){
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
                   age++;
                   //Tutorial for the first 5 years;
                   if(age==1) {
                       System.out.println("A0");
                       init();
                       System.out.println("A1");
                       makeYourName();
                       System.out.println("A2");
                       selectAFamilyType();
                       System.out.println("A3");
                       selectACountry();


                       continueButton.startAnimation(animationFade);
                       informationalTextView.setText("Press the 'Continue Button' to begin the game");
                   } else if (age == 2) {
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

                   } else if (age < 20 && age > 5) {

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
                          buttonActivation(true);
                          keepStatsUpToDate(family.getFamilyWealth(),family.getFamilyInfluence(), job,family.getFamilyCountry().getTaxes()
                          ,0,family.getFamilyWorshippers(),family.getFamilyFriends(), 0);
                          informationalTextView.setText("IDEAL:Adult Life" + "\n" + "You are now an adult an inherited everything from your family. Now you will have to work on your own to secure your IDEAL Life" + "\n"
                                  + "If you can reach $10,000,000 in wealth or 2,000,000 in influence;you would have won the game!!"
                                  + "\n There are also many other features to unlock in the game"
                                  + "\n Only one Action can be done per turn so choose wisely"
                                  + "\n Create your IDEAL Life");

                           shownOne = true;
                       }else{
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
                          if(socialisingWithFriends>=10) {
                              socializeWithPeopleButton.setEnabled(false);
                              socialisingWithFriendsTextView.setBackgroundColor(Color.parseColor("#FF0000"));
                              socialisingWithFriendsTextView.setText("MAX");
                              //HTML may be a limitation in android code
                              //socializeWithPeopleButton.setText(R.string.socialize_button + "\n" + Html.fromHtml("<font color='red'>MAX</font>"));

                              if (workingOnPhysicalApp >= 10) {
                                  workOnPhysicalAppearanceButton.setEnabled(false);
                                  //Set TextView color to Red and TextView to MAX
                                  workingOnPhysicalAppTextView.setBackgroundColor(Color.parseColor("#FF0000"));
                                  workingOnPhysicalAppTextView.setText("MAX");
                                  //workOnPhysicalAppearanceButton.setText(R.string.physical_Button + "\n" + Html.fromHtml("<font color='red'>MAX</font>"));

                                  if (schoolAttendanceAmount >= 10) {
                                      schoolButton.setEnabled(false);
                                      schoolAttendanceAmountTextView.setBackgroundColor(Color.parseColor("#FF0000"));
                                      schoolAttendanceAmountTextView.setText("MAX");
                                      //Changing text color within a button with Multiple Lines
                                      //schoolButton.setText(Html.fromHtml("<font color='red'>First line</font><br/><font color='blue'>Second line</font>"));
                                      //schoolButton.setText(R.string.school_Button + "\n" + Html.fromHtml("<font color='red'>MAX</font>"));

                                  }
                              }
                          }
                          //Let the User make a life Decision//Disable after footer button press
                            buttonActivation(true);
                            grownUpHuman();
                          keepStatsUpToDate(wealth*1.0,influence,job,human.getCountry().getTaxes(),looks,worshippers,friends,professionalAssociates);
                          //Reset stats so that there are no further stacking up
                          //Chances of Life-->Recommendation
                          informationalTextView.setText(
                                  chainString+"\n"+
                                    "What you have earned:\n"+
                                     "Wealth: " +wealth +"\n"+
                                     "Influence: " +influence +"\n"+
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

        age_Turn_textView.setText("Age: " + age);


        

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

    public void onStop(){
        super.onStop();
        //Save File
        saveUserProfile();

    }

    public void buttonActivation(boolean activate){
        changeCountryButton.setEnabled(activate);

        selectAJobButton.setEnabled(activate);

        schoolButton.setEnabled(activate);

        workOnPhysicalAppearanceButton.setEnabled(activate);

        socializeWithPeopleButton.setEnabled(activate);
    }

    public String recommendation(){
        String recommendation="";

//User should be working on gatting a job by the age of 20
        if(human.getJob()==Jobs.NOJOB && age>20){
            recommendation+="\n You need to get a job to earn income on very turn.Note:Only Certain jobs are unlocked for each Country |";
        }

//If the User does not have a certain amount of wealth then suggest something useful to the User
        if(human.getOverAllWealth()<2000000&&age>40){
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
    public void saveUserProfile(){

        try {
            savedata = "testFile \n" +
                    "Age:" + age+ " \n" +
                    //Family
                    "FamilyCountry:" + family.getFamilyCountry().toString() + " \n" +
                    "FamilyFriends:" + family.getFamilyFriends() + " \n" +
                    "FamilyProfessionalAssociate:" + human.getProfessionalAssociates() + " \n" +
                    "FamilyWorshippers:" + family.getFamilyWorshippers() + " \n" +
                    "FamilyWealth: " + family.getFamilyWealth() + " \n" +
                    "FamilyInfluence:" + family.getFamilyInfluence() + " \n" +
                    //Human
                    "schoolAttendance:" + schoolAttendanceAmount + " \n" +
                    "workOut:" + workingOnPhysicalApp + " \n" +
                    "socialize:" + socialisingWithFriends + " \n" +
                    "FirstName:" + firstNamePart + " \n" +
                    "LastName:" + lastNamePart + " \n" +
                    "Job:" + human.getJob().toString() + " \n" +
                    "Country:" + human.getCountryString() + " \n" +
                    "Friends:" + human.getFriends() + " \n" +
                    "ProfessionalAssociate:" + human.getProfessionalAssociates() + " \n" +
                    "Worshippers:" + human.getWorshippers() + " \n" +
                    "Looks:" + human.getLooks() + " \n" +
                    "Wealth: " + human.getOverAllWealth() + " \n" +
                    "Influence:" + human.getInfluence(); /*Save everything as a string for now*/
        }catch (Throwable n){//There will be many exceptions thrown if the user closes this app before and during the splash screen AND before Country dialog is filled
            System.out.println("The file was unable to be saved: " + n);
        }


        FileOutputStream outputStream;
        //File file = new File(context.getFilesDir,filename);
        try {
            if(savedata.equals("NothingInFile")) {
                System.out.println("Profile has NOT been Saved");
            }else{
                String filename = "idealApplicationProfile";
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(savedata.getBytes());
                outputStream.close();
                System.out.println("Profile has been Saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private boolean readFromFile() {
        boolean bolen = false;
        try {
            String fileInfo;
            FileInputStream fileInputStream =openFileInput("idealApplicationProfile");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            try {
                while((fileInfo=bufferedReader.readLine())!=null){
                   stringBuffer.append(fileInfo);//.append("\n");
                    //System.out.println(fileInfo);
                    if (fileInfo.equals("FamilyCountry:")){
                            family.setCountry(getThisCountry(bufferedReader.readLine()));
                            System.out.println("Country was successfully pulled from file" + family.getFamilyCountry().getName());
                        bolen=true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bolen;
    }
//->


    }

