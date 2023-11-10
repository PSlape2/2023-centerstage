package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MainJavaOpMode extends LinearOpMode {

    private static final int EXTENDED = 1000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor DcMotor1 = hardwareMap.get(DcMotor.class, "DcMotor1" );
        DcMotor DcMotor2 = hardwareMap.get(DcMotor.class, "DcMotor2" );

        Climb climb = new Climb(DcMotor1, DcMotor2);

        while (opModeIsActive()) {
            if (gamepad2.dpad_up) {
                climb.setTargetPos(EXTENDED);
            } else if (gamepad2.dpad_down) {
               climb.setTargetPos(RETRACTED);
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                climb.setPower(0.3);
            } else {
                climb.stopMotor();
            }

        }
    }
}
