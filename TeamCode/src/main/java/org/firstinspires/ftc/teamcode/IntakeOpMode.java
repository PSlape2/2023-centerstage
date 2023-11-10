package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class IntakeOpMode extends LinearOpMode {
    @Override
    public void runOpMode() {
        Servo servo1 = hardwareMap.get(Servo.class, "Servo1");


        Intake intake = new Intake(servo1);

        while (opModeIsActive()) {

            boolean latest = gamepad2.a; //get the current state of the gamepad2.a button
            boolean release = gamepad2.b;

            if (latest) {
                intake.setServo(Servo.MAX_POSITION);
            } else if (release) {
                intake.setServo(Servo.MIN_POSITION);
            }

        }

    }
}