package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class IntakeJavaOpMode extends LinearOpMode {

    private Servo servo1 = null;
    private Servo servo2 = null;
    private Servo servo3 = null;
    private Servo servo4 = null;
    private Servo servo5 = null;
    private Servo servo6 = null;

    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(Servo.class, "Servo1" );
        servo2 = hardwareMap.get(Servo.class, "Servo2" );
        servo3 = hardwareMap.get(Servo.class, "Servo3" );
        servo4 = hardwareMap.get(Servo.class, "Servo4" );
        servo5 = hardwareMap.get(Servo.class, "Servo5" );
        servo6 = hardwareMap.get(Servo.class, "Servo6" );
        double constPower = 1; //TODO: What is "use final"? Or what is a good value for constant power?

        boolean last = false;

        while (opModeIsActive()) {

            boolean latest = gamepad2.a; //get the current state of the gamepad2.a button

            if (latest != last) { //did it change form the last time?
                //this is so we only update the Servo when the gamepad2.a button state changes
                if (latest) {
                    //gamepad2.a is pressed
                    servo1.setPosition(Servo.MAX_POSITION);
                    servo2.setPosition(Servo.MAX_POSITION);
                    servo3.setPosition(Servo.MAX_POSITION);
                    servo4.setPosition(Servo.MAX_POSITION);
                    servo5.setPosition(Servo.MAX_POSITION);
                    servo6.setPosition(Servo.MAX_POSITION);
                } else {
                    //gamepad2.a is released
                    servo1.setPosition(Servo.MIN_POSITION); //TODO: what is the default position?
                    servo2.setPosition(Servo.MIN_POSITION);
                    servo3.setPosition(Servo.MIN_POSITION);
                    servo4.setPosition(Servo.MIN_POSITION);
                    servo5.setPosition(Servo.MIN_POSITION);
                    servo6.setPosition(Servo.MIN_POSITION);
                }
                last = latest; //update the last known gamepad2.a button state
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
 */