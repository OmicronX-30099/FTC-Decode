package org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;

public class HoodSubsystem implements Subsystem {
    public static HoodSubsystem INSTANCE = new HoodSubsystem();
    public HoodSubsystem() { }

    public ServoEx hoodServo = new ServoEx("hood");

    public void setPosition(double position) {
        hoodServo.setPosition(position);
    }

    @Override
    public void initialize() {
        hoodServo.setPosition(1);
    }
}
