package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class HoodSubsystem implements Subsystem {
    public static HoodSubsystem INSTANCE = new HoodSubsystem();
    private HoodSubsystem() { }

    public static double LOW_ANGLE = 0;
    public static double MID_ANGLE = 0;
    public static double HIGH_ANGLE = 0;

    public ServoEx hoodServo = new ServoEx("hood");

    public Command lowAngle = new SetPosition(hoodServo, LOW_ANGLE);
    public Command midAngle = new SetPosition(hoodServo, MID_ANGLE);
    public Command highAngle = new SetPosition(hoodServo, HIGH_ANGLE);

    @Override
    public void initialize() {
        hoodServo.setPosition(LOW_ANGLE);
    }
}
