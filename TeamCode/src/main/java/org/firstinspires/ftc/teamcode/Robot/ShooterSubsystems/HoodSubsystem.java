package org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class HoodSubsystem implements Subsystem {
    public static HoodSubsystem INSTANCE = new HoodSubsystem();
    public HoodSubsystem() { }

    public ServoEx hoodServo = new ServoEx("hood");

    public double lowAngle = 0;
    public double midAngle = 0.3;
    public double highAngle = 1;

    public Command lowPosition = new SetPosition(hoodServo, lowAngle);
    public Command midPosition = new SetPosition(hoodServo, midAngle);
    public Command highPosition = new SetPosition(hoodServo, highAngle);

    @Override
    public void initialize() {
        hoodServo.setPosition(highAngle);
    }
}
