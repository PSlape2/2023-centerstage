package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MainJavaOpMode extends LinearOpMode {

    private static final int EXTENDED = 1000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "Elevator Motor");

        Elevator elevator = new Elevator(motor);

        @Override
    public void runOpMode() throws InterruptedException {

        DcMotor DcMotor1 = hardwareMap.get(DcMotor.class, "Camel 1" );
        DcMotor DcMotor2 = hardwareMap.get(DcMotor.class, "Camel 2" );
        Servo servo1 = hardwareMap.get(Servo.class, "Sardine 1");
        Servo servo2 = hardwareMap.get(Servo.class, "Gorilla 1");
        Servo servo11 = hardwareMap.get(Servo.class, "Gorilla 2");

        Climb climb = new Climb(DcMotor1, DcMotor2);
        Grabber grabber = new Grabber(servo11);
        Intake intake = new Intake(servo1);
        Shooter shooter = new Shooter(servo1);

        while (opModeIsActive()) {
            boolean latest = gamepad2.a; //get the current state of the gamepad2.a button
            boolean release = gamepad2.b;
            if (latest) {
                intake.setServo(Servo.MAX_POSITION);
            } else if (release) {
                intake.setServo(Servo.MIN_POSITION);
            }
        }
            if (gamepad2.dpad_up) {
                climb.setTargetPos(EXTENDED);
            } else if (gamepad2.dpad_down) {
               climb.setTargetPos(RETRACTED);
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                climb.setPower(0.3);
            } else {
                climb.stopMotor();
            }
            if (gamepad2.x){
                shooter.setPosition(Servo.MAX_POSITION);
            } else {
                shooter.setPosition(Servo.MIN_POSITION);
            }
            if (gamepad2.dpad_left || gamepad2.dpad_right) {
                climb.setPower(0.3);
            } else {
                climb.stopMotor();
            }
        }
    }
}
