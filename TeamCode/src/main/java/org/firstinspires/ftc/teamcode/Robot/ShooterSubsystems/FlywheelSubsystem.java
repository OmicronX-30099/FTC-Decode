package org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.internal.hardware.android.GpioPin;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;

public class FlywheelSubsystem implements Subsystem {
    public static FlywheelSubsystem INSTANCE = new FlywheelSubsystem();
    public FlywheelSubsystem() { }

    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwMotors = new MotorGroup(fwr, fwl);

    public void autoFlywheel(double velocity) {
        fwr.setPower(velocity+0.05);
        ActiveOpMode.telemetry().addData("power2", fwMotors.getPower());
    }

    @Override
    public void periodic() {

    }
}
