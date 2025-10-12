package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class IntakeTest extends LinearOpMode {
    public DcMotorEx intake,flywheelL,flywheelR;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        flywheelL = hardwareMap.get(DcMotorEx.class, "fwl");
        flywheelR = hardwareMap.get(DcMotorEx.class, "fwr");

        flywheelR.setDirection(DcMotorEx.Direction.REVERSE);

        waitForStart();

        if (isStopRequested()) { return;}

        while (opModeIsActive()) {
            intake.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
            flywheelR.setPower(-gamepad1.left_stick_y);
            flywheelL.setPower(-gamepad1.left_stick_y);

        }
    }
}
