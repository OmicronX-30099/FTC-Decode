package org.firstinspires.ftc.teamcode.Finals.AutoOpModes

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.FollowPath
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import org.firstinspires.ftc.teamcode.Finals.Constants
import org.firstinspires.ftc.teamcode.Finals.Systems.IndicatorSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.PI


@Autonomous(name="Achintya Blue 12 ball auto far")
class Blue12AutoFar: NextFTCOpMode() {
    init {
        addComponents(
            BulkReadComponent,
            LoopTimeComponent(),
            PedroComponent(Constants::createFollower),
            SubsystemComponent(ShooterSystem, PassiveSystem, IndicatorSystem)
        )
    }

    var Paths: Array<PathChain> = arrayOf()

    override fun onInit() {
        ShooterSystem.setAlliance(Alliance.BLUE)
        follower.setStartingPose(Pose(58.75,8.9, PI))
        this.buildPaths()
    }
    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            FollowPath(Paths[0], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[1], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[2], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[3], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[4], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[5], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[6], true, 1.0),
            Delay(1.0),
            FollowPath(Paths[7], true, 1.0),
        )
        main.schedule()
    }
    fun buildPaths() {
        val pickupFirst = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 8.900),
                    Pose(60.300, 70.700),
                    Pose(10.000, 58.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val openGate = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(10.000, 58.500),
                    Pose(39.000, 60.000),
                    Pose(17.000, 63.750)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootFirst = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(17.000, 63.750),
                    Pose(41.000, 63.425),
                    Pose(58.750, 76.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pickupSecond = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 76.000),
                    Pose(46.615, 85.085),
                    Pose(17.000, 83.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootSecond = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(17.000, 83.500),
                    Pose(46.615, 85.085),
                    Pose(58.750, 76.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pickupThird = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 76.000),
                    Pose(63.008, 30.000),
                    Pose(10.000, 35.250)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootThird = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(10.000, 35.250),
                    Pose(46.100, 38.300),
                    Pose(62.000, 26.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val leavePath = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(62.000, 26.000),
                    Pose(51.100, 58.250),
                    Pose(23.500, 70.500)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(0.0))
            .build()
        
        Paths += pickupFirst
        Paths += openGate
        Paths += shootFirst
        Paths += pickupSecond
        Paths += shootSecond
        Paths += pickupThird
        Paths += shootThird
        Paths += leavePath
    }
}