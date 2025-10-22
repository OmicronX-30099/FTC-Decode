package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.HoodSubsystem;
import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.TurretSubsystem;

import dev.nextftc.core.subsystems.SubsystemGroup;
import dev.nextftc.ftc.ActiveOpMode;

public class ShooterSystem extends SubsystemGroup {
    public static ShooterSystem INSTANCE = new ShooterSystem();
    public ShooterSystem() {
        super(FlywheelSubsystem.INSTANCE, TurretSubsystem.INSTANCE, HoodSubsystem.INSTANCE);
    }

    public double[] getAngleVelocity(double distance) {
        if (distance < 2) {
            return new double[] {0,0.3};
        } else if (distance >= 2 && distance <3) {
            return new double[] {0,0.4};
        } else if (distance >= 3 && distance <4) {
            return new double[] {0,0.45};
        } else if (distance >= 4 && distance <5) {
            return new double[] {0.3,0.429};
        } else if (distance >= 5 && distance <6) {
            return new double[] {0.3,0.44};
        } else if (distance >= 6 && distance <7.5) {
            return new double[] {0.3,0.49};
        } else if (distance >= 7.5 && distance <8.5) {
            return new double[] {0.3,0.51};
        } else if (distance >= 8.5 && distance <9.5) {
            return new double[] {0.3,0.52};
        } else if (distance >= 9.5 && distance <10.5) {
            return new double[] {1,0.54};
        } else if (distance >= 10.5 && distance <11.5) {
            return new double[] {1,0.565};
        } else if (distance >= 11.5 && distance <12.5) {
            return new double[] {1,0.58};
        } else if (distance >= 12.5 && distance <13.5D) {
            return new double[] {1,0.614};
        } else {
            return new double[] {1,1650};
        }
    }

    public void autoAim(double x, double y, double heading) {
        double dx = 72-x;
        double dy = -72-y;
        double distance = Math.sqrt(x*x + y*y) / 12;
        double[] angleVel = getAngleVelocity(distance);
        TurretSubsystem.INSTANCE.setTurretHeading(dx, dy, heading);
        FlywheelSubsystem.INSTANCE.autoFlywheel(angleVel[1]);
        ActiveOpMode.telemetry().addData("Power", angleVel[1]);
        HoodSubsystem.INSTANCE.setPosition(angleVel[0]);
    }
}
