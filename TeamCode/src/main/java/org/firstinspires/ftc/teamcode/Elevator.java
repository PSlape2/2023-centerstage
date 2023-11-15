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
    public void setHeight(int targetPosition) {
        elevatorMotor.setTargetPosition(targetPosition);
        elevatorMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setExtension(int targetPosition) {
        extendMotor.setTargetPosition(targetPosition);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPower(double pow) {
        motor.setPower(pow);
    }


}
