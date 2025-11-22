package org.firstinspires.ftc.teamcode.Draft2.System.ShooterSystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

// Subsystem to manage Flywheel
object FlywheelSubsystem: Subsystem {
    // Declaration of hardware
    val leftFlywheelMotor: MotorEx = MotorEx("leftFlywheelMotor")
    val rightFlywheelMotor: MotorEx = MotorEx("rightFlywheelMotor")
    val flywheelMotors: MotorGroup = MotorGroup(rightFlywheelMotor, leftFlywheelMotor)
    // Flywheel default power when autoAim is off
    val standByPower: Double = 0.25

    // Control system to manage flywheel with velocity PID
    val flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.000325,0.0,0.063)
        .build()

    // Declaration of variables to track autoAim and current Velocity
    var flywheelAutoAim: Boolean = false
    var currentFlywheelVelocity: Double = 0.0

    // Periodic function for turretSubsystem
    override fun periodic() {
        // Sets calculated power to turretMotor, turret is continuously powered
        if (flywheelAutoAim) {
            flywheelMotors.power = flywheelControl.calculate(flywheelMotors.state)
        } else {
            flywheelMotors.power = standByPower
        }
    }
}