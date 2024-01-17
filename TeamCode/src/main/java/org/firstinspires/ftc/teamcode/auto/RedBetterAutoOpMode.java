package org.firstinspires.ftc.teamcode.auto;


import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name="Red TFOD + Tags", group="Robot", preselectTeleOp="MainJavaOpMode")
public class RedBetterAutoOpMode extends LinearOpMode {
    private static final double DRIVE_SPEED = 0.45;
    private static final double TURN_SPEED = 0.3;

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "CenterStage.tflite";
    // TFOD_MODEL_FILE points to a model file stored onboard the Robot Controller's storage,
    // this is used when uploading models directly to the RC using the model upload interface.
    private static final String TFOD_MODEL_FILE = "..\\..\\..\\..\\..\\..\\..\\src\\tflitemodels\\CenterStage.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "Pixel"
    };

    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    private Drivetrain drive;
    private Elevator elevator;
    private Grabber grabber;
    @Override
    public void runOpMode() throws InterruptedException {
        int tagToPos;
        /* -----  tagToPos value  ------

        Left To Right:
            BLUE: 1, 2, 3
            RED: 4, 5, 6

         */

        tfod =  new TfodProcessor.Builder()
                //.setModelAssetName(TFOD_MODEL_ASSET)
                //.setModelFileName(TFOD_MODEL_FILE)
                //.setModelLabels(LABELS)
                .build();

        VisionPortal.Builder visBuilder = new VisionPortal.Builder();

        visBuilder.setCamera(hardwareMap.get(WebcamName.class, "Vision"));
        visBuilder.setCameraResolution(new Size(1280, 720));
        visBuilder.enableLiveView(true);
        visBuilder.addProcessor(tfod);

        visionPortal = visBuilder.build();

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
                hardwareMap.get(Servo.class, "GrabberServo1"),
                hardwareMap.get(Servo.class, "GrabberServo2")
        );

        waitForStart();

        grabber.setPusher(Grabber.MAX_PUSHER_POSITION);
        grabber.setPusher2(Grabber.MIN_PUSHER_POSITION);

        drive.encoderDrive(DRIVE_SPEED, 20, 20);

        List<Recognition> recognitions = tfod.getRecognitions();
        for(Recognition recog : recognitions) {
            double objX = (recog.getLeft() + recog.getRight()) / 2;
            double objY = (recog.getTop()  + recog.getBottom()) / 2;

            // Telemetry from example
            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recog.getLabel(), recog.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", objX, objY);
            telemetry.addData("- Size", "%.0f x %.0f", recog.getWidth(), recog.getHeight());
            telemetry.addData("- Angle: ", recog.estimateAngleToObject(AngleUnit.DEGREES));
        }

        sleep(250);

        if(recognitions.size() != 0) {
            Recognition recog = recognitions.get(0);
            double angle = recog.estimateAngleToObject(AngleUnit.DEGREES);

            if(angle > 10) {
                tagToPos = 4;
            } else if(angle < -10) {
                tagToPos = 6;
            } else {
                tagToPos = 5;
            }

            drive.travelTo(recog, DRIVE_SPEED, TURN_SPEED);

            sleep(500);

            grabber.setPusher(Grabber.MIN_PUSHER_POSITION);

            sleep(500);

            drive.turn(90, TURN_SPEED);

            sleep(250);

            switch(tagToPos) {
                case 4:
                    drive.encoderStrafe(DRIVE_SPEED, -5);
                    break;
                case 6:
                    drive.encoderStrafe(DRIVE_SPEED, 5);
                    break;
            }

            sleep(250);

            elevator.setAutoAngle(7800);
            elevator.setAutoExtend(2500);

            sleep(250);

            drive.encoderDrive(DRIVE_SPEED, 45, 45);

            sleep(250);

            grabber.setPusher2(Grabber.MAX_PUSHER_POSITION);

            sleep(250);
        }

    }
}
