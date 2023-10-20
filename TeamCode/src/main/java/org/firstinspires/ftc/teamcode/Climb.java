package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Climb extends LinearOpMode {

    private DcMotor DcMotor1 = null; //establish motor, however null until called for
    private DcMotor DcMotor2 = null; //also establish motor but null until called for

    private static final int EXTENDED = 1000; //max value
    private static final int RETRACTED = 0; //min value

    @Override
    public void runOpMode() {
        DcMotor1 = hardwareMap.get(DcMotor.class, "DcMotor1" ); //gets motor software assigned to mentioned motor
        DcMotor2 = hardwareMap.get(DcMotor.class, "DcMotor2" ); //also assigns motor software
        DcMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION); //setting the motor in position
        DcMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION); //also setting motor in position

        double constPower = 1; //TODO: What is "use final"? Or what is a good value for constant power? //sets constant power to 1 on both motors

        boolean last = false; //show false if hand is retracted

        while (opModeIsActive()) {
            if(gamepad2.dpad_up) { //if gamepad thing is pointed up
                DcMotor1.setTargetPosition(EXTENDED); //hand extends
                DcMotor2.setTargetPosition(-EXTENDED); //hand extends
            } else if(gamepad2.dpad_down) { //if gamepad thing pointed down
                DcMotor1.setTargetPosition(RETRACTED); //hand retracts
                DcMotor2.setTargetPosition(-RETRACTED); //hand retracts
            }
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


