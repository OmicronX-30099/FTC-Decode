package org.firstinspires.ftc.teamcode.Subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;


public class IntakeSystem implements Subsystem {
    public static IntakeSystem INSTANCE = new IntakeSystem();
    private IntakeSystem() {}
    public MotorEx IntakeMotor = new MotorEx("Intake");

    public Command activateIntake = new SetPower(IntakeMotor, 0.5);
    public Command deactivateIntake = new SetPower(IntakeMotor, 0);
    @Override
    public void Intialize() {IntakeMotor.SetPower();}

}
