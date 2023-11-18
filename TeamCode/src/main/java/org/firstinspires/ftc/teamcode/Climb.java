package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Climb {
    private final DcMotor DcMotor1;
    private final DcMotor DcMotor2;
    // TODO: Change the extended constant to the proper value


    public Climb(DcMotor mot1, DcMotor mot2) {
        DcMotor1 = mot1;
        DcMotor2 = mot2;
    }
    public void setPower(double pow) {
        DcMotor1.setPower(pow); // LEFT MOTOR
        DcMotor2.setPower(pow); // RIGHT MOTOR
    }

    public void stopMotor() {
        DcMotor1.setPower(0);
        DcMotor2.setPower(0);
    }
    public void setTargetPos(int pos) {
        DcMotor1.setTargetPosition(pos);
        DcMotor2.setTargetPosition(pos);

        DcMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        DcMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void forceMove(boolean movesUp) {
        DcMotor1.setPower(1);
        DcMotor2.setPower(1);

        DcMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (movesUp) {
            DcMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
            DcMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            DcMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
            DcMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public int getLeftPosition() {
        return DcMotor1.getCurrentPosition();
    }

    public int getRightPosition() {
        return DcMotor2.getCurrentPosition();
    }

    public void resetEncoders() {
        DcMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DcMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
/*
TODO:

Initialize 4 servos using hardwareMap.get(Servo.class, "ServoName")

Use gamepad2 get if the A button is pressed, turn the servos to the max value
The servos will return to the default value when the butotn is released.
Set CONSTANT values for the motor speed at the top of the class (use final)
Turn in, turn out, whole the button is pressed it will repeat over and over and over amd over and over and over and over and over and over and over and over and over .

Telemetry

boolean current is the whether the climb is extended or retracted

if retracted and dpad up is pressed, extend
if extended and dpad down is pressed, retract


 */


