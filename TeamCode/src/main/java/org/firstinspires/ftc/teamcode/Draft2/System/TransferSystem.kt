package org.firstinspires.ftc.teamcode.Draft2.System

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Draft2.System.TransferSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Draft2.System.TransferSubsystems.ShooterGateSubsystem

// Subsystem group to store compound transfer commands
object TransferSystem: SubsystemGroup(KickerSubsystem, ShooterGateSubsystem) {
    // Compound command to kick the ball
    val pushBallCommand: Command
        get() = SequentialGroup(
            ShooterGateSubsystem.openShooterGateCommand,
            KickerSubsystem.engageKickerCommand,
            Delay(0.15),
            ShooterGateSubsystem.closeShooterGateCommand,
            KickerSubsystem.disengageKickerCommand
        )
    val tripleBallCommand: Command
        get() = SequentialGroup(
            pushBallCommand,
            Delay(0.25),
            pushBallCommand,
            Delay(0.25),
            pushBallCommand
        )
}