package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.Subsystem
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D
import kotlin.properties.Delegates

object CalculationSubsystem: Subsystem {
    val goalPose: Pose = Pose(0,141)
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
}
