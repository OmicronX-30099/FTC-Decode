package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="testing")
public class testing extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo hood_servo = hardwareMap.get(Servo.class, "hood");
        DcMotorEx flyWheelL = hardwareMap.get(DcMotorEx.class, "fwl");
        DcMotorEx flyWheelR = hardwareMap.get(DcMotorEx.class, "fwr");
        hood_servo.setPosition(0);

        waitForStart();

        if(isStopRequested()) return;

        while (opModeIsActive()){
            flyWheelR.setPower(gamepad1.right_trigger);
            flyWheelL.setPower(gamepad1.right_trigger);
            flyWheelR.setPower(-gamepad1.left_trigger);
            flyWheelL.setPower(-gamepad1.left_trigger);

            telemetry.addData("Flywheel V:", flyWheelR.getVelocity());
            telemetry.addData("Flywheel Power:", flyWheelR.getPower());
            telemetry.update();
            hood_servo.setPosition(0);
        }
    }
}
