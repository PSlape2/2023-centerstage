package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Grabber extends LinearOpMode {
    private final float MAX_VALUE = 1000;// initializes float, Max Value/Power = 1000

    private final float MIN_VALUE = 0;//initializes float, Minimum Value/Power = 0
    Servo one;
    Servo two;

    private boolean state;
    public Grabber() { //gets two servos, Servo 1 and Servo 2.
        one = hardwareMap.get(Servo.class, "Servo 1");//gets Servo class #1 from hardwareMap
        two = hardwareMap.get(Servo.class, "Servo 2");//gets Servo class #2 from hardwareMap
        state = false;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        if(gamepad2.a) {

            if(!state) {
                one.setPosition(MAX_VALUE); // Set the servo position to the maximum
                two.setPosition(MIN_VALUE); // Set the servo position to the maximum
                state = true;
            }
        } else {
            two.setPosition(MAX_VALUE); // Set the servo position to the maximum
            one.setPosition(MIN_VALUE); // Set the servo position to the default
            state = false;


        }

    }
}
