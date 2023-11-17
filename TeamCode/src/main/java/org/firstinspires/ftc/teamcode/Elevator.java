package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Elevator {
    private DcMotor extendMotor, elevatorMotor;
    // initializes DC Motor

    public Elevator(DcMotor extend, DcMotor elevator) {
        extendMotor = extend;
        elevatorMotor = elevator;
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // sets motor variable using hardware map then sets motor   to Run_using_encoder
    }

    public int getExtendedPos() {
        return extendMotor.getCurrentPosition();
    }
    public int getElevatorPos() {
        return elevatorMotor.getCurrentPosition();
    }
    public void setHeight(int targetPosition, double pow) {
        elevatorMotor.setTargetPosition(targetPosition);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setElevatorPower(pow);
    }
    public void setExtension(int targetPosition, double pow) {
        extendMotor.setTargetPosition(targetPosition);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setExtendPower(pow);
    }
    public void setExtendPower(double pow) {
        extendMotor.setPower(pow);
    }
    public void setElevatorPower(double pow) {
        elevatorMotor.setPower(pow);
    }
}
