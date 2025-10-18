package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

@TeleOp(name="Angle and Velocity Calibrator")
public class PositionalCalibration extends NextFTCOpMode {
    public PositionalCalibration() {
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }
    public ServoEx hood = new ServoEx("hood");
    /*
    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwl, fwr);
    */

    public double low = 0;
    public double mid = 0.3;
    public double high = 0.7;

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().x().whenTrue(
                () -> hood.setPosition(low)
        );
        Gamepads.gamepad1().y().whenTrue(
                () -> hood.setPosition(mid)
        );
        Gamepads.gamepad1().a().whenTrue(
                () -> hood.setPosition(high)
        );
        /*
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> fwm.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> fwm.setPower(0));

         */
    }

    @Override
    public void onUpdate() {
        //telemetry.addData("Velocity", fwm.getVelocity());
    }
}
