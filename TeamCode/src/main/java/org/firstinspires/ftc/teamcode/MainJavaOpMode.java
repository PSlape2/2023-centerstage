package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MainJavaOpMode extends LinearOpMode {
    private static final int EXTENDED = 11000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;
    private final int kMaxExtension = 1000;
    private final int kMinExtension = 0;
    private final int kMaxAngle = 1000;
    private final int kMinAngle = 0;
    private final int ELEVATOR_MAX = 1000;
    // initializes float that = 10000
    private final int ELEVATOR_MIN = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor extendMotor = hardwareMap.get(DcMotor.class, "Extend Motor");
        DcMotor angleMotor = hardwareMap.get(DcMotor.class, "Angle Motor");
        DcMotor ClimbLeft = hardwareMap.get(DcMotor.class, "ClimbLeftMotor");
        DcMotor ClimbRight = hardwareMap.get(DcMotor.class, "ClimbRightMotor");
        Servo ShooterServo = hardwareMap.get(Servo.class, "ShooterServo");
        Servo GrabberServo = hardwareMap.get(Servo.class, "GrabberServo");
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        IMU imu = hardwareMap.get(IMU.class, "imu");

        Drivetrain drivetrain = new Drivetrain(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, imu);
        Elevator elevator = new Elevator(extendMotor, angleMotor);
        Climb climb = new Climb(ClimbLeft, ClimbRight);
        Grabber grabber = new Grabber(GrabberServo);
        Shooter shooter = new Shooter(ShooterServo);

        waitForStart();

        while (opModeIsActive()) {
            if (isStopRequested()) return;

            // DRIVETRAIN CONTROLS
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            if (gamepad1.options) {
                drivetrain.imuResetYaw();
            } else if (y != 0 || x != 0 || rx != 0) {
                drivetrain.move(y, x, rx);
            }

            // GRABBER CONTROLS
            if (gamepad2.a) {
                grabber.setPosition(Servo.MAX_POSITION);
            } else if (gamepad2.b) {
                grabber.setPosition(Servo.MIN_POSITION);
            }

            // CLIMB CONTROLS
            if (gamepad2.dpad_up) {
                climb.setTargetPos(EXTENDED);
                climb.setPower(0.3);
            } else if (gamepad2.dpad_down) {
                climb.setTargetPos(RETRACTED);
                climb.setPower(0.3);
            } else {
                climb.stopMotor();
            }

            // SHOOTER CONTROLS - not using for 1st competition
            if (gamepad2.x) {
                shooter.setPosition(Servo.MAX_POSITION);
            } else {
                shooter.setPosition(Servo.MIN_POSITION);
            }

            // ELEVATOR CONTROLS
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