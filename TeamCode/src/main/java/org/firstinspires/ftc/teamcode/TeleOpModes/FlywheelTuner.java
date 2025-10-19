package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
@Config
@TeleOp(name="Flywheel Tuner")
public class FlywheelTuner extends NextFTCOpMode {
    public FlywheelTuner() {
        addComponents(
                new SubsystemComponent(FlywheelSubsystem.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );
    }

    public static double velocity = 0;

    @Override
    public void onUpdate() {
        FlywheelSubsystem.INSTANCE.calibrateFlywheelVelocity(velocity);
        telemetry.addData("vel", FlywheelSubsystem.INSTANCE.getvel());
        telemetry.addData("targetvel", velocity);
    }
}
