package org.firstinspires.ftc.teamcode.Draft2.System.ShooterSystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

// Subsystem to manage turret
object TurretSubsystem: Subsystem {
    // Declaration of hardware
    val turretMotor: MotorEx = MotorEx("turretMotor")

    // Control system to manage turret with positional PID
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.025,0.0,0.0)
        .build()

    // Declaration of variables to track autoAim and current Position
    var turretAutoAim: Boolean = false
    var currentTurretPosition: Double = 0.0

    fun setTurretPosition(turretGoal: Double) {
        if (turretAutoAim) {
            turretControl.goal = KineticState(turretGoal)
            currentTurretPosition = turretGoal
        }
    }

    // Initialization of turret
    override fun initialize() {
        // Resets encoder to 0
        turretMotor.zero()
    }
    // Periodic function for turretSubsystem
    override fun periodic() {
        // Sets calculated power to turretMotor, turret is continuously powered
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}