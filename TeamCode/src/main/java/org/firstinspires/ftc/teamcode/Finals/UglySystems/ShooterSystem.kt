package org.firstinspires.ftc.teamcode.Finals.UglySystems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import org.firstinspires.ftc.teamcode.Finals.UglySystems.ShooterSystems.*
import kotlin.math.PI
import kotlin.math.atan2

object ShooterSystem: SubsystemGroup(FlywheelSubsystem, HoodSubsystem, TurretSubsystem) {
    val flywheelEquationA: Double = 0.0;
    val flywheelEquationB: Double = 0.0;
    val flywheelEquationC: Double = 0.0;
    val flywheelEquationD: Double = 0.0;
    val flywheelEquationE: Double = 0.0;
    val flywheelEquationF: Double = 0.0;
    val goalPose = Pose(0.0,141.0)

    var fullAutoAim: Boolean = false

    fun autoAimOn() {
        fullAutoAim = true
        FlywheelSubsystem.flywheelAutoAim = true
    }
    fun autoAimOff() {
        fullAutoAim = false
        FlywheelSubsystem.flywheelAutoAim = false
    }

    fun calibrateFlywheel(currPose: Pose) {
        var distanceFromGoal: Double = currPose.distanceFrom(goalPose)
        var hoodPosition: Double = HoodSubsystem.hoodServo.position
        var quadCoeffA: Double = flywheelEquationC
        var quadCoeffB: Double = (flywheelEquationB * hoodPosition) + flywheelEquationE
        var quadCoeffC: Double = (flywheelEquationA * hoodPosition * hoodPosition) + (flywheelEquationD * hoodPosition) + (flywheelEquationF - distanceFromGoal)
        var discriminant: Double = (quadCoeffB * quadCoeffB) - (4 * quadCoeffA * quadCoeffC)
        var velocity = (-1 * quadCoeffB - Math.sqrt(discriminant)) / (2 * quadCoeffA)
        FlywheelSubsystem.flywheeControl.goal = KineticState(0.0,velocity)
    }
    fun calibrateTurret(currPose: Pose) {
        var angle = atan2(goalPose.x-currPose.x,goalPose.y-currPose.y)
        var ticks = (((currPose.heading-(PI/2))+angle) / (2*PI)) * (100/24) * 384.5 * -1
        TurretSubsystem.turretControl.goal = KineticState(Math.round(ticks).toDouble())
    }
    fun calibrateHood(currPose: Pose) {
        var distanceFromGoal: Double = currPose.distanceFrom(goalPose)
        when (distanceFromGoal) {
            in 0.0..<  0.5 -> HoodSubsystem.hoodServo.position = 0.0
            in 0.5..<  1.0 -> HoodSubsystem.hoodServo.position = 0.65
            else              -> HoodSubsystem.hoodServo.position = 1.0
        }
    }

    override fun periodic() {
        if (fullAutoAim) {
            calibrateHood(follower.pose)
            calibrateTurret(follower.pose)
            calibrateFlywheel(follower.pose)
        }
    }
}