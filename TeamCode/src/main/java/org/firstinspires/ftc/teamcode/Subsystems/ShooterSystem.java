package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.subsystems.SubsystemGroup;

public class ShooterSystem extends SubsystemGroup {
    public static ShooterSystem INSTANCE = new ShooterSystem();

    private ShooterSystem() {
        super(
                TurretSubsystem.INSTANCE
        );
    }
}
