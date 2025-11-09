package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

object FlywheelSubsystem : Subsystem {
    val flywheelMotorLeft: MotorEx = MotorEx("fwl")
    val flywheelMotorRight: MotorEx = MotorEx("fwr")

    val flywheelMotors: MotorGroup = MotorGroup(flywheelMotorRight, flywheelMotorLeft)

    val flywheelControl = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.00033,0.0,0.07)
        .build()

    fun setTargetVelocity(vel: Double) {
        flywheelControl.goal = KineticState(0.0,vel)
    }

    override fun periodic() {
        flywheelMotors.power = flywheelControl.calculate(flywheelMotors.state)
    }
}