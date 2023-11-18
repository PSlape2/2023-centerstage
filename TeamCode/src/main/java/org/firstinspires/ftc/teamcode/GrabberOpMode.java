package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class GrabberOpMode extends LinearOpMode {
    private final Grabber grabber = new Grabber(
            hardwareMap.get(Servo.class, "GrabberServo"),
            hardwareMap.get(Servo.class, "GrabberPusherServo")
    );
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
