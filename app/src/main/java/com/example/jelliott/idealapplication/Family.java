package com.example.jelliott.idealapplication;
import java.util.ArrayList;

/**
 * Created by jelliott on 4/9/2016.
 */
public class Family {


    /**
     * Created by jelliott on 10/4/2015.From Ideal Java Program
     */



        private ArrayList<FamilyMember> family = new ArrayList<FamilyMember>();
        private int familyFriends,familyWorshippers;
        private double familyWealth,familyInfluence;
        private  String familyName,familyFatherName,familyMotherName,familyBrotherName,familySisterName,neighborhood;
        private FamilyMember father,brother, sister,mother;
        private Countries country;


    public Family(){
        this.familyWealth=0.0;
        this.familyInfluence=0;
        this.familyWorshippers=0;
        this.familyFriends=0;
    }

    public Family( double familyWealthA,double influenceA,int friendsA){
        this.familyWealth=familyWealthA;
        this.familyInfluence=influenceA;
        this.familyFriends=friendsA;
    }

        //FamilyMemeber Constructor
        //(String firstNameA,String familyNameA,Jobs jobA,Countries countryA,int friendsA,int worshippers
        //Custom Family with a custom name
        public Family(String familyNameA,FamilyMember brotherA, FamilyMember sisterA, FamilyMember fatherA, FamilyMember motherA) {
            this.familyName = familyNameA;
            this.familyFatherName ="Mr."+familyName;
            this.familyBrotherName = "Brother "+ familyName;
            this.familyMotherName = "Mrs."+ familyName;
            this.familySisterName = "Sister "+ familyName;
            this.father =fatherA;
            this.mother = motherA;
            this.sister = sisterA;
            this.brother = brotherA;
            family.add(fatherA);
            family.add(motherA);
            family.add(sisterA);
            family.add(brotherA);
            //brotherA.getWealthDoubleValue returns a double date set of the brother's wealth
            this.familyWealth=(brotherA.getOverAllWealth() + sisterA.getOverAllWealth() + motherA.getOverAllWealth() + fatherA.getOverAllWealth());
            this.familyInfluence=(brotherA.getInfluence() + sisterA.getInfluence() + motherA.getInfluence() + fatherA.getInfluence());
            this.familyWorshippers =(brotherA.getWorshippers()+motherA.getWorshippers()+fatherA.getWorshippers()+sisterA.getWorshippers());
            this.familyFriends =(brotherA.getFriends()+motherA.getFriends()+fatherA.getFriends()+sisterA.getFriends());
            country=fatherA.getCountry();
        }

        public Family(int fatherA, int motherA, int sisterA, int brotherA) {}

        ///Getters
        //Getters
        public double getFamilyWealth() {
            return familyWealth;
        }

        public double getFamilyInfluence() {
            return familyInfluence;
        }

        public String getFamilyName(){ return familyName;   }

        public String getFamilyCountry(){return country.getName();}

        public int getFamilyFriends(){return familyFriends;}

        public int getFamilyWorshippers(){return familyWorshippers;}

        public String getFamilyNeighborhood(){return neighborhood;}

        public void setFamilyWealth(double familyWealthA) {
            this.familyWealth = familyWealthA;
        }

        public void setFamilyInfluence(int familyInfluenceA) {
            this.familyInfluence = familyInfluenceA;
        }

        public void setFamilyName(String familyNameA){
            familyName = familyNameA;
        }

        public void setNeighborhood(String neighborhoodA){neighborhood=neighborhoodA;}

        public void setCountry(Countries countryA){country=countryA;}


}
