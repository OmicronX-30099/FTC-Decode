package org.firstinspires.ftc.teamcode.Finals.Systems

import com.qualcomm.robotcore.hardware.Gamepad
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.conditionals.IfElseCommand
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.ParallelGroup
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.ftc.Gamepads
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.IntakeSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems.ShooterGateSubsystem

object PassiveSystem:
    // System for Intake, Kicker, and Gate
    SubsystemGroup(IntakeGateSubsystem, IntakeSubsystem, KickerSubsystem, ShooterGateSubsystem)
{
    val autoAimRumble = Gamepad.RumbleEffect.Builder()
        .addStep(0.65,0.65,250)
        .addStep(1.0,1.0, 500)
        .build()

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
            Delay(0.1875)
        )
    val positionBall: Command
        get() = SequentialGroup(
            this.maxIntakeCommand,
            Delay(0.15),
            this.stopIntakeCommand
        )
    val tripleShootSequence: Command
        get() = SequentialGroup(
            positionBall,
            pushBallCommand,
            positionBall,
            pushBallCommand,
            maxIntakeCommand,
            Delay(0.15),
            pushBallCommand,
            this.stopIntakeCommand
        )
    val altTripleShootSequence: Command
        get() = SequentialGroup(
            this.releaseShooterCommand,
            maxIntakeCommand,
            Delay(0.3),
            pushBallCommand,
            Delay(0.3),
            pushBallCommand,
            this.stopIntakeCommand
        )

    fun checkedTripleShootSequence(): Command {
        ActiveOpMode.telemetry.addData("autoAim", ShooterSystem.fullAutoAim)
        if (ShooterSystem.fullAutoAim) {
            ActiveOpMode.telemetry.addData("autoAim", "successful")
            return this.tripleShootSequence
        } else {
            return InstantCommand { ActiveOpMode.gamepad1.runRumbleEffect(autoAimRumble) }
        }
    }

    fun intake(intakePower: Double) {
        IntakeSubsystem.intake(intakePower)
    }
}
