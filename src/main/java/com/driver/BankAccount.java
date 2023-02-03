package com.driver;


public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    String accountNumber = "";
    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public void generate(int arr[],int digits, int sum, int currInd, String str){
        if(sum<0)
            return ;
        if(sum==0){
            if(str.length()==digits){
                accountNumber = str;
                return;
            }
            return;
        }

        for(int i=currInd;i<arr.length;i++){
            str += arr[i];
            generate(arr, digits, sum-arr[i], i, str);
            str = str.substring(0, str.length()-1);
        }
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String str = "";
        int arr[] = {0,1,2,3,4,5,6,7,8,9};
        generate(arr,digits,sum,0,str);
        if(accountNumber=="")
            throw new Exception("Account Number can not be generated");
        else
            return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(amount>this.balance){
            throw new Exception("Insufficient Balance");
        }
        else{
            this.balance -= amount;
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }
}