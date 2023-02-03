package com.driver;

import java.util.*;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;
    List<Integer> res = new ArrayList<>();

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public void backtracking(int candidates[],int target,int rejected,ArrayList<Integer> list,int digits){
        if(target<0)
            return;
        if(target==0){
            if(list.size()==digits){
                res.clear();
                res = new ArrayList<>(list);
            }
            else if(list.size()<digits && res.size()<list.size())
                res = new ArrayList<>(list);
            return;
        }
        for(int i=rejected;i<candidates.length;i++){
            list.add(candidates[i]);
            backtracking(candidates,target-candidates[i],i,list,digits);
            list.remove(list.size()-1); // backtrack
        }
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String accountNo = "";
        int arr[] = {1,2,3,4,5,6,7,8,9};
        backtracking(arr,sum,0,new ArrayList<>(),digits);
        for(int i=0;i<res.size();i++){
            accountNo += res.get(i);
        }

        if(res.size()<digits){
            int rem = digits - res.size();
            while(rem -- >0){
                accountNo += "0";
            }
        }

        if(accountNo=="")
            throw new Exception("Account Number can not be generated");
        else
            return accountNo;
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