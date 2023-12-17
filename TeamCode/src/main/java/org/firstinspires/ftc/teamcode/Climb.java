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
       DcMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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
}

