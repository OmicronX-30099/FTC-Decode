package org.firstinspires.ftc.teamcode.Draft2.System.IntakeSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.powerable.SetPower

// Subsystem singleton for intake motor subsystem
object IntakeSubsystem: Subsystem {
    // Declaration of intakeMotor
    val intakeMotor: MotorEx = MotorEx("intake")

    // Commands with default positions for AUTONOMOUS USE ONLY
    val fullIntakeCommand: Command = SetPower(intakeMotor, 1.0).requires(this)
    val slowIntakeCommand: Command = SetPower(intakeMotor, 0.3).requires(this)
    val stopIntakeCommand: Command = SetPower(intakeMotor, 0.0).requires(this)

    // Function to return variable setPower command, based on power input
    fun setIntakePower(intakePower: Double): Command {
        // Returns setPower command using power argument
        return SetPower(intakeMotor, intakePower).requires(this)
    }
}