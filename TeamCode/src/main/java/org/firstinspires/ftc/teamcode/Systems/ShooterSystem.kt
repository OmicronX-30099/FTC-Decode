package org.firstinspires.ftc.teamcode.Systems

import android.R
import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.TurretSubsystem
import kotlin.math.*

object ShooterSystem: SubsystemGroup(
    FlywheelSubsystem, HoodSubsystem, KickerSubsystem, TurretSubsystem
) {

    var AUTO_AIM: Boolean = false;
    var GOAL_POSE: Pose = Pose(144.0,144.0);
    var HEADING_CONSTANT: Double = 0.0;
    var CURRENT_TURRET_TICKS = 0.0;
    var CURRENT_FLYWHEEL_VELOCITY = 0.0;
    var CURRENT_HOOD_POSITION = 0.0;

    var eq = 1


    fun calibrateShooter(currPose: Pose, currHeading: Double) {
        if (AUTO_AIM) {
            calibrateFlywheelVelocity(currPose)
            calibrateHoodPosition(currPose)
            calibrateTurretHeading(currPose, currHeading)
        } else {
            FlywheelSubsystem.setTargetVelocity(-500.0)
        }
        ActiveOpMode.telemetry.addData("TURRET TICKS", CURRENT_TURRET_TICKS)
        ActiveOpMode.telemetry.addData("FLYWHEEL VELOCITY", FlywheelSubsystem.flywheelMotors.velocity)
        ActiveOpMode.telemetry.addData("HOOD POSITION", CURRENT_HOOD_POSITION)
    }
    fun autoAim(autobool: Boolean) {
        AUTO_AIM = autobool
    }
    fun calibrateFlywheelVelocity(currPose: Pose) {
        var distance = currPose.distanceFrom(GOAL_POSE)
        var vel = 0.0;
        when (eq) {
            1 -> {vel = 5.35256 * distance + 820.5}
            2 -> {vel = 5.96847 * distance + 665.75}
            3 -> {vel = 5.66751 * distance + 675.8}
        }
        FlywheelSubsystem.setTargetVelocity(vel)
        CURRENT_FLYWHEEL_VELOCITY = vel
    }
    fun calibrateHoodPosition(currPose: Pose) {
        var dist = currPose.distanceFrom(GOAL_POSE)
        ActiveOpMode.telemetry.addData("DISTANCE FROM GOAL", dist)
        if (dist <= 84.0) {
            eq = 1
            HoodSubsystem.hoodServo.position = 0.0
        } else if (dist <= 120.0) {
            eq = 2
            HoodSubsystem.hoodServo.position = 0.4
        } else {
            eq = 3
            HoodSubsystem.hoodServo.position = 1.0
        }
        CURRENT_HOOD_POSITION = HoodSubsystem.hoodServo.position
    }
    fun calibrateTurretHeading(currPose: Pose, heading: Double) {
        var angle = atan((GOAL_POSE.y-currPose.y)/(GOAL_POSE.x-currPose.x)) + HEADING_CONSTANT
        var ticks = (heading-angle) / (2 * PI) * (100 / 24) * 384.5 + 192.5;
        TurretSubsystem.setTurretPosition(ticks)
        CURRENT_TURRET_TICKS = ticks
    }
}