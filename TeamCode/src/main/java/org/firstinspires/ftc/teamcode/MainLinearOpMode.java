package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainLinearOpMode extends LinearOpMode {
    private Climb climb;
    private Elevator elevator;
    private Grabber grabber;
    private Shooter shooter;
    private IntakeJavaOpMode intake;
    private Drivetrain drivetrain;

    @Override
    public void runOpMode() {
        climb=new Climb();
        elevator=new Elevator();
        grabber=new Grabber();
        shooter=new Shooter();
        intake=new IntakeJavaOpMode();
        drivetrain = new Drivetrain();
        waitForStart();
        while(opModeIsActive()) {
            climb.runOpMode();
            elevator.runOpMode();
            grabber.runOpMode();
            shooter.runOpMode();
            intake.runOpMode();
            drivetrain.runOpMode();
        }
    }
}
