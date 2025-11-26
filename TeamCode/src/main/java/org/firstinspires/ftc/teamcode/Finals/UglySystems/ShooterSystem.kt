package org.firstinspires.ftc.teamcode.Finals.UglySystems

import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Finals.UglySystems.ShooterSystems.*

object ShooterSystem: SubsystemGroup(FlywheelSubsystem, HoodSubsystem, TurretSubsystem) {
    val flywheelEquationA: Double = 0.0;
    val flywheelEquationB: Double = 0.0;
    val flywheelEquationC: Double = 0.0;
    val flywheelEquationD: Double = 0.0;
    val flywheelEquationE: Double = 0.0;
    val flywheelEquationF: Double = 0.0;

    var fullAutoAim: Boolean = false

    fun autoAimOn() {
        fullAutoAim = true
        FlywheelSubsystem.flywheelAutoAim = true
    }
    fun autoAimOff() {
        fullAutoAim = false
        FlywheelSubsystem.flywheelAutoAim = false
    }

    fun calibrateFlywheelVelocity(distanceFromGoal: Double) {
        var hoodPosition: Double = HoodSubsystem.hoodServo.position


    }

    override fun periodic() {
        if (fullAutoAim) {

        }
    }
}