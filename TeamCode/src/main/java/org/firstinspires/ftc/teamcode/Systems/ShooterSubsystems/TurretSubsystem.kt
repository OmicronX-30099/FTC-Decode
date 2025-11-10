package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

object TurretSubsystem : Subsystem {
    val turretMotor: MotorEx = MotorEx("tur")

    val turretControl = dev.nextftc.control.ControlSystem.builder()
        .posPid(0.03,0.0,0.0)
        .build()

    fun setTurretPosition(pos: Double) {
        turretControl.goal = KineticState(pos)
    }

    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}