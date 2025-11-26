package org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems

import dev.nextftc.control.ControlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

object FlywheelSubsystem: Subsystem {
    val leftFlywheelMotor: MotorEx = MotorEx("leftFlywheelMotor")
    val rightFlywheelMotor: MotorEx = MotorEx("rightFlywheelMotor")
    val flywheelMotors: MotorGroup = MotorGroup(rightFlywheelMotor, leftFlywheelMotor)

    @JvmField var flywheelPID = PIDCoefficients(0.01, 0.0, 0.0)
    @JvmField var flywheelFF = BasicFeedforwardParameters(0.00031,0.0,0.063)
    val flywheeControl: ControlSystem = ControlSystem.builder()
        .velPid(flywheelPID)
        .basicFF(flywheelFF)
        .build()

    var flywheelAutoAim: Boolean = false

    override fun periodic() {
        if (flywheelAutoAim) { flywheelMotors.power = flywheeControl.calculate(flywheelMotors.state) }
        else { flywheelMotors.power = 0.25 }
    }
}