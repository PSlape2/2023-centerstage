package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {

    private int position; // 0- 270
    private boolean state;
    public static final double MAX_INTAKE_POSITION = Servo.MAX_POSITION * (120.0/180.0);
    public static final double MIN_INTAKE_POSITION = Servo.MIN_POSITION;
    public static final double MAX_PUSHER_POSITION = Servo.MAX_POSITION;
    public static final double MIN_PUSHER_POSITION = Servo.MIN_POSITION;
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
