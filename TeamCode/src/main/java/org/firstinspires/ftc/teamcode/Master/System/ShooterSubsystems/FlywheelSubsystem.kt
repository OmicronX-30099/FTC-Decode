package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Util.FlywheelState
import kotlin.properties.Delegates

object FlywheelSubsystem: Subsystem {
    val leftFlywheelMotor: MotorEx = MotorEx("fwl")
    val rightFlywheelMotor: MotorEx = MotorEx("fwr")
    val flywheelMotorGroup: MotorGroup = MotorGroup(rightFlywheelMotor,leftFlywheelMotor)

    val flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.000325,0.0,0.063)
        .build()

    var currentFlywheelVelocity: Double by Delegates.notNull()
    var flywheelAutoAim: Boolean = false

    fun flywheelAutoAimToggle() {
        flywheelAutoAim = !flywheelAutoAim
    }
    fun setFlywheelVelocity(velocity: Double) {
        if (flywheelAutoAim) {
            flywheelControl.goal = KineticState(0.0,velocity)
            currentFlywheelVelocity = velocity
        }
    }
    fun getFlywheelState(): FlywheelState {
        return FlywheelState(currentFlywheelVelocity, flywheelAutoAim)
    }

    override fun periodic() {
        if (flywheelAutoAim) {
            flywheelMotorGroup.power = flywheelControl.calculate(flywheelMotorGroup.state)
        } else {
            flywheelMotorGroup.power = 0.25
        }
    }
}