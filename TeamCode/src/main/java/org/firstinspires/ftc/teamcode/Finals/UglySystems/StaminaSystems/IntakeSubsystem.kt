package org.firstinspires.ftc.teamcode.Finals.UglySystems.StaminaSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.powerable.SetPower

object IntakeSubsystem: Subsystem {
    val intakeMotor: MotorEx = MotorEx("intake")

    fun intake(intakePower: Double) { intakeMotor.power = intakePower } 

    override fun initialize() {
    }
}
