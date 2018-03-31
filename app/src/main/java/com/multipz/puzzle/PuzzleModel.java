package com.multipz.puzzle;

/**
 * Created by Admin on 30-03-2018.
 */

public class PuzzleModel {

    int no;
    boolean isEnable;

    public PuzzleModel(int no, boolean isEnable) {
        this.no = no;
        this.isEnable = isEnable;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
