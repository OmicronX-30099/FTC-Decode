package org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

object IntakeSubsystem: Subsystem {
    val intakeMotor: MotorEx = MotorEx("intake")

    fun intake(intakePower: Double) { intakeMotor.power = intakePower } 

    override fun initialize() {
    }
}
