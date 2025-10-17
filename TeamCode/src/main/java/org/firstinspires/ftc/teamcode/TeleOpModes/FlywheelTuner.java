package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class FlywheelTuner extends NextFTCOpMode {
    public FlywheelTuner() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                new SubsystemComponent(FlywheelSystem.INSTANCE)
        );
    }

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(FlywheelSystem.INSTANCE.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(FlywheelSystem.INSTANCE.setPower(0))
        ;
    }

    @Override
    public void onUpdate() {
        ActiveOpMode.telemetry().addData("Velocity", FlywheelSystem.INSTANCE.getVel());
        ActiveOpMode.telemetry().update();
    }
}
