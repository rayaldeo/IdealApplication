package com.example.jelliott.idealapplication;

/**
 * Created by jelliott on 4/9/2016.
 */

/**
 * Created by jelliott on 9/16/2015.
 */

enum Jobs {

    /*Very Low Paying Jobs */
    NOJOB("null",0,0),/*This is the default state for a job*/
    BEGGER("Begger",5,5),
    VAGRANT("Vagrant",10,10),

    /*Low Paying Jobs that add  greater Influence*/
    INTERN("Intern",20000,400),


    /*Average Paying Jobs with low Influence*/
    PACKINGBOY("Packingboy",15000,400),
    FIREFIGHTER("Firefighter",35000,100),
    BANKER("Banker",60000,50),

    /*Medium Paying Jobs with High Influence*/
    SCIENTIST("Scienctist",70000,4000),
    INDEPENDENT("Independent",80000,9000),

    /*Highest Paying Jobs*/
    BUSINESSOWNER("BusinessOwner",100000,10000),
    KING("King",200000,50000),
    SULTAN("Sultan",90000,90000),


    /*Non Paying Jobs that give incredible amount of Influence*/
    GOD("GOD",0,10000000),

    /*Incredible amounts of money and Influence*/
    OMEGA("OMEGA",1000000,10000000);


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




