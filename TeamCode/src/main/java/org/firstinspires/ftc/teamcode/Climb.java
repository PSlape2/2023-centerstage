package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Climb extends LinearOpMode {
    private DcMotor DcMotor1;
    private DcMotor DcMotor2;

    // TODO: Change the extended constant to the proper value
    private static final int EXTENDED = 1000;
    // TODO: Change the retracted constant to the proper value
    private static final int RETRACTED = 0;

    @Override
    public void runOpMode() {
        DcMotor1 = hardwareMap.get(DcMotor.class, "DcMotor1" );
        DcMotor2 = hardwareMap.get(DcMotor.class, "DcMotor2" );

        double constPower = 1; //TODO: What is "use final"? Or what is a good value for constant power?

        boolean last = false;

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.dpad_up) {
                DcMotor1.setTargetPosition(EXTENDED);
                DcMotor2.setTargetPosition(EXTENDED);
            } else if (gamepad2.dpad_down) {
                DcMotor1.setTargetPosition(RETRACTED);
                DcMotor2.setTargetPosition(RETRACTED);
            }

            if (gamepad2.dpad_up || gamepad2.dpad_down) {
                DcMotor1.setPower(0.3);
                DcMotor2.setPower(0.3);
            } else {
                DcMotor1.setPower(0);
                DcMotor2.setPower(0);
            }

            DcMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            DcMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}
/*
TODO:

Initialize 4 servos using hardwareMap.get(Servo.class, "ServoName")

Use gamepad2 get if the A button is pressed, turn the servos to the max value
The servos will return to the default value when the butotn is released.
Set CONSTANT values for the motor speed at the top of the class (use final)
Turn in, turn out, whole the button is pressed it will repeat over and over and over amd over and over and over and over and over and over and over and over and over .

Telemetry

boolean current is the whether the climb is extended or retracted

if retracted and dpad up is pressed, extend
if extended and dpad down is pressed, retract


 */


