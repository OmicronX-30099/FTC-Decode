package org.firstinspires.ftc.teamcode.Finals.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.TurretSubsystem
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.PI
import kotlin.math.atan2

object ShooterSystem: SubsystemGroup(FlywheelSubsystem, HoodSubsystem, TurretSubsystem) {
    val autoAimOnCommand: Command = InstantCommand { this.autoAimOn() }
    val autoAimOffCommand: Command = InstantCommand { this.autoAimOff() }

    val flywheelEquationA: Double = -24.993970450383305
    val flywheelEquationB: Double = -0.07154455960209152
    val flywheelEquationC: Double = 0.00019278705563574583
    val flywheelEquationD: Double = 116.26277827556967
    val flywheelEquationE: Double = -0.272675259634784
    val flywheelEquationF: Double = 122.99485320234648
    lateinit var goalPose: Pose

    var fullAutoAim: Boolean = false

    fun calibrateFlywheel(currPose: Pose) {
        var distanceFromGoal: Double = currPose.distanceFrom(goalPose)
        var hoodPosition: Double = HoodSubsystem.hoodServo.position
        var quadCoeffA: Double = flywheelEquationC
        var quadCoeffB: Double = (flywheelEquationB * hoodPosition) + flywheelEquationE
        var quadCoeffC: Double = (flywheelEquationA * hoodPosition * hoodPosition) + (flywheelEquationD * hoodPosition) + (flywheelEquationF - distanceFromGoal)
        var discriminant: Double = (quadCoeffB * quadCoeffB) - (4 * quadCoeffA * quadCoeffC)
        var velocity = (-1 * quadCoeffB + Math.sqrt(discriminant)) / (2 * quadCoeffA)
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

    fun autoAimOn() {
        fullAutoAim = true
        FlywheelSubsystem.flywheelAutoAim = true
    }
    fun autoAimOff() {
        fullAutoAim = false
        FlywheelSubsystem.flywheelAutoAim = false
    }

    fun setAlliance(alliance: Alliance) {
        this.goalPose = alliance.goalPose
    }

    override fun periodic() {
        if (fullAutoAim) {
            calibrateHood(follower.pose)
            calibrateTurret(follower.pose)
            calibrateFlywheel(follower.pose)
        }
    }
}