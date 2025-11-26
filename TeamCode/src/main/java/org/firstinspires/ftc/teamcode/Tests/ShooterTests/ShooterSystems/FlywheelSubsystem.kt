package org.firstinspires.ftc.teamcode.Tests.ShooterTests.ShooterSystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

object FlywheelSubsystem: Subsystem {
    val leftFlywheelMotor: MotorEx = MotorEx("leftFlywheelMotor")
    val rightFlywheelMotor: MotorEx = MotorEx("rightFlywheelMotor")
    val flywheelMotors: MotorGroup = MotorGroup(rightFlywheelMotor, leftFlywheelMotor)
    val flywheelControl: ControlSystem = ControlSystem.builder()
        .posPid(0.01,0.0,0.005)
        .build()

    var flywheelAutoAim: Boolean = false
    var currentFlywheelVelocity: Double = 0.0

    fun setTurretPosition(flywheelGoal: Double) {
        flywheelControl.goal = KineticState(0.0,flywheelGoal)
        currentFlywheelVelocity = flywheelGoal
        ActiveOpMode.telemetry.addData("FlywheelSubsystem", "Set flywheel goal to: " + flywheelGoal.toString())
    }

    override fun initialize() {
        ActiveOpMode.telemetry.addData("System", "FlywheelSubsystem initialized")
    }
    override fun periodic() {
        if (flywheelAutoAim) {
            flywheelMotors.power = flywheelControl.calculate(flywheelMotors.state)
        } else {
            ActiveOpMode.telemetry.addData("FlywheelSubsystem", "flywheelAutoAim is currently off, setting power to default 0.25")
            flywheelMotors.power = 0.25
        }
    }
}