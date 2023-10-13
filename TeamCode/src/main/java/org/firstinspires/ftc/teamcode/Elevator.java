package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Elevator extends LinearOpMode {
    private DcMotor motor;
    private final float MAX_VALUE = 1000;
    private final float MIN_VALUE = 0;
    public Elevator() {
        motor = hardwareMap.get(DcMotor.class, "Elevator Motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void runOpMode(){
       //Set gamepad 2 joystick on the controller to move the elevator up and down

        boolean update = false;

        while (opModeIsActive()) {
            float updated = -gamepad2.left_stick_y;
            float position = motor.getCurrentPosition();

            if (position <= MIN_VALUE) {
                if (updated > 0) motor.setPower(updated);
            } else if(position >= MAX_VALUE) {
                if (updated < 0) motor.setPower(updated);
            }


        }

    }
}
