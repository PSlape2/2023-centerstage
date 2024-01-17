package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Do Nothing Auto", group="Robot", preselectTeleOp="MainJavaOpMode")
public class DoNothingAutoOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status: ", "Waiting for Start");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("Status: ", "Active");
            telemetry.speak("Doing Nothing");
            sleep(1000);
            telemetry.update();
        }
    }
}
