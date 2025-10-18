package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

@TeleOp(name="Angle and Velocity Calibrator")
public class PositionalCalibration extends NextFTCOpMode {
    public ServoEx hood = new ServoEx("hood");
    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwl, fwr);

    public double low = 0;
    public double mid = 0;
    public double high = 0;

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().circle().whenBecomesTrue(
                () -> hood.setPosition(low)
        );
        Gamepads.gamepad1().square().whenBecomesTrue(
                () -> hood.setPosition(mid)
        );
        Gamepads.gamepad1().triangle().whenBecomesTrue(
                () -> hood.setPosition(high)
        );
    }

    @Override
    public void onUpdate() {
        fwm.setPower(Gamepads.gamepad1().rightTrigger().get()-Gamepads.gamepad1().leftTrigger().get());
        telemetry.addData("Velocity", fwm.getVelocity());
    }
}
