package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SecondLinearOpMode extends LinearOpMode {
    private final Grabber grabber = new Grabber(hardwareMap.get(Servo.class, "Servo 1"), hardwareMap.get(Servo.class, "Servo 2"));//gets Servo class #1 from hardwareMap);
    @Override
    public void runOpMode() {

        if(gamepad2.a) {

            if(!grabber.getState()) {

                grabber.setState(true);
            }
        } else {

            grabber.setState(false);
        }

    }
}
