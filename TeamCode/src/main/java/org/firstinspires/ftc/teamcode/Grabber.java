package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {

    private int position; // 0- 270
    private boolean state;
    private final float MAX_VALUE = 1000;// initializes float, Max Value/Power = 1000
    private final float MIN_VALUE = 0;//initializes float, Minimum Value/Power = 0
    Servo one;

    public Grabber(Servo one) { //gets two servos, Servo 1 and Servo 2.
        this.one = one;
        state = false;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean stateIn) {
        state = stateIn;
    }

    public void setPosition (int n){
        one.setPosition(n);
    }

}
