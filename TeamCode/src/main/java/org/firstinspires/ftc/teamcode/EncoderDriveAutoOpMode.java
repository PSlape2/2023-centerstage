package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;


public class EncoderDriveAutoOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drive = new Drivetrain(
                hardwareMap.get(DcMotor.class, "frontLeftMotor"),
                hardwareMap.get(DcMotor.class, "backLeftMotor"),
                hardwareMap.get(DcMotor.class, "frontRightMotor"),
                hardwareMap.get(DcMotor.class, "backRightMotor"),
                hardwareMap.get(IMU.class, "imu")
        );

        waitForStart();

        drive.encoderDrive(0.3, 40, 40, 2);
    }
}
