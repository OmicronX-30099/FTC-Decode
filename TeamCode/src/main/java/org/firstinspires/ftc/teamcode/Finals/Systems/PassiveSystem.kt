package org.firstinspires.ftc.teamcode.Finals.Systems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.ParallelGroup
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
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
            InstantCommand{ IntakeSubsystem.intake(0.4) },
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
    val positionBall: Command
        get() = SequentialGroup(
            this.maxIntakeCommand,
            Delay(0.25),
            this.stopIntakeCommand
        )
    val tripleShootSequence: Command
        get() = SequentialGroup(
            positionBall,
            pushBallCommand,
            positionBall,
            pushBallCommand,
            positionBall,
            pushBallCommand
        )
    val altTripleShootSequence: Command
        get() = SequentialGroup(
            this.openGateCommand,
            maxIntakeCommand,
            Delay(0.5),
            stopIntakeCommand,
            pushBallCommand,
            maxIntakeCommand,
            Delay(0.15),
            pushBallCommand
        )

    fun intake(intakePower: Double) {
        IntakeSubsystem.intake(intakePower)
    }
}
