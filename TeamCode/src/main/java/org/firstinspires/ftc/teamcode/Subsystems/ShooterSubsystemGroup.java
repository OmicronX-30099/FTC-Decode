package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.subsystems.SubsystemGroup;

import com.pedropathing.geometry.Pose;

public class ShooterSubsystemGroup extends SubsystemGroup {
    public static ShooterSubsystemGroup INSTANCE = new ShooterSubsystemGroup();
    private ShooterSubsystemGroup() {
        super(TurretSubsystem.INSTANCE);
    }
    public void autoShooter(Pose currentPos) {
        TurretSubsystem.INSTANCE.calculateHeading(currentPos);
    }
}
