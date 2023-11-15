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

    public int getPosition() {
        return motor.getCurrentPosition();
    }
    public void setTargetPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPower(double pow) {
        motor.setPower(pow);
    }


}
