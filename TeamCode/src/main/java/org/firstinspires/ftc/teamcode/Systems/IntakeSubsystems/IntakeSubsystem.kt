package org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

object IntakeSubsystem : Subsystem {
    val intakeMotor: MotorEx = MotorEx("intake")

    fun setIntakePower(power: Double) {
        intakeMotor.power = power
    }
}