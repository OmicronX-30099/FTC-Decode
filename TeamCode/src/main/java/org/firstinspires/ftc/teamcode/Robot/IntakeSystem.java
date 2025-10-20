package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Robot.IntakeSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Robot.IntakeSubsystems.KickerSubsystem;

import java.time.Duration;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.subsystems.SubsystemGroup;

public class IntakeSystem extends SubsystemGroup {
    public static IntakeSystem INSTANCE = new IntakeSystem();
    private IntakeSystem() {
        super(
                KickerSubsystem.INSTANCE,
                IntakeSubsystem.INSTANCE
        );
    }

    public Command kick = new SequentialGroup(
            KickerSubsystem.INSTANCE.kickBall,
            new Delay(0.1),
            KickerSubsystem.INSTANCE.reset
    );
}
