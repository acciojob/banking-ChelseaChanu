package com.driver;

public class StudentAccount extends BankAccount{

    private String  institutionName;

    public StudentAccount(String name, double balance, String  institutionName) {
        //minimum balance is 0 by default
        super(institutionName, balance, 0);
        this.institutionName = institutionName;
    }

    public String getInstitutionName() {
        return institutionName;
    }
    

}
