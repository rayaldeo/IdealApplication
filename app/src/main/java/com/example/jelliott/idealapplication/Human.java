package com.example.jelliott.idealapplication;

/**
 * Created by jelliott on 4/9/2016.
 *
 */

class Human {

    private Integer looks,influence;
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
    Human(){
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
    Human(boolean sex, String familyNameA, Jobs jobA, Countries countryA, int friendsA, int worshippersA){
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
    int getLooks(){return looks;}

    String getNeighborhood(){return neighborhood;}

    public String getName(){return name;}

    public String getColor(){return  color;}

    public double getFamilyWealth(){return familyWealth;}

    int getInfluence(){return influence;}

    double getOverAllWealth(){return overAllWealth;}

    public double getInitialWealth(){return initialWealth;}

    int getFriends(){return friends;}

    int getProfessionalAssociates(){return professionAssociates;}

    int getWorshippers(){return worshippers;}

    double getIncome(){return income;}

    Jobs getJob() {return job;}

    Countries getCountry(){return country;}

    String getCountryString(){return country.getName();}

    //Setters
    void setLooks(int looksA) {
        looks = looksA;
    }

    void setNeighborhood(String neighborhoodA) {
        neighborhood = neighborhoodA;
    }

    //public void setName(String nameA){ name= nameA;}

    void setJob(Jobs jobA) {
        job = jobA;
    }

    void setCountries(Countries countryA) {
        country = countryA;
    }

    public void setFamilyWealth(double familyWealthA){ familyWealth=familyWealthA;}

    void setInfluence(int influenceA) {
        influence = influenceA;
    }

    //This is the amount of money that character owns at anytime in the game
    void setOverAllwealth(double wealthA) {
        overAllWealth = wealthA;
    }

    void setFriends(int friendsA) {
        friends = friendsA;
    }

    void setProfessionalAssociates(int professionAssociatesA) {
        professionAssociates = professionAssociatesA;
    }

    public void setColor(String colorA){ color=colorA;}

    void setWorshippers(int worshippersA) {
        worshippers = worshippersA;
    }

    void setIncome(double incomeA) {
        income = incomeA;
    }
}

