package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Shooter extends LinearOpMode {

    private Servo servo1;
    @Override
    public void runOpMode() throws InterruptedException {
        servo1 = hardwareMap.get(Servo.class, "shooter");

        while (opModeIsActive()) {
            if (gamepad2.x){
                servo1.setPosition(Servo.MAX_POSITION);
            } else {
                servo1.setPosition(Servo.MIN_POSITION);
            }
        }
    }
}

