package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Subsystems.HoodSubsystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

public class HoodTester extends NextFTCOpMode {
    public HoodTester() {
        addComponents(
                new SubsystemComponent(HoodSubsystem.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().triangle()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.lowAngle)
        ;
        Gamepads.gamepad1().square()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.midAngle)
        ;
        Gamepads.gamepad1().circle()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.highAngle)
        ;
    }
}
