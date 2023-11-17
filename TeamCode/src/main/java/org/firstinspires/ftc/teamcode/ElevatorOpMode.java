package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class ElevatorOpMode extends LinearOpMode {
    private final int kMaxExtension = 1000;
    private final int kMinExtension = 0;
    private final int kMaxAngle = 1000;
    private final int kMinAngle = 0;
    public void runOpMode() throws InterruptedException {
        Elevator elevator = new Elevator(
                hardwareMap.get(DcMotor.class, "Extend Motor"),
                hardwareMap.get(DcMotor.class, "Angle Motor")
        );

        waitForStart();

        while (opModeIsActive()) {
            // while the opmode is active make new float that takes the left stick y value
            float extendInput = -gamepad2.left_stick_y;
            float angleInput = -gamepad2.right_stick_y;

            if (extendInput > 0.1)  {
                elevator.setExtension(kMaxExtension, 0.3);
            }
                // if the motor position is less than  or equal to 0 and the joystick value is greater than 0 set the motor power to the joystick value
            else if (extendInput < -0.1) {
                elevator.setExtension(kMinExtension, 0.3);
            }
            else {
                elevator.setExtension(elevator.getAnglePos(), 0.2);
            }
                // if the motor position is greater or equal to 1000 and the joystick value is less than 0 set the motor power  to the joystick value

            if(angleInput > 0.1) {
                elevator.setAngle(kMaxAngle, 0.3);
            } else if(angleInput < -0.1) {
                elevator.setAngle(kMinAngle, 0.3);
            } else {
                elevator.setAngle(elevator.getAnglePos(), 0.3);
            }
        }
    }
}
