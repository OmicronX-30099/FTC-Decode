package org.firstinspires.ftc.teamcode.Draft2.System

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Draft2.System.IntakeSubsystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Draft2.System.IntakeSubsystems.IntakeSubsystem

// Subsystem group to store compound intaking commands
object IntakeSystem: SubsystemGroup(IntakeGateSubsystem, IntakeSubsystem) {
    // Compound Commands to open gate and intake at full power for AUTONOMOUS USE ONLY
    val fullIntakeCommand: Command
        get() = SequentialGroup(
            IntakeGateSubsystem.openIntakeGateCommand,
            IntakeSubsystem.fullIntakeCommand
        )
    // Compound Commands to open gate and intake at a slow power for AUTONOMOUS USE ONLY
    val slowIntakeCommand: Command
        get() = SequentialGroup(
            IntakeGateSubsystem.openIntakeGateCommand,
            IntakeSubsystem.slowIntakeCommand
        )
    // Compound Commands to close gate and stop intake
    val stopIntakeCommand: Command
        get() = SequentialGroup(
            IntakeGateSubsystem.closeIntakeGateCommand,
            IntakeSubsystem.stopIntakeCommand
        )
    val openGateCommand: Command
        get() = IntakeGateSubsystem.openIntakeGateCommand
    val  closeGateCommand: Command
        get() = IntakeGateSubsystem.closeIntakeGateCommand
    // Function to return a compound command including gate opening and variable intaking power
    fun intakeCommand(power: Double): Command {
        return SequentialGroup(
            IntakeSubsystem.setIntakePower(power)
        )
    }
}