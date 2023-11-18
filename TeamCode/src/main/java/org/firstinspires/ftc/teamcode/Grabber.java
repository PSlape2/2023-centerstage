package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {

    private int position; // 0- 270
    private boolean state;
    private final float MAX_VALUE = 1000;// initializes float, Max Value/Power = 1000
    private final float MIN_VALUE = 0;//initializes float, Minimum Value/Power = 0
    Servo intake, pusher;

    public Grabber(Servo one, Servo two) { //gets two servos, Servo 1 and Servo 2.
        intake = one;
        pusher = two;
        state = false;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean stateIn) {
        state = stateIn;
    }

    public void setIntake(double n){
        intake.setPosition(n);
    }

    public void setPusher(double n) {
        pusher.setPosition(n);
    }

}
