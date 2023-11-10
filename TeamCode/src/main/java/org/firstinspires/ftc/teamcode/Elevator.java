package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Elevator extends LinearOpMode {
    private DcMotor motor;
    // intializes DC MOtor
    private final float MAX_VALUE = 1000;
    // intializes  float that = 10000
    private final float MIN_VALUE = 0;
    //intializes float that = 0
    public Elevator() {
        motor = hardwareMap.get(DcMotor.class, "Elevator Motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // sets motor varaiable using hardware map then sets motor   to Run_using_encoder
    }

    @Override
    public void runOpMode(){
       //Set gamepad 2 joystick on the controller to move the elevator up and down

        boolean update = false;
        // makes new booleans sets to false

        while (opModeIsActive()) {
            // while the opmode is active make new float that takes the left stick y value
            float updated = -gamepad2.left_stick_y;
            float position = motor.getCurrentPosition();
            //makes new float that takes the motors position

            if (position <= MIN_VALUE) {
                if (updated > 0) motor.setPower(updated);
                // if the motor position is less than  or equal to 0 and the joystick value is greater than 0 set the motor power to the joystick value
            } else if(position >= MAX_VALUE) {
                if (updated < 0) motor.setPower(updated);
                // if the motor position is greter or equal to 1000 and the joystick value is less than 0 set the motor power  to the joystick value
            }


        }

    }
}
