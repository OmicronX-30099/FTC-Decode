package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.TurretSubsystem
import kotlin.math.*

object ShooterSystem: SubsystemGroup
    (FlywheelSubsystem, HoodSubsystem, TurretSubsystem, KickerSubsystem) {

    var GOAL_POSE: Pose = Pose(144.0,144.0)
    var AUTO_AIM: Boolean = false;
    var TARGET_TURRET_HEADING: Double = 0.0;
    var TARGET_FLYWHEEL_VELOCITY: Double = 0.0;
    var CURRENT_HOOD_POSITION: Double = 0.0;
    var eq: Int = 1;

    val kickBall: Command
        get() = SequentialGroup(
            KickerSubsystem.engageKicker,
            Delay(0.15),
            KickerSubsystem.disengageKicker
        )


    fun autoAim() {
        AUTO_AIM = !AUTO_AIM
    }
    fun calibrateHoodPosition(currPose: Pose) {
        var distance = currPose.distanceFrom(GOAL_POSE)
        when (distance) {
            in 0.0..84.0 ->   { HoodSubsystem.lowMode.schedule()
                                    eq = 1
                                    CURRENT_HOOD_POSITION = 0.0}
            in 84.0..120.0 -> { HoodSubsystem.mediumMode.schedule()
                                    eq = 2
                                    CURRENT_HOOD_POSITION = 0.4}
            else ->                 { HoodSubsystem.highMode.schedule()
                                    eq = 3
                                    CURRENT_HOOD_POSITION = 1.0}
        }
    }
    fun calibrateFlywheelVelocity(currPose: Pose) {
        var distance = currPose.distanceFrom(GOAL_POSE)
        var vel: Double = 0.0;
        when (eq) {
            1 -> {
                vel = 5.35256 * distance + 820.5
            }

            2 -> {
                vel = 5.96847 * distance + 665.75
            }

            3 -> {
                vel = 5.66751 * distance + 675.8
            }
        }
        TARGET_FLYWHEEL_VELOCITY = vel
        FlywheelSubsystem.setFlywheelVelocity(vel)
    }

    fun calibrateTurretPosition(currPose: Pose) {
        var angle = atan((GOAL_POSE.y+currPose.y)/(GOAL_POSE.x-currPose.x))
        var ticks = ((-currPose.heading-angle) / (2 * PI)) * (100 / 24) * 384.5+192.5+225;
        if (abs(round(ticks) - TARGET_TURRET_HEADING) >= 0) {
            TurretSubsystem.setTurretPosition(ticks)
            TARGET_TURRET_HEADING = ticks
        }
    }

    fun FullTurretAim(currPose: Pose) {
        if (AUTO_AIM) {
            calibrateTurretPosition(currPose)
            calibrateHoodPosition(currPose)
            calibrateFlywheelVelocity(currPose)
        }
    }

    override fun periodic() {
        ActiveOpMode.telemetry.addData("Target Turret Heading", TARGET_TURRET_HEADING)
        ActiveOpMode.telemetry.addData("Target Flywheel Vel", TARGET_FLYWHEEL_VELOCITY)
        ActiveOpMode.telemetry.addData("Current Hood Position", CURRENT_HOOD_POSITION)
        ActiveOpMode.telemetry.addData("AutoAim Status", AUTO_AIM)
    }
}