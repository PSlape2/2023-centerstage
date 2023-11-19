package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

// FIELD CENTRIC
public class Drivetrain {
    private static final double COUNTS_PER_INCH = 2.199114; // 28 counts per revolution
    private final DcMotor frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;
    private final IMU imu;
    private double frontLeftPower, backLeftPower, frontRightPower, backRightPower;
    int driveMode;

    public Drivetrain(DcMotor frontLeftMotorIn, DcMotor backLeftMotorIn, DcMotor frontRightMotorIn, DcMotor backRightMotorIn, IMU imuIn) {
        frontLeftMotor = frontLeftMotorIn;
        backLeftMotor = backLeftMotorIn;
        frontRightMotor = frontRightMotorIn;
        backRightMotor = backRightMotorIn;
        imu = imuIn;

        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        driveMode = 0;
        imu.initialize(parameters);
    }

    public void robotCentricMove(double y, double x, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
    }
    public void robotCentricMove(double y, double x, double rx, double speed) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setPower(frontLeftPower * speed);
        backLeftMotor.setPower(backLeftPower * speed);
        frontRightMotor.setPower(frontRightPower * speed);
        backRightMotor.setPower(backRightPower * speed);
    }

    public void move(double x, double y , double rx, double speed) {
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rotX *= 1.1; // Counteract imperfect strafing

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        frontLeftPower = (rotY + rotX + rx) / denominator;
        backLeftPower = (rotY - rotX + rx) / denominator;
        frontRightPower = (rotY - rotX - rx) / denominator;
        backRightPower = (rotY + rotX - rx) / denominator;

        frontLeftMotor.setPower(frontLeftPower * speed);
        backLeftMotor.setPower(backLeftPower * speed);
        frontRightMotor.setPower(frontRightPower * speed);
        backRightMotor.setPower(backRightPower * speed);
    }
    public void tankDrive(double right, double left) {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeftMotor.setPower(left);
        backLeftMotor.setPower(left);
        frontRightMotor.setPower(right);
        backRightMotor.setPower(right);
    }
    public void strafeDrive(double speed) {
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightMotor.setPower(-speed);
        backRightMotor.setPower(speed);
        frontLeftMotor.setPower(speed);
        backLeftMotor.setPower(-speed);
    }

    public void mecanumDrive(double x, double y, double rot) {
        double denominator = Math.abs(y) + Math.abs(x) + Math.abs(rot);
        frontRightPower = (y - x - rot) / denominator;
        backRightPower = (y + x - rot) / denominator;
        frontLeftPower = (y + x + rot) / denominator;
        backLeftPower = (y - x + rot) / denominator;

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);
        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);
    }
    public void mecanumDrive(double x, double y, double rot, double speed) {
        double denominator = Math.abs(y) + Math.abs(x) + Math.abs(rot);
        frontRightPower = (y - x - rot) / denominator;
        backRightPower = (y + x - rot) / denominator;
        frontLeftPower = (y + x + rot) / denominator;
        backLeftPower = (y - x + rot) / denominator;

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightMotor.setPower(frontRightPower * speed);
        backRightMotor.setPower(backRightPower * speed);
        frontLeftMotor.setPower(frontLeftPower * speed);
        backLeftMotor.setPower(backLeftPower * speed);
    }
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeOut) {

        ElapsedTime runtime = new ElapsedTime();
        int leftTarget, rightTarget;

        leftTarget = frontLeftMotor.getCurrentPosition() + (int) (leftInches);
        rightTarget = frontRightMotor.getCurrentPosition() + (int) (rightInches);

        frontRightMotor.setTargetPosition(rightTarget);
        backRightMotor.setTargetPosition(rightTarget);
        frontLeftMotor.setTargetPosition(leftTarget);
        backLeftMotor.setTargetPosition(leftTarget);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRightMotor.setPower(speed);
        backRightMotor.setPower(speed);

        frontLeftMotor.setPower(speed);
        backLeftMotor.setPower(speed);

        while((runtime.seconds() < timeOut) && (frontLeftMotor.isBusy() || frontRightMotor.isBusy())) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void imuResetYaw() {
        imu.resetYaw();
    }

    public YawPitchRollAngles imuGetYawAngles() {
        return imu.getRobotYawPitchRollAngles();
    }

    public double getRightPosition() {
        return frontRightMotor.getCurrentPosition();
    }
    public double getLeftPosition() {
        return frontLeftMotor.getCurrentPosition();
    }

    public double getMotorSpeed(int motor) {
        switch(motor) {
            case 0:
                return frontRightPower;
            case 1:
                return backRightPower;
            case 2:
                return frontLeftPower;
            case 3:
                return backLeftPower;
            default:
                return 0;
        }
    }
    public void setMode(int mode) {
        driveMode = mode;
    }
    public int getMode() {
        return driveMode;
    }
}
