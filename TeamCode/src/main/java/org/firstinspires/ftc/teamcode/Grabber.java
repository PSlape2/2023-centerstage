package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {
    public static final double MAX_INTAKE_POSITION = Servo.MAX_POSITION * (1.0/4.0);
    public static final double MIN_INTAKE_POSITION = Servo.MIN_POSITION;
    public static final double MAX_PUSHER_POSITION = Servo.MAX_POSITION;
    public static final double MIN_PUSHER_POSITION = Servo.MIN_POSITION;
    private final Servo intake, pusher;

    public Grabber(Servo one, Servo two) { //gets two servos, Servo 1 and Servo 2.
        intake = one;
        pusher = two;
    }
    public void setIntake(double n){
        intake.setPosition(n);
    }

    public void setPusher(double n) {
        pusher.setPosition(n);
    }
    public double getPusher() {
        return pusher.getPosition();
    }
    public double getIntake() {
        return intake.getPosition();
    }
}
