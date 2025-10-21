package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.HoodSubsystem;
import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.TurretSubsystem;

import dev.nextftc.core.subsystems.SubsystemGroup;

public class ShooterSystem extends SubsystemGroup {
    public static ShooterSystem INSTANCE = new ShooterSystem();
    public ShooterSystem() {
        super(FlywheelSubsystem.INSTANCE, TurretSubsystem.INSTANCE, HoodSubsystem.INSTANCE);
    }

    public double[] getAngleVelocity(double distance) {
        if (distance < 2) {
            return new double[] {0,800};
        } else if (distance >= 2 && distance <3) {
            return new double[] {0,1000};
        } else if (distance >= 3 && distance <4) {
            return new double[] {0,1140};
        } else if (distance >= 4 && distance <5) {
            return new double[] {0.3,1100};
        } else if (distance >= 5 && distance <6) {
            return new double[] {0.3,1130};
        } else if (distance >= 6 && distance <7.5) {
            return new double[] {0.3,1220};
        } else if (distance >= 7.5 && distance <8.5) {
            return new double[] {0.3,1280};
        } else if (distance >= 8.5 && distance <9.5) {
            return new double[] {0.3,1350};
        } else if (distance >= 9.5 && distance <10.5) {
            return new double[] {1,1400};
        } else if (distance >= 10.5 && distance <11.5) {
            return new double[] {1,1460};
        } else if (distance >= 11.5 && distance <12.5) {
            return new double[] {1,1500};
        } else if (distance >= 12.5 && distance <13.5D) {
            return new double[] {1,1550};
        } else {
            return new double[] {1,1650};
        }
    }

    public void autoAim(double x, double y, double heading) {
        double dx = 48
        double distance = Math.sqrt(x*x + y*y)
        TurretSubsystem.INSTANCE.setTurretHeading();
    }

}
