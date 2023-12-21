package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Elevator {
    private final DcMotor extendMotor, angleMotor;

    private static final int MAX_EXTEND = 100000;
    // initializes DC Motor

    public Elevator(DcMotor extendMotor, DcMotor angleMotor) {
        this.extendMotor = extendMotor;
        this.angleMotor = angleMotor;
    }

    public int getExtensionPos() {
        return extendMotor.getCurrentPosition();
    }
    public int getAnglePos() {
        return angleMotor.getCurrentPosition();
    }
    public void setExtension(int targetPosition, double pow) {
        extendMotor.setTargetPosition(targetPosition);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendMotor.setPower(pow);
    }
    public void setAutoExtend(int targetPosition) {
        extendMotor.setTargetPosition(targetPosition);
        extendMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        extendMotor.setPower(0.75);
        while(extendMotor.isBusy()) {}
        extendMotor.setPower(0);
    }
    public void setAngle(int targetPos, double pow) {
        angleMotor.setTargetPosition(targetPos);
        angleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        angleMotor.setPower(pow);
    }
    public void setAutoAngle(int targetPos) {
        angleMotor.setTargetPosition(targetPos);
        angleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        angleMotor.setPower(0.75);
        while(angleMotor.isBusy()) {}
        angleMotor.setPower(0);
    }
    public void stopExtend() {
        extendMotor.setTargetPosition(MAX_EXTEND);
        extendMotor.setPower(0);
    }
    public void stopAngle(){
        angleMotor.setPower(0);
    }
    public void resetEncoders() {
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
