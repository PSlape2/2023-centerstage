package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Climb {
    private final DcMotor DcMotor1;
    private final DcMotor DcMotor2;

    public Climb(DcMotor mot1, DcMotor mot2) {
        DcMotor1 = mot1;
        DcMotor2 = mot2;
    }
    public void setPower(double pow) {
        DcMotor1.setPower(pow); // LEFT MOTOR
        DcMotor2.setPower(pow); // RIGHT MOTOR
    }

    /*
    TODO:
        void setTargetPos(int targetPos)
        int getLeftPosition()
        int getRightPosition()
        void stopMotor()
        void forceMove(boolean movesUp)
        void resetEncoders()
     */
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


