package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;


public class Grabber {
    public static final double MAX_PUSHER_POSITION = Servo.MAX_POSITION;
    public static final double MIN_PUSHER_POSITION = Servo.MIN_POSITION;
    private final Servo pusher;

    private final Servo pusher2;

    public Grabber(Servo one, Servo two) { //gets two servos, Servo 1 and Servo 2.
        pusher = one;
        pusher2 = two;
    }

    public void setPusher(double n) {

        pusher.setPosition(n);
    }

    public double getPusher() {

        return pusher.getPosition();
    }

    public void setPusher2(double a) {

        pusher2.setPosition(a);
    }

    public double getPusher2() {

        return pusher2.getPosition();
    }

}

