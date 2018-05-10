package me.aflak.bluetoothterminal;

/**
 * Created by sears on 2/4/2018.
 */

public class Helper {
    public boolean isGetOpLogVal() {
        return getOpLogVal;
    }

    public void setGetOpLogVal(boolean getOpLogVal) {
        this.getOpLogVal = getOpLogVal;
    }

    public boolean getOpLogVal = true;

    public Helper(boolean getOpLogVal) {
        this.getOpLogVal = getOpLogVal;
    }

    private String PIN = "123456";

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public Helper(String PIN) {
        this.PIN = PIN;
    }
}
