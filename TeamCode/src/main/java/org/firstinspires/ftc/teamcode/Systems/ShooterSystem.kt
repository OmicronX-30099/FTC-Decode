package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.TurretSubsystem

object ShooterSystem: SubsystemGroup(
    FlywheelSubsystem, HoodSubsystem, KickerSubsystem, TurretSubsystem
) {
    lateinit var GOAL_POSE: Pose;
    var CURRENT_TURRET_HEADING = 0;
    var CURRENT_FLYWHEEL_VELOCITY = 0;
    var CURRENT_HOOD_POSITION = 0;

    var eq = 1

    fun calibrateTurretHeading(currPose: Pose) {

    }
    fun calibrateFlywheelVelocity(currPose: Pose) {

    }
    fun calibrateHoodPosition(currPose: Pose) {
        if (currPose.distanceFrom(GOAL_POSE) <= 7) {
            eq = 1
            HoodSubsystem.lowMode.schedule()
        } else if (currPose.distanceFrom(GOAL_POSE) <= 10) {
            eq = 2
            HoodSubsystem.mediumMode.schedule()
        }
    }
}