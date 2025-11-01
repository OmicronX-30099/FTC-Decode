package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

object FlywheelSubsystem : Subsystem {
    val fwl: MotorEx = MotorEx("fwl")
    val fwr: MotorEx = MotorEx("fwr")
    val fwm: MotorGroup = MotorGroup(fwr, fwl)

    val flywheelControl = ControlSystem.builder()
        .velPid(0.0,0.0,0.0)
        .basicFF(0.0,0.0,0.0)
        .build()

    fun setGoal(vel: Double) {
        flywheelControl.goal = KineticState(0.0,vel,0.0)
    }

    override fun periodic() {
        fwm.power = flywheelControl.calculate(fwm.state)
    }
}