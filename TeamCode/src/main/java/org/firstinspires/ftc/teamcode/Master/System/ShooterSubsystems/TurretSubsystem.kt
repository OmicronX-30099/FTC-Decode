package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Util.TurretState
import kotlin.properties.Delegates

object TurretSubsystem: Subsystem {
    val turretMotor: MotorEx = MotorEx("turret")

    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.025,0.0,0.0)
        .build()

    var currentTurretPosition: Double by Delegates.notNull()
    var turretAutoAim: Boolean = false

    fun turretAutoAimToggle() {
        turretAutoAim = !turretAutoAim
    }
    fun setTurretPosition(turretPos: Double) {
        if (turretAutoAim) {
            turretControl.goal = KineticState(turretPos)
            currentTurretPosition = turretPos
        }
    }
    fun getCurrentState(): TurretState {
        return TurretState(currentTurretPosition, turretAutoAim)
    }

    override fun initialize() {
        turretMotor.zero()
    }
    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}