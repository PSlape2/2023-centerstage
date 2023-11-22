package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

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
    public void setTargetPos(int targetPos){
       DcMotor1.setTargetPosition(targetPos);
       DcMotor2.setTargetPosition(targetPos);

       DcMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public int getLeftPosition() {
        return DcMotor1.getCurrentPosition();
    }
    public int getRightPosition() {
       return DcMotor2.getCurrentPosition();
    }
    public void stopMotors() {
        DcMotor1.setPower(0);
        DcMotor2.setPower(0);
    }
    public void forceMove(boolean movesUp){
        if(movesUp) {
            setTargetPos(10000000);
            setPower(1.0);
        } else {
            setTargetPos(-1000000000);
            setPower(1.0);
        }
    }
    public void resetEncoders(){
       DcMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       DcMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    /*
    Replace [motor name] with DcMotor1 or DcMotor2

    DcMotor1 is left, DcMotor2 is right
    [motor name].setPower(0) to stop
    [motor name].getCurrentPosition() returns motor position
    [motor name].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER) resets encoders

    TODO:
        void setTargetPos(int targetPo)
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


