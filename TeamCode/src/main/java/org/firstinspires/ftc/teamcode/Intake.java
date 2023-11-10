package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo servo1 = null;

    public Intake( Servo servo1) {

        this.servo1 = servo1;

    }
    public void setServo(double pos) {
        servo1.setPosition(pos);
    }



}
