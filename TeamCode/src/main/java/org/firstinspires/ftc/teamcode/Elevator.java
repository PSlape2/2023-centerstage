package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Elevator {
    private DcMotor motor;
    // initializes DC Motor

    public Elevator(DcMotor motor) {
        this.motor = motor;
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // sets motor varaiable using hardware map then sets motor   to Run_using_encoder
    }

    public int getPosition() {
        return motor.getCurrentPosition();
    }
    public void setTargetPosition(int targetPosition) {
        motor.setTargetPosition(targetPosition);
    }

    public void setPower(double pow) {
        motor.setPower(pow);
    }


}
