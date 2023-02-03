package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if(getMinBalance()<5000)
            throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int freqArr[] = new int [26];
        PriorityQueue<Key> pq = new PriorityQueue<>(new KeyComparator());
        for(int i=0;i<tradeLicenseId.length();i++){
            char ch = tradeLicenseId.charAt(i);
            freqArr[ch - 'A']++;
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            int val = c - 'A';
            if (freqArr[val] > 0)
                pq.add(new Key(freqArr[val], c));
        }

        String res = "";
        Key prev = new Key(-1, '#');
        while (pq.size() != 0) {
            Key k = pq.peek();
            pq.poll();
            res = res + k.ch;
            if (prev.freq > 0)
                pq.add(prev);
            (k.freq)--;
            prev = k;
        }

        if(tradeLicenseId.length()!=res.length()){
            throw new Exception("Valid License can not be generated");
        }
        else{
            tradeLicenseId = res;
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
