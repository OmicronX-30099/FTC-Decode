package org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems;

import com.acmerobotics.dashboard.config.Config;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;

public class FlywheelSubsystem implements Subsystem {
    public static FlywheelSubsystem INSTANCE = new FlywheelSubsystem();
    public FlywheelSubsystem() { }

    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwMotors = new MotorGroup(fwr, fwl);

    public double p, i, d, v, a, s = 0.0;

    public ControlSystem flywheelControl = ControlSystem.builder()
            .velPid(1, i, d)
            .basicFF(v, a, s)
            .build()
    ;

    public void autoFlywheel(double velocity) {
        flywheelControl.setGoal(new KineticState(0, velocity));
    }

    @Override
    public void periodic() {
        fwMotors.setPower(flywheelControl.calculate(fwMotors.getState()));
    }
}
