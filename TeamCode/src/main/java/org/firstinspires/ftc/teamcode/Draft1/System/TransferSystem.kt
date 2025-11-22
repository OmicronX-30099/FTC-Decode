package org.firstinspires.ftc.teamcode.Draft1.System

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Draft1.System.TransferSubsystem.KickerSubsystem
import org.firstinspires.ftc.teamcode.Draft1.System.TransferSubsystem.ShooterGateSubsystem

// Subsystem group to store compound transfer commands
object TransferSystem: SubsystemGroup(KickerSubsystem, ShooterGateSubsystem) {
    // Compound command to kick the ball
    val kickBallCommand: Command
        get() = SequentialGroup(
            KickerSubsystem.engageKickerCommand,
            Delay(0.15),
            KickerSubsystem.disengageKickerCommand
        )
}