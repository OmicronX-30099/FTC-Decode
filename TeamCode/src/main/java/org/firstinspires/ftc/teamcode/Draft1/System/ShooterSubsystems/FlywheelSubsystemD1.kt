package org.firstinspires.ftc.teamcode.Draft1.System.ShooterSubsystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Util.FlywheelState
import kotlin.properties.Delegates

// Subsystem to control flywheel motors
object FlywheelSubsystem: Subsystem {
    // Definition of hardware
    val leftFlywheelMotor: MotorEx = MotorEx("fwl")
    val rightFlywheelMotor: MotorEx = MotorEx("fwr")
    val flywheelMotorGroup: MotorGroup = MotorGroup(rightFlywheelMotor,leftFlywheelMotor)

    // Control system to manage flywheel with velocity pid and feedforward
    val flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.000325,0.0,0.063)
        .build()

    // Variables to track flywheel status
    var currentFlywheelVelocity: Double by Delegates.notNull()
    var flywheelAutoAim: Boolean = false

    // Function to toggle flywheel auto aiming on and off
    fun flywheelAutoAimToggle() {
        // Toggles flywheel on and off
        flywheelAutoAim = !flywheelAutoAim
    }
    // Function to set flywheel control system goal using given velocity
    fun setFlywheelVelocity(velocity: Double) {
        // Enforces auto aiming here to save calculational memory
        if (flywheelAutoAim) {
            flywheelControl.goal = KineticState(0.0,velocity)
            currentFlywheelVelocity = velocity
        }
    }
    // Function to get current state of Flywheel with auto aiming status and current velocity
    fun getFlywheelState(): FlywheelState {
        return FlywheelState(currentFlywheelVelocity, flywheelAutoAim)
    }

    // Function to set calculated flywheel power to motors
    override fun periodic() {
        // Enforces auto aim for second time to avoid spoiling gears with instant stop
        if (flywheelAutoAim) {
            flywheelMotorGroup.power = flywheelControl.calculate(flywheelMotorGroup.state)
        } else {
            // Sets flywheel power to 0.25 for faster acceleration later on when auto aim is off
            flywheelMotorGroup.power = 0.25
        }
    }
}