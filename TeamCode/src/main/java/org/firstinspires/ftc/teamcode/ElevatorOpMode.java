package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class ElevatorOpMode extends LinearOpMode {
    private final int MAX_VALUE = 1000;
    // initializes float that = 10000
    private final int MIN_VALUE = 0;
    //initializes float that = 0
    public void runOpMode() throws InterruptedException {
        DcMotor motor = hardwareMap.get(DcMotor.class, "Elevator Motor");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "Elevator Extend Motor");
        Elevator elevator = new Elevator(motor, motor2);

        while (opModeIsActive()) {
            // while the opmode is active make new float that takes the left stick y value
            float updated = -gamepad2.left_stick_y;
            float position = elevator.getElevatorPos();
            //makes new float that takes the motors position

            if (updated > 0)  {
                elevator.setHeight(MAX_VALUE, 0.3 * updated);
            }
                // if the motor position is less than  or equal to 0 and the joystick value is greater than 0 set the motor power to the joystick value
            if (updated < 0) {
                elevator.setHeight(MIN_VALUE, 0.3 * updated);
            }
                // if the motor position is greater or equal to 1000 and the joystick value is less than 0 set the motor power  to the joystick value
        }
    }
}
