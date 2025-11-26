package org.firstinspires.ftc.teamcode.Tests.ShooterTests.ShooterSystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.MotorEx

object TurretSubsystem: Subsystem {
    val turretMotor: MotorEx = MotorEx("turret")
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.01,0.0,0.005)
        .build()

    var turretAutoAim: Boolean = false
    var currentTurretPosition: Double = 0.0

    fun setTurretPosition(turretGoal: Double) {
        turretControl.goal = KineticState(turretGoal)
        currentTurretPosition = turretGoal
        ActiveOpMode.telemetry.addData("TurretSubsystem", "Set turret goal to: " + turretGoal.toString())
    }

    override fun initialize() {
        ActiveOpMode.telemetry.addData("System", "TurretSubsystem initialized")
    }
    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}