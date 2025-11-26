package org.firstinspires.ftc.teamcode.Finals.Systems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.ParallelGroup
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.IntakeSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.ShooterGateSubsystem

object PassiveSystem:
    SubsystemGroup(IntakeGateSubsystem, IntakeSubsystem, KickerSubsystem, ShooterGateSubsystem)
{
    val openGateCommand: Command = InstantCommand { IntakeGateSubsystem.openGate() }
    val closeGateCommand: Command = InstantCommand { IntakeGateSubsystem.closeGate() }
    val kickBallCommand: Command = InstantCommand { KickerSubsystem.kickBall() }
    val resetKickerCommand: Command = InstantCommand { KickerSubsystem.resetKicker() }
    val blockShooterCommand: Command = InstantCommand { ShooterGateSubsystem.blockGate() }
    val releaseShooterCommand: Command = InstantCommand { ShooterGateSubsystem.releaseGate() }
    val maxIntakeCommand: Command
        get() = SequentialGroup(
            InstantCommand{ IntakeSubsystem.intake(1.0) },
            InstantCommand { IntakeGateSubsystem.openGate() }
        )
    val mediumIntakeCommand: Command
        get() = SequentialGroup(
            InstantCommand{ IntakeSubsystem.intake(0.5) },
            InstantCommand { IntakeGateSubsystem.openGate() }
        )
    val stopIntakeCommand: Command
        get() = ParallelGroup(
            InstantCommand{ IntakeGateSubsystem.closeGate() },
            InstantCommand{ IntakeSubsystem.intake(0.0) }
        )
    val pushBallCommand: Command
        get() = SequentialGroup(
            InstantCommand { ShooterGateSubsystem.releaseGate() },
            InstantCommand { KickerSubsystem.kickBall() },
            Delay(0.175),
            InstantCommand { KickerSubsystem.resetKicker() },
            InstantCommand { ShooterGateSubsystem.blockGate() },
            Delay(0.2)
        )
    val tripleShootSequence: Command
        get() = SequentialGroup(
            InstantCommand { this.intake(1.0) },
            InstantCommand { ShooterGateSubsystem.releaseGate() },
            Delay(0.5),
            pushBallCommand
        )

    fun intake(intakePower: Double) {
        IntakeSubsystem.intake(intakePower)
    }
}
