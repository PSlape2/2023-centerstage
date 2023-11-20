package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Place on Middle Line", group="Robot", preselectTeleOp="MainJavaOpMode")
public class PlaceAutoOpMode extends LinearOpMode {
    private static final double kDriveSpeed = 0.6;
    private static final double kTurnSpeed = 0.5;
    private static final double kElevatorExtensionSpeed = 0.3;
    private static final double kElevatorAngleSpeed = 0.3;

    private Drivetrain drive;
    private Elevator elevator;
    private Grabber grabber;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(
                hardwareMap.dcMotor.get("frontLeftMotor"),
                hardwareMap.dcMotor.get("backLeftMotor"),
                hardwareMap.dcMotor.get("frontRightMotor"),
                hardwareMap.dcMotor.get("backRightMotor"),
                hardwareMap.get(IMU.class, "imu")
        );
        elevator = new Elevator(
                hardwareMap.get(DcMotor.class, "Extend Motor"),
                hardwareMap.get(DcMotor.class, "Angle Motor")
        );
        grabber = new Grabber(
                hardwareMap.get(Servo.class, "GrabberServo"),
                hardwareMap.get(Servo.class, "PusherServo")
        );


        waitForStart();

        grabber.setPusher(Grabber.MIN_PUSHER_POSITION);

        sleep(250);

                // leftInches and rightInches are not in inches
        drive.encoderDrive(0.25, 1000, 1000, 0.7);
        //drive.encoderDrive(kTurnSpeed, -1000, 1000, 3);

        telemetry.addData("Status: ", "Finished Drive");
        telemetry.update();

        sleep(250);

                // about 1000 counts per revolution, 2.66 revolutions to max (probably)
        elevator.setAutoAngle(1000);
        elevator.setAutoExtend(1000);

        telemetry.addData("Status: ", "Extended");
        telemetry.update();

        sleep(250);

        grabber.setPusher(Grabber.MAX_PUSHER_POSITION);
        grabber.setIntake(Grabber.MIN_INTAKE_POSITION);

        telemetry.addData("Status: ", "Pixel Placed");
        telemetry.update();

        sleep(250);

        elevator.setAutoExtend(500);
        elevator.setAutoAngle(500);
        grabber.setIntake(Grabber.MAX_INTAKE_POSITION);

        telemetry.addData("Status: ", "Auto Finished");
        telemetry.update();

        sleep(250);
    }
}
