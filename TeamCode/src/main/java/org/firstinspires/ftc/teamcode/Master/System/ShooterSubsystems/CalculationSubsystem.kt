package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.Subsystem
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D
import org.firstinspires.ftc.teamcode.Util.LimelightStatus
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.properties.Delegates
import kotlin.math.round

object CalculationSubsystem: Subsystem {
    val goalPose: Pose = Pose(0.0,141.0)
    fun calculateHoodPosition(botPose: Pose3D?, pedroPose: Pose = Pose(0.0,0.0)): Double {
        lateinit var currPose: Pose
        var targetHoodPose: Double by Delegates.notNull()
        if (botPose != null) {
            currPose = Pose(botPose.position.x,botPose.position.y)
        } else {
            currPose = pedroPose
        }
        var distanceToGoal = currPose.distanceFrom(currPose)
        when (distanceToGoal) {
            in 0.0..<3.0 -> targetHoodPose = 0.0
            in 3.0..<5.0 -> targetHoodPose = 0.0
            else -> targetHoodPose = 0.0
        }
        return targetHoodPose
    }
    fun calculateTurretPosition(limelightStatus: LimelightStatus, pedroPose: Pose = Pose(0.0,0.0), currentTurretPos: Double): Double {
        var targetTurretPos: Double by Delegates.notNull()
        if (limelightStatus == LimelightStatus.TARGETS_DETECTED) {
            targetTurretPos = ((limelightStatus.targetX / 360) * (100 / 24) * 384.5 * -1) + currentTurretPos
        } else {
            var angle = atan2(goalPose.x-pedroPose.x,goalPose.y-pedroPose.y)
            targetTurretPos = (((pedroPose.heading-(PI/2))+angle) / (2*PI)) * (100/24) * 384.5 * -1
        }
        return round(targetTurretPos)
    }
}
