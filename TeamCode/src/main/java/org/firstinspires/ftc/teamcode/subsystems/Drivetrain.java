package org.firstinspires.ftc.teamcode.subsystems;

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
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

// FIELD CENTRIC
public class Drivetrain {
    // Add constants to Robot class
    private static final double COUNTS_PER_INCH = 12.211; // Assuming ~145 counts per revolution
    private static final double MAX_METERS_PER_SECOND = 1.0;
    private static final double ROBOT_WIDTH = 18.0; // inches
    private static final double FULL_TURN_DIST = 56.5486; // inches
    private static final double CAMERA_HEIGHT = 2.0; // mm
    private static final double TIME_TO_ROTATE = 2.0 * Math.PI * MAX_METERS_PER_SECOND * 0.1;
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

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void robotCentricMove(double y, double x, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        /*
            y is forward, x is sideways, rx is for strafing
            y is always positive
            x is same sign on opposite corners
            rx is same sign on the same side
         */
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

        leftTarget = frontLeftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        rightTarget = frontRightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

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

    public void encoderDrive(double speed, double leftInches, double rightInches) {
        int leftTarget, rightTarget;

        leftTarget = frontLeftMotor.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        rightTarget = frontRightMotor.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

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

        while(frontLeftMotor.isBusy() || frontRightMotor.isBusy()) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void encoderStrafe(double speed, double inches) {
        int frontLeftTarget = frontLeftMotor.getCurrentPosition();
        int backLeftTarget = backLeftMotor.getCurrentPosition();
        int frontRightTarget = frontRightMotor.getCurrentPosition();
        int backRightTarget = backRightMotor.getCurrentPosition();

        if(inches > 0) {
            frontLeftTarget += (int) (inches * COUNTS_PER_INCH);
            backLeftTarget -= (int) (inches * COUNTS_PER_INCH);
            frontRightTarget -= (int) (inches * COUNTS_PER_INCH);
            backRightTarget += (int) (inches * COUNTS_PER_INCH);
        } else {
            frontLeftTarget -= (int) (inches * COUNTS_PER_INCH);
            backLeftTarget += (int) (inches * COUNTS_PER_INCH);
            frontRightTarget += (int) (inches * COUNTS_PER_INCH);
            backRightTarget -= (int) (inches * COUNTS_PER_INCH);
        }

        frontLeftMotor.setTargetPosition(frontLeftTarget);
        backLeftMotor.setTargetPosition(backLeftTarget);
        frontRightMotor.setTargetPosition(frontRightTarget);
        backRightMotor.setTargetPosition(backRightTarget);

        frontLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRightMotor.setPower(speed);
        backRightMotor.setPower(speed);
        frontLeftMotor.setPower(speed);
        backLeftMotor.setPower(speed);

        while(frontLeftMotor.isBusy() || frontRightMotor.isBusy() || backLeftMotor.isBusy() || backRightMotor.isBusy()) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void timeDrive(double rSpeed, double lSpeed, double timeOut) {
        ElapsedTime runtime = new ElapsedTime();

        frontRightMotor.setPower(rSpeed);
        backRightMotor.setPower(rSpeed);

        frontLeftMotor.setPower(lSpeed);
        backLeftMotor.setPower(lSpeed);

        while(runtime.seconds() < timeOut) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void timeDrive(double frontRightPower, double backRightPower, double frontLeftPower, double backLeftPower, double timeOut) {
        ElapsedTime runtime = new ElapsedTime();

        frontRightMotor.setPower(frontRightPower);
        backRightMotor.setPower(backRightPower);

        frontLeftMotor.setPower(frontLeftPower);
        backLeftMotor.setPower(backLeftPower);

        while(runtime.seconds() < timeOut) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void timeDrive(double speed, double timeOut) {
        ElapsedTime runtime = new ElapsedTime();

        frontRightMotor.setPower(speed);
        backRightMotor.setPower(speed);

        frontLeftMotor.setPower(speed);
        backLeftMotor.setPower(speed);

        while(runtime.seconds() < timeOut) {}

        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
    }

    public void turn(double angle, double speed) { // any number of degrees: (-) left, (+) right
        if(angle == 0) return;

        double turnDist = FULL_TURN_DIST * (angle / 360.0);

        if(angle < 0) {
            encoderDrive(speed, -turnDist, turnDist);
        } else {
            encoderDrive(speed, turnDist, -turnDist );
        }
    }

    public void setHeading(double angle, double speed) { // 0 - 360 degrees
        /*
        if(Math.abs(angle) > 180) {
            while(angle > 360) {
                angle -= 360;
            }

            while(angle < -360) {
                angle += 360;
            }

            if(angle > 0) {
                if(angle < 180) {
                    angle *= -1;
                } else if(angle > 180) {
                    angle -= 180;
                }
            } else if(angle < 0) {
                if(angle > -180) {
                    angle *= -1;
                } else if(angle < -180) {
                    angle += 180;
                }
            }
        }
        */

        double relativeAngle = angle - getHeading360();

        if(Math.abs(relativeAngle) > 180) {
            if(relativeAngle > 0) {
                relativeAngle = relativeAngle - 360;
            } else {
                relativeAngle = relativeAngle + 360;
            }
        }

        turn(relativeAngle, speed);
    }

    public void travelTo(Recognition recognition, double speed, double turnSpeed) {
        double x = (recognition.getLeft() + recognition.getRight()) / 2;
        double y = (recognition.getTop()  + recognition.getBottom()) / 2;

        double relativeAngle = recognition.estimateAngleToObject(AngleUnit.DEGREES);

        turn(relativeAngle, turnSpeed);

        double distance = (recognition.getHeight() * CAMERA_HEIGHT / recognition.getImageHeight())  - (ROBOT_WIDTH / 2.0);
        encoderDrive(
                speed,
                distance,
                distance,
                5
        );
    }

    public void imuResetYaw() {
        imu.resetYaw();
    }

    public YawPitchRollAngles imuGetYawAngles() {
        return imu.getRobotYawPitchRollAngles();
    }

    public double getHeading() {
        // left is negative, right is positive
        return -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    public double getHeading360() {
        double heading = getHeading();
        if(heading > 0) {
            return 360 - heading;
        } else if(heading < 0) {
            return -heading;
        } else {
            return 0;
        }
    }

    public double getRightPosition() {
        return frontRightMotor.getCurrentPosition();
    }
    public double getLeftPosition() {
        return frontLeftMotor.getCurrentPosition();
    }

    public double getMotorSpeed(int motor) { // Doesn't work
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
