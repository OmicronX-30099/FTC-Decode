package org.firstinspires.ftc.teamcode.Test

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.Pose
import dev.frozenmilk.dairy.mercurial.continuations.Closure
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.exec
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.loop
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.match
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.sequence
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.wait
import dev.frozenmilk.dairy.mercurial.ftc.Mercurial
import org.firstinspires.ftc.teamcode.Constants
import java.lang.Math.toRadians
import kotlin.properties.Delegates

val mercurialPedroDrive = Mercurial.teleop {
    var follower: Follower by Delegates.notNull()
    var driveScalar: Double = 1.0

    val initializeFollower: Closure =
        sequence(
            exec { follower = Constants.createFollower(hardwareMap) },
            exec { follower.setStartingPose(Pose(70.5,70.5,toRadians(90.0))) },
            exec { follower.update() }
        )
    val updateFollower: Closure =
        sequence (
            exec { follower.update() },
            exec { follower.setTeleOpDrive(
                -gamepad1.left_stick_y.toDouble() * driveScalar,
                -gamepad1.left_stick_x.toDouble() * driveScalar,
                -gamepad1.right_stick_x.toDouble() * driveScalar,
                true
            ) }
        )
    val drivetrainSpeedControl: Closure =
        match { driveScalar }
            .branch(1.0, exec { driveScalar = 0.6 })
            .branch(0.6, exec { driveScalar = 0.2 })
            .branch(0.2, exec { driveScalar = 1.0 })
            .assertExhaustive()

    schedule(
        sequence(
            wait { inInit },
            initializeFollower,
            wait { inLoop },
            exec { follower.startTeleopDrive() },
            loop(
                updateFollower
            )
        )
    )
    bindSpawn (
        risingEdge { gamepad1.right_stick_button },
        drivetrainSpeedControl
    )

    dropToScheduler()
}