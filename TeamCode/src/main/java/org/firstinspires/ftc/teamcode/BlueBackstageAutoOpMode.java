package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Blue Backboard", group="Robot", preselectTeleOp="MainJavaOpMode")
public class BlueBackstageAutoOpMode extends LinearOpMode {
    private static final double SpeedDrive = 0.3;
    private static final double SpeedTurn = 0.3;
    private static final double ElevatorExtensionSpeed = 0.3;
    private static final double ElevatorAngleSpeed = 0.3;

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
                hardwareMap.get(Servo.class, "GrabberServo1"), hardwareMap.get(Servo.class, "GrabberServo2")
        );
        waitForStart();

        grabber.setPusher(Grabber.MIN_PUSHER_POSITION);

        sleep(250);

        drive.timeDrive(SpeedDrive, 0.75);

        sleep(250);

        drive.timeDrive(SpeedTurn, -SpeedTurn, 2.3);

        sleep(250);

        drive.timeDrive(-SpeedDrive, SpeedDrive, SpeedDrive, -SpeedDrive, 2.4);

        sleep(250);

        elevator.setAutoAngle(7500);

        sleep(250);

        elevator.setAutoExtend(2500);

        sleep(250);

        drive.timeDrive(SpeedDrive, 2.4);

        sleep(250);

        grabber.setPusher(Grabber.MAX_PUSHER_POSITION);

        sleep(500);

        drive.timeDrive(-SpeedDrive, 0.25);

        sleep(250);

        elevator.setAutoExtend(800);

        sleep(250);

        elevator.setAutoAngle(2000);

        sleep(500);
    }
}
