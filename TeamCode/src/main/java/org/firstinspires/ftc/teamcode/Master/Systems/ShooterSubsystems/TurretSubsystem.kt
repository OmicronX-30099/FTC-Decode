package org.firstinspires.ftc.teamcode.Master.Systems.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import kotlin.properties.Delegates

object TurretSubsystem: Subsystem {
    val turretMotor: MotorEx  = MotorEx("tur")
    var turretAutoAim: Boolean = false
    var currentTurretPos: Double by Delegates.notNull()

    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.0,0.0,0.0)
        .build()

    fun turretAutoAimToggle() {
        turretAutoAim = !turretAutoAim
    }

    fun setPosition(ticks: Double) {
        if ((turretAutoAim) && (ticks != currentTurretPos)) {
            currentTurretPos = ticks
            turretControl.goal = KineticState(ticks, 0.0,0.0)
        }
    }

    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}