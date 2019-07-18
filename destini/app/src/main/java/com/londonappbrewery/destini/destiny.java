package com.londonappbrewery.destini;

public class destiny {
     private int dstory;
     private int respone1,respone2;
     destiny ques1,ques2;
     public destiny(int Ques,int ans1,int ans2)
     {
         dstory=Ques;
         respone1=ans1;
         respone2=ans2;
     }

    public int getDstory() {
        return dstory;
    }

    public int getRespone1() {
        return respone1;
    }

    public int getRespone2() {
        return respone2;
    }
}
