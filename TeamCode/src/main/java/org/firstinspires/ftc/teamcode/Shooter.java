package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.Servo;

public class Shooter {

    private Servo servo1;
    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(Servo.class, "shooter");

    public Shooter(Servo servo1) {
        this.servo1 = servo1;
    }
    public void setPosition(double pos) {
        servo1.setPosition(pos);
    }
}

