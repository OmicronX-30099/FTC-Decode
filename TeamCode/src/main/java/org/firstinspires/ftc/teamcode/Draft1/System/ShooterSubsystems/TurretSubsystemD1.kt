package org.firstinspires.ftc.teamcode.Draft1.System.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Util.TurretState
import kotlin.properties.Delegates

// Subsystem to manage turretMotor and turret auto-aiming
object TurretSubsystemD1: Subsystem {
    // Definition of hardware
    val turretMotor: MotorEx = MotorEx("turret")

    // Definition of control system with positional pid
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.025,0.0,0.0)
        .build()

    // Variables to track Turret Status
    var currentTurretPosition: Double by Delegates.notNull()
    var turretAutoAim: Boolean = false

    // Function to toggle auto aim on and off for turret
    fun turretAutoAimToggle() {
        // Toggles auto aiming
        turretAutoAim = !turretAutoAim
    }
    // Function to set position of turret
    fun setTurretPosition(turretPos: Double) {
        // Sets turret position to calculated position and writes to system state
        // Enforces auto aim lock here
        if (turretAutoAim) {
            turretControl.goal = KineticState(turretPos)
            currentTurretPosition = turretPos
        }
    }
    // Function to return current state of turret with position and auto aim
    fun getTurretState(): TurretState {
        return TurretState(currentTurretPosition, turretAutoAim)
    }

    // Function to reset motor encoder on initialization
    override fun initialize() {
        turretMotor.zero()
    }
    // Function to set power as calculated by control system
    override fun periodic() {
        // Turret is still powered when auto aim is off, it will hold current position.
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}