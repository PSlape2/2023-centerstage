package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class FourthMainJavaOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo1 = hardwareMap.get(Servo.class, "shooter");

        Shooter shooter = new Shooter(servo1);

        while (opModeIsActive()) {
            if (gamepad2.x){
                shooter.setPosition(Servo.MAX_POSITION);
            } else {
                shooter.setPosition(Servo.MIN_POSITION);
            }
        }
    }
}
