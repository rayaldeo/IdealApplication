package com.example.jelliott.idealapplication;
import java.util.ArrayList;

/**
 * Created by jelliott on 4/9/2016.
 */
class Family {
        private ArrayList<FamilyMember> family = new ArrayList<>();
        private int familyFriends,familyWorshippers,familyInfluence,familyProfessionalAssociates;
        private double familyWealth;
        private  String familyName,familyFatherName,familyMotherName,familyBrotherName,familySisterName,neighborhood;
        private FamilyMember father,brother, sister,mother;
        private Countries country;


    Family(){
        this.familyWealth=0.0;
        this.familyInfluence=0;
        this.familyWorshippers=0;
        this.familyFriends=0;
        this.familyProfessionalAssociates=0;
        this.country=Countries.NoCountry;
    }

    Family(double familyWealthA, int influenceA, int friendsA, String familyNameA, FamilyMember brotherA, FamilyMember sisterA, FamilyMember fatherA, FamilyMember motherA){
        this.familyName = familyNameA;
        this.familyFatherName ="Mr."+familyName;
        this.familyBrotherName = "Brother "+ familyName;
        this.familyMotherName = "Mrs."+ familyName;
        this.familySisterName = "Sister "+ familyName;
        this.familyWealth=familyWealthA;
        this.familyInfluence=influenceA;
        this.familyFriends=friendsA;
        this.familyWorshippers=0;
        this.father =fatherA;
        this.mother = motherA;
        this.sister = sisterA;
        this.brother = brotherA;
        family.add(fatherA);
        family.add(motherA);
        family.add(sisterA);
        family.add(brotherA);
        country=fatherA.getCountry();
    }

        //FamilyMemeber Constructor
        //(String firstNameA,String familyNameA,Jobs jobA,Countries countryA,int friendsA,int worshippers
        //Custom Family with a custom name
        Family(String familyNameA, FamilyMember brotherA, FamilyMember sisterA, FamilyMember fatherA, FamilyMember motherA) {
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
            this.familyProfessionalAssociates =(brotherA.getProfessionalAssociates()+motherA.getProfessionalAssociates()+fatherA.getProfessionalAssociates()+sisterA.getProfessionalAssociates());
            country=fatherA.getCountry();
        }

        public Family(int fatherA, int motherA, int sisterA, int brotherA) {}

        ///Getters
        double getFamilyWealth() {
            return familyWealth;
        }

         int getFamilyInfluence() {
            return familyInfluence;
        }

        public String getFamilyName(){ return familyName;   }

         Countries getFamilyCountry(){return country;}

         int getFamilyFriends(){return familyFriends;}

         int getFamilyWorshippers(){return familyWorshippers;}

         String getFamilyNeighborhood(){return neighborhood;}

    int getFamilyProfessionalAssociates() {
        return familyProfessionalAssociates;
    }


    //Setters
         void setFamilyWealth(double familyWealthA) {
            this.familyWealth = familyWealthA;
        }

         void setFamilyInfluence(int familyInfluenceA) {
            this.familyInfluence = familyInfluenceA;
        }

        public void setFamilyName(String familyNameA){
            familyName = familyNameA;
        }

         void setNeighborhood(String neighborhoodA){neighborhood=neighborhoodA;}

         void setCountry(Countries countryA){country=countryA;}

         void setFamilyProfessionalAssociates(int professionalAssociatesA){this.familyProfessionalAssociates=professionalAssociatesA;}

         void setFamilyFriends(int familyFriendsA){this.familyFriends=familyFriendsA;}

         void setFamilyWorshippers(int familyWorshippersA){this.familyWorshippers= familyWorshippersA;}


}
