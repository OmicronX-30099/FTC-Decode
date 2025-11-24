package org.firstinspires.ftc.teamcode.Draft1.System.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Util.TurretState
import kotlin.properties.Delegates

object TurretSubsystemLLT: Subsystem {
    // Definition of hardware
    val turretMotor: MotorEx = MotorEx("turret")
    // Definition of control system with velocity pid
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.025,0.0,0.0)
        .build()

    // Variables to track Turret Status
    var currentTurretPosition: Double = 0.0
    var turretAutoAim: Boolean = true

    // Function to toggle auto aim on and off for turret
    fun turretAutoAimToggle() {
        // Toggles auto aiming
        turretAutoAim = !turretAutoAim
    }
    // Function to set position of turret
    fun setTurretPosition(turretPos: Double) {
        // Sets turret position to calculated position and writes to system state
        if (turretAutoAim) {
            turretControl.goal = KineticState(turretPos)
            currentTurretPosition = turretPos
            ActiveOpMode.telemetry.addData("Setting turret to:",currentTurretPosition)
        }
    }
    // Function to return current state of turret with position and auto aim
    fun getTurretState(): TurretState {
        return TurretState(currentTurretPosition, turretAutoAim)
    }

    // Function to set power as calculated by control system
    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}