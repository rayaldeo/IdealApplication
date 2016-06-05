package com.example.jelliott.idealapplication;

/**
 * Created by jelliott on 4/9/2016.
 */

/**
 * Created by jelliott on 9/16/2015.
 */

public enum Jobs {

    /*Very Low Paying Jobs */
    NOJOB("null",0,0),/*This is the default state for a job*/
    BEGGER("Begger",5,5),
    VAGRANT("Vagrant",10,10),

    /*Low Paying Jobs that add  greater Influence*/
    INTERN("Intern",100,40),


    /*Average Paying Jobs with low Influence*/
    PACKINGBOY("Packingboy",150,40),
    FIREFIGHTER("Firefighter",350,10),
    BANKTER("Banker",100,50),

    /*Medium Paying Jobs with High Influence*/
    SCIENTIST("Scienctist",300,400),
    INDEPENDENT("Independent",600,900),

    /*Highest Paying Jobs*/
    BUSINESSOWNER("BusinessOwner",1000,1000),
    KING("King",5000,5000),
    SULTAN("Sultan",9000,9000),


    /*Non Paying Jobs that give incredible amount of Influence*/
    GOD("GOD",0,100000),

    /*Incredible amounts of money and Influence*/
    OMEGA("OMEGA",100000,100000);


    private  double income;
    private  int influence;
    private String name;

    Jobs(String nameA ,double incomeA,int influenceA) {
        this.income =incomeA;
        this.influence = influenceA;
        this.name= nameA;
    }

    //Getters
    public double getIncome() {
        return income;
    }

    public int getInfluence() {
        return influence;
    }

    public String getName(){return name;}


}




