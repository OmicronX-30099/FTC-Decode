package org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.powerable.SetPower

object IntakeSubsystem: Subsystem {
    val intakeMotor: MotorEx = MotorEx("intake")

    fun intake(power: Double): Command {
        return SetPower(intakeMotor, power).requires(this)
    }
}