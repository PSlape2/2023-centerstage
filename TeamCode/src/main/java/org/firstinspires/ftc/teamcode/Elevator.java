package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Elevator {
    private DcMotor elevatorMotor;
    // initializes DC Motor

    public Elevator(DcMotor elevator) {
        elevatorMotor = elevator;
        elevatorMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // sets motor variable using hardware map then sets motor   to Run_using_encoder
    }

    public int getElevatorPos() {
        return elevatorMotor.getCurrentPosition();
    }
    public void setHeight(int targetPosition, double pow) {
        elevatorMotor.setTargetPosition(targetPosition);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        setElevatorPower(pow);
    }

    public void setElevatorPower(double pow) {
        elevatorMotor.setPower(pow);
    }
}
