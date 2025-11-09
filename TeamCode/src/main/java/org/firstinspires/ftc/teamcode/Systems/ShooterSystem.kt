package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
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
        var dist = currPose.distanceFrom(GOAL_POSE)
        ActiveOpMode.telemetry.addData("DISTANCE FROM GOAL", dist)
        if (dist <= 84.0) {
            eq = 1
            HoodSubsystem.lowMode.schedule()
            ActiveOpMode.telemetry.addData("STATUS", "Setting to low mode")
        } else if (dist <= 120.0) {
            eq = 2
            HoodSubsystem.mediumMode.schedule()
            ActiveOpMode.telemetry.addData("STATUS", "Setting to medium mode")
        } else {
            eq = 3
            HoodSubsystem.highMode.schedule()
            ActiveOpMode.telemetry.addData("STATUS", "Setting to high mode")
        }
    }
}