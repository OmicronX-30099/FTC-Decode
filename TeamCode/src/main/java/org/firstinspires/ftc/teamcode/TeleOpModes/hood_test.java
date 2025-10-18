package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.hardware.impl.ServoEx;

@TeleOp
public class hood_test extends LinearOpMode {
    public Servo hood;
    double hood_angle = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        hood = hardwareMap.get(Servo.class,"hood");

        waitForStart();
        hood.setPosition(0);

        if (isStopRequested()) { return;}

        while (opModeIsActive()) {
            if(gamepad1.squareWasPressed()){
                hood_angle += 0.1;
                hood.setPosition(hood_angle);
            }
            if (gamepad1.triangleWasPressed()){
                hood_angle -= 0.1;
                hood.setPosition(hood_angle);
            }
        }
    }
}
