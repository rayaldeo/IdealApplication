package com.example.jelliott.idealapplication;

/**
 * Created by jelliott on 4/9/2016.
 */

/**
 * Created by jelliott on 9/8/2015.From Ideal Java Program
 */

public class Human {

    private int looks,influence;
    private String color,neighborhood="nothing",name,countryName;
    //private  NameGenerator generatedName = new NameGenerator();
    private Integer friends,professionAssociates,worshippers;
    //private boolean sex;
    private Jobs job;
    private Countries country;
    private double initialWealth;
    private double overAllWealth;
    private double income;
    private double familyWealth;


    /*
    Create an average Human in the poorest neighborhood,average looks, and zero wealth or friends
    User needs to specify skin color
     */
    //Empty Constructor
    public Human(){
        looks=0;
        worshippers=0;
        familyWealth= 0;
        influence=0;
        income = 0;
        overAllWealth=0;
        influence=0;
        friends=0;
        professionAssociates=0;
        neighborhood ="poor";
        job=Jobs.NOJOB;
        country=Countries.NoCountry;

    }

    //This contructor can be used after the game is beaten specifically for the player
    public Human(/*String colorA,boolean sexA*/String nameA,Jobs jobA, Family familyA,Countries countriesA){
        //sex=sexA;
        //nameGenerator =  new NameGenerator(sex);
        name = nameA;
        //color=colorA;
        looks=5;
        neighborhood= countriesA.getPoorNeighborHood();
        worshippers=0;
        familyWealth= familyA.getFamilyWealth();
        influence=familyA.getFamilyInfluence();
        income = jobA.getIncome();
        overAllWealth=jobA.getIncome()+ initialWealth;
        influence=jobA.getInfluence();
        friends=0;
        professionAssociates=0;
        job=jobA;
        country=countriesA;
        countryName =countriesA.getName();


    }

    //This constructor is used specifically for  the FamilyMember class
    //Deleting this constructor ruins a core mechanic of this program
    public Human(boolean sex,String familyNameA,Jobs jobA,Countries countryA,int friendsA,int worshippersA){
        //generatedName =  new NameGenerator(sex);
        //name = generatedName.getFirstName()+""+familyNameA;
        //color=colorA;
        looks=5;
        neighborhood= countryA.getPoorNeighborHood();
        worshippers=worshippersA;
        //familyWealth= familyA.getFamilyWealth();
        //influence=familyA.getfamilyInfluence();
        income = jobA.getIncome();
        overAllWealth=jobA.getIncome()+initialWealth;
        influence=jobA.getInfluence();
        friends=friendsA;
        professionAssociates=0;
        job=jobA;
        country=countryA;
        countryName =countryA.getName();

    }

    //Getters
    public int getLooks(){return looks;}

    public String getNeighborhood(){return neighborhood;}

    public String getName(){return name;}

    public String getColor(){return  color;}

    public double getFamilyWealth(){return familyWealth;}

    public int getInfluence(){return influence;}

    public double getOverAllWealth(){return overAllWealth;}

    public double getInitialWealth(){return initialWealth;}

    public int getFriends(){return friends;}

    public int getProfessionalAssociates(){return professionAssociates;}

    public int getWorshippers(){return worshippers;}

    public double getIncome(){return income;}

    public Jobs getJob() {return job;}

    public  Countries getCountry(){return country;}

    public String getCountryString(){return country.getName();}




    //Setters
    public void setLooks(int looksA){ looks=looksA;}

    public void setNeighborhood(String neighborhoodA){ neighborhood=neighborhoodA;}

    //public void setName(String nameA){ name= nameA;}

    public void setJob(Jobs jobA){job = jobA;}

    public void setCountries(Countries countryA){country =countryA;}

    public void setFamilyWealth(double familyWealthA){ familyWealth=familyWealthA;}

    public void setInfluence(int influenceA){ influence=influenceA;}

    //This is the amount of money that character owns at anytime in the game
    public void setOverAllwealth(double wealthA){ overAllWealth =wealthA;}

    public void setFriends(int friendsA){friends=friendsA;}

    public void setProfessionalAssociates(int professionAssociatesA){ professionAssociates=professionAssociatesA;}

    public void setColor(String colorA){ color=colorA;}

    public void setWorshippers(int worshippersA){worshippers= worshippersA;}

    public void setIncome(double incomeA){income=incomeA;}






}

