package com.londonappbrewery.quizzler;

public class TrueFalse {
    private int mQuestionID;
    private boolean ans;
    public TrueFalse(int questres,boolean TrueorFalse){
        mQuestionID=questres;
        ans=TrueorFalse;
    }

    public int getmQuestionID() {
        return mQuestionID;
    }

    public void setmQuestionID(int mQuestionID) {
        this.mQuestionID = mQuestionID;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }
}
