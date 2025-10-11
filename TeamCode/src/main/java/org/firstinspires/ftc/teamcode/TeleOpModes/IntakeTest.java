package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class IntakeTest extends LinearOpMode {
    public DcMotorEx intake;
    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotorEx.class, "intake");

        waitForStart();

        if (isStopRequested()) { return;}

        while (opModeIsActive()) {
            intake.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
        }
    }
}
