package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

object FlywheelSubsystem: Subsystem {
    val flywheelLeftMotor: MotorEx = MotorEx("fwl")
    val flywheelRightMotor: MotorEx = MotorEx("fwr")

    val flywheelMotors: MotorGroup = MotorGroup(flywheelRightMotor, flywheelLeftMotor)

    val flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.00033,0.0,0.07)
        .build()

    fun setFlywheelVelocity(vel: Double) {
        flywheelControl.goal = KineticState(0.0, vel)
    }

    override fun periodic() {
        if (ShooterSystem.AUTO_AIM) {
            flywheelMotors.power = flywheelControl.calculate(flywheelMotors.state)
        } else {
            flywheelMotors.power = 0.0
        }
    }
}