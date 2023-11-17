package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MainJavaOpMode extends LinearOpMode {
    private static final int EXTENDED = 11000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "Elevator Motor");
        DcMotor ClimbLeft = hardwareMap.get(DcMotor.class, "ClimbLeftMotor");
        DcMotor ClimbRight = hardwareMap.get(DcMotor.class, "ClimbRightMotor");
        Servo servo1 = hardwareMap.get(Servo.class, "Sardine 1");
        Servo servo2 = hardwareMap.get(Servo.class, "Gorilla 1");
        Servo servo11 = hardwareMap.get(Servo.class, "Gorilla 2");

        Elevator elevator = new Elevator(motor);
        Climb climb = new Climb(ClimbLeft, ClimbRight);
        Grabber grabber = new Grabber(servo11);
        Intake intake = new Intake(servo1);
        Shooter shooter = new Shooter(servo1);

        while (opModeIsActive()) {
            // INTAKE CONTROLS
            if (gamepad2.a) {
                intake.setServo(Servo.MAX_POSITION);
            } else if (gamepad2.b) {
                intake.setServo(Servo.MIN_POSITION);
            }

            // CLIMB CONTROLS
            if (gamepad2.dpad_up) {
                climb.setTargetPos(EXTENDED);
                climb.setPower(0.3);
            } else if (gamepad2.dpad_down) {
                climb.setTargetPos(RETRACTED);
                climb.setPower(0.3);
            } else {
                climb.stopMotor();
            }

            // SHOOTER CONTROLS
            if (gamepad2.x){
                shooter.setPosition(Servo.MAX_POSITION);
            } else {
                shooter.setPosition(Servo.MIN_POSITION);
            }
        }
    }
}