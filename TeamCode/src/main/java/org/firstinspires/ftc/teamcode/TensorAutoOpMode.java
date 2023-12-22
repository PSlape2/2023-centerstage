package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;


import java.util.List;


public class TensorAutoOpMode extends LinearOpMode {
    private static final double CAMERA_HEIGHT = 2.0;
    private static final double DRIVE_SPEED = 0.45;
    private static final double TURN_SPEED = 0.3;
    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    private Drivetrain drive;
    private Elevator elevator;
    private Grabber grabber;




    @Override
    public void runOpMode() throws InterruptedException {
        tfod = TfodProcessor.easyCreateWithDefaults();


        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam"), tfod
        );


        drive = new Drivetrain(
                hardwareMap.get(DcMotor.class, "frontLeftMotor"),
                hardwareMap.get(DcMotor.class, "backLeftMotor"),
                hardwareMap.get(DcMotor.class, "frontRightMotor"),
                hardwareMap.get(DcMotor.class, "backRightMotor"),
                hardwareMap.get(IMU.class, "imu")
        );
        elevator = new Elevator(
                hardwareMap.get(DcMotor.class, "Extend Motor"),
                hardwareMap.get(DcMotor.class, "Angle Motor")
        );
        grabber = new Grabber(
                hardwareMap.get(Servo.class, "GrabberServo")
        );




        waitForStart();


        List<Recognition> recognitions = tfod.getRecognitions();
        for(Recognition recog : recognitions) {
            double objX = (recog.getLeft() + recog.getRight()) / 2;
            double objY = (recog.getTop()  + recog.getBottom()) / 2;




            // Telemetry from example
            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recog.getLabel(), recog.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", objX, objY);
            telemetry.addData("- Size", "%.0f x %.0f", recog.getWidth(), recog.getHeight());
        }
        grabber.setPusher(Grabber.MIN_PUSHER_POSITION);


        sleep(250);


        drive.travelTo(recognitions.get(0), DRIVE_SPEED, TURN_SPEED);


        sleep(500);


        grabber.setPusher(Grabber.MAX_PUSHER_POSITION);


        sleep(500);
    }
}
