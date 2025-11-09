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
    var GOAL_POSE: Pose = Pose(144.0,144.0);
    var CURRENT_TURRET_HEADING = 0;
    var CURRENT_FLYWHEEL_VELOCITY = 0;
    var CURRENT_HOOD_POSITION = 0;

    var eq = 1

    fun calibrateTurretHeading(currPose: Pose) {

    }
    fun calibrateFlywheelVelocity(currPose: Pose) {
        var distance = currPose.distanceFrom(GOAL_POSE)
        var vel = 0.0;
        when (eq) {
            1 -> {vel = 5.35256 * distance + 810.51282}
            2 -> {vel = 5.96847 * distance + 656.75676}
            3 -> {vel = 5.66751 * distance + 686.83879}
        }
        FlywheelSubsystem.setTargetVelocity(vel)
    }
    fun calibrateHoodPosition(currPose: Pose) {
        if (currPose.distanceFrom(GOAL_POSE) <= 7) {
            eq = 1
            HoodSubsystem.hoodServo.position = 0.0
        } else if (currPose.distanceFrom(GOAL_POSE) <= 10) {
            eq = 2
            HoodSubsystem.hoodServo.position = 0.4
        } else {
            eq = 3
            HoodSubsystem.hoodServo.position = 1.0
        }
    }
}