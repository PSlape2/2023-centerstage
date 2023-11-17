package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous(name="Move to Middle Line", group="Robot", preselectTeleOp="MainJavaOpMode")
public class DriveAutoOpMode extends LinearOpMode {
    private static final double kDriveSpeed = 0.6;
    private static final double kTurnSpeed = 0.5;
    private Drivetrain drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new Drivetrain(
                hardwareMap.dcMotor.get("frontLeftMotor"),
                hardwareMap.dcMotor.get("backLeftMotor"),
                hardwareMap.dcMotor.get("frontRightMotor"),
                hardwareMap.dcMotor.get("backRightMotor"),
                hardwareMap.get(IMU.class, "imu")
        );

        waitForStart();

        drive.encoderDrive(kDriveSpeed, 10, 10, 5);
    }
}
