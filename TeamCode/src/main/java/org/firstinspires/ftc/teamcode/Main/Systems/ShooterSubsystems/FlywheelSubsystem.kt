package org.firstinspires.ftc.teamcode.Main.Systems.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

object FlywheelSubsystem: Subsystem {
    val flywheelLeft: MotorEx = MotorEx("fwl")
    val flywheelRight: MotorEx = MotorEx("fwr")
    val flywheelMotors: MotorGroup = MotorGroup(flywheelRight, flywheelLeft)
    val flywheelControl = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.000325,0.0,0.063)
        .build()

    var currentFlywheelVelocity: Double = 0.0;
    var flywheelAutoAim: Boolean = false;

    fun flywheelAutoAimToggle() {
        flywheelAutoAim = !flywheelAutoAim
    }

    fun setFlywheelVelocity(targetVel: Double) {
        if (targetVel != currentFlywheelVelocity) {
            currentFlywheelVelocity = targetVel
            flywheelControl.goal = KineticState(targetVel, 0.0,0.0)
        }
    }

    override fun periodic() {
        if (flywheelAutoAim) {
            flywheelMotors.power = flywheelControl.calculate(flywheelMotors.state)
        } else {
            flywheelMotors.power = -0.25
        }
    }
}