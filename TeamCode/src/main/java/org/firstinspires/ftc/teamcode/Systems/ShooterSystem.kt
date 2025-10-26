package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Systems.Subsystems.TurretSubsystem

object ShooterSystem : SubsystemGroup(TurretSubsystem) {
    fun autoShooter(currentPose: Pose) {
        TurretSubsystem.setTurretHeading(currentPose)
    }
}