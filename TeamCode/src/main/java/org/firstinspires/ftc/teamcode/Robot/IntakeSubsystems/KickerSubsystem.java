package org.firstinspires.ftc.teamcode.Robot.IntakeSubsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

public class KickerSubsystem implements Subsystem {
    public static KickerSubsystem INSTANCE = new KickerSubsystem();
    public KickerSubsystem() { }

    public ServoEx kicker = new ServoEx("k");

    public static double DOWN_POS = 0;
    public static double UP_POS = 0.27;

    public Command kickBall = new SetPosition(kicker, UP_POS).requires(this);
    public Command reset = new SetPosition(kicker, DOWN_POS).requires(this);

    @Override
    public void initialize() {
        kicker.setPosition(0);
    }
}
