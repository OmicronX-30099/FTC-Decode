package org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.IntakeSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.powerable.SetPower

object IntakeSubsystem: Subsystem {
    val intakeMotor: MotorEx = MotorEx("intake", 0.0)

    fun intakeCommand(intakePower: Double): Command {
        return SetPower(intakeMotor, intakePower)
    }

    override fun initialize() {
        ActiveOpMode.telemetry.addData("System", "IntakeSubsystem initialized")
    }
}