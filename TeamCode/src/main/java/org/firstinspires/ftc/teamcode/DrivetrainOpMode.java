package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
@TeleOp
public class DrivetrainOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        IMU imu = hardwareMap.get(IMU.class, "imu");

        Drivetrain drivetrain = new Drivetrain(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, imu);

        if (isStopRequested()) return;

        waitForStart();

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
//            double x = gamepad1.left_stick_x;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            if (gamepad1.options || gamepad1.start) {
                drivetrain.imuResetYaw();
                telemetry.addLine("IMU Reset!");
                telemetry.update();
            }

            drivetrain.move(y, x, rx, 1);
        }
    }
}
