package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ClimbOpMode extends LinearOpMode {
    private static final int EXTENDED = 11000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor DcMotor1 = hardwareMap.get(DcMotor.class, "DcMotor1" );
        DcMotor DcMotor2 = hardwareMap.get(DcMotor.class, "DcMotor2" );

        Climb climb = new Climb(DcMotor1, DcMotor2);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("DcMotor1 Current Position", DcMotor1.getCurrentPosition());
            telemetry.addData("DcMotor1 Target Position", DcMotor1.getTargetPosition());
            telemetry.addData("DcMotor2 Current Position", DcMotor2.getCurrentPosition());
            telemetry.addData("DcMotor2 Target Position", DcMotor2.getTargetPosition());
            telemetry.update();
            if (gamepad2.dpad_up) {
                climb.setTargetPos(EXTENDED);
            } else if (gamepad2.dpad_down) {
               climb.setTargetPos(RETRACTED);
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                climb.setPower(1);
            } else {
                climb.stopMotor();
            }
        }
    }
}
