package org.firstinspires.ftc.teamcode.Robot.IntakeSubsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;

public class IntakeSubsystem implements Subsystem {
    public static IntakeSubsystem INSTANCE = new IntakeSubsystem();
    public IntakeSubsystem() { }

    public MotorEx intakeMotor = new MotorEx("intake");

    public static Boolean IS_LOCKED = Boolean.FALSE;

    public Command activateIntake = new SetPower(intakeMotor, 1);
    public Command stopIntake = new SetPower(intakeMotor, 0);
    public Command outtake = new SetPower(intakeMotor, -1);

}
