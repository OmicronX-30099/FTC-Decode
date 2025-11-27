package org.firstinspires.ftc.teamcode.Finals.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.TurretSubsystem
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.round
import kotlin.math.sqrt

object ShooterSystem: SubsystemGroup(FlywheelSubsystem, HoodSubsystem, TurretSubsystem) {
    val autoAimOnCommand: Command = InstantCommand { this.autoAimOn() }
    val autoAimOffCommand: Command = InstantCommand { this.autoAimOff() }

    const val flywheelEquationA: Double = -24.993970450383305
    const val flywheelEquationB: Double = -0.07154455960209152
    const val flywheelEquationC: Double = 0.00019278705563574583
    const val flywheelEquationD: Double = 116.26277827556967
    const val flywheelEquationE: Double = -0.272675259634784
    const val flywheelEquationF: Double = 122.99485320234648
    lateinit var goalPose: Pose

    var fullAutoAim: Boolean = false

    fun calibrateFlywheel(currPose: Pose) {
        val distanceFromGoal: Double = currPose.distanceFrom(goalPose)
        val hoodPosition: Double = HoodSubsystem.hoodServo.position
        val quadCoeffA: Double = flywheelEquationC
        val quadCoeffB: Double = (flywheelEquationB * hoodPosition) + flywheelEquationE
        val quadCoeffC: Double = (flywheelEquationA * hoodPosition * hoodPosition) + (flywheelEquationD * hoodPosition) + (flywheelEquationF - distanceFromGoal)
        val discriminant: Double = (quadCoeffB * quadCoeffB) - (4 * quadCoeffA * quadCoeffC)
        val velocity = (-1 * quadCoeffB + sqrt(discriminant)) / (2 * quadCoeffA)
        FlywheelSubsystem.flywheeControl.goal = KineticState(0.0,velocity)
    }
    fun calibrateTurret(currPose: Pose) {
        val angle = atan2(goalPose.x-currPose.x,goalPose.y-currPose.y)
        var ticks = (((currPose.heading-(PI/2))+angle) / (2*PI)) * (100.0/24.0) * 384.5 * -1
        TurretSubsystem.turretControl.goal = KineticState(round(ticks))
        ActiveOpMode.telemetry.addData("Turret", "rawAngle = $angle")
        ActiveOpMode.telemetry.addData("Turret", "ticks = $ticks")
    }
    fun calibrateHood(currPose: Pose) {
        val distanceFromGoal: Double = currPose.distanceFrom(goalPose)
        when (distanceFromGoal) {
            in 0.0..< 63.0 -> HoodSubsystem.hoodServo.position = 0.0
            in 63.0..<  105.0 -> HoodSubsystem.hoodServo.position = 0.65
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
        ActiveOpMode.telemetry.addData("auto", this.fullAutoAim)
    }
}