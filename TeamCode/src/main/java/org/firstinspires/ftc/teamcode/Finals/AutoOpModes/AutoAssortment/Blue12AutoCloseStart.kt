package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.AutoAssortment

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
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
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.BlueTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.PI

@Disabled
@Autonomous(name="Achintya Blue 12 ball auto close",group="Tests")
class Blue12AutoCloseStart: NextFTCOpMode() {
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
        follower.setStartingPose(Pose(32.9, 131.3, PI))
        this.buildPaths()
    }
    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            // Turn on auto aim for preload
            ShooterSystem.autoAimOnCommand,
            // Go to preload score position
            FollowPath(Paths[0], true, 1.0),
            // Shoot preload, uses alt-tripleshoot because it is closer position
            PassiveSystem.altTripleShootSequence,
            // Turn off auto aim for power saving
            ShooterSystem.autoAimOffCommand,
            // Start intake for first row of balls
            PassiveSystem.maxIntakeCommand,
            // Pickup first row of balls
            FollowPath(Paths[1], true, 1.0),
            Delay(0.3),
            // Stop intake for power save
            PassiveSystem.stopIntakeCommand,
            // Go to open gate
            FollowPath(Paths[2], true, 1.0),
            // Wait for gate opening
            Delay(1.25),
            // Turn on auto aim to shoot first row of balls
            ShooterSystem.autoAimOnCommand,
            // Go to close shoot position for first row of balls
            FollowPath(Paths[3], true, 1.0),
            // Shoot first row, use alt-tripleshoot because it is closer position
            PassiveSystem.altTripleShootSequence,
            // Turn off auto aim for power saving
            ShooterSystem.autoAimOffCommand,
            // Start intake for second row of balls
            PassiveSystem.maxIntakeCommand,
            // Pickup second row of balls
            FollowPath(Paths[4], true, 1.0),
            Delay(0.3),
            // Stop intake for power save
            PassiveSystem.stopIntakeCommand,
            // Turn on auto aim to shoot balls
            ShooterSystem.autoAimOnCommand,
            // Go to close shoot position for second row of balls
            FollowPath(Paths[5], true, 1.0),
            // Shoot second row, use alt-tripleshoot because it is closer position
            PassiveSystem.altTripleShootSequence,
            // Turn off auto aim for power saving
            ShooterSystem.autoAimOffCommand,
            // Start intake for third row of balls
            PassiveSystem.maxIntakeCommand,
            // Pickup third row of balls
            FollowPath(Paths[6], true, 1.0),
            Delay(0.3),
            // Stop intake for power save
            PassiveSystem.stopIntakeCommand,
            // Turn on auto aim to shoot balls
            ShooterSystem.autoAimOnCommand,
            // Go to far shoot position for third row of balls
            FollowPath(Paths[7], true, 1.0),
            // Shoot third row, use tripleshoot because it is far position
            PassiveSystem.tripleShootSequence,
            // Turn off auto aaim for power saving
            ShooterSystem.autoAimOffCommand,
            // Go closer to gate for teleop ease of access
            FollowPath(Paths[8], true, 1.0)
        )
        main.schedule()
    }

    //the following builds robot movement pathings
    fun buildPaths() {
        val scorePreload = follower.pathBuilder()
            .addPath(
                BezierLine(Pose(32.900, 131.300), Pose(58.750, 84.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pickupFirst = follower.pathBuilder()
            .addPath(
                BezierLine(Pose(58.750, 84.000), Pose(17.000, 84.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val openGate = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(17.000, 84.000),
                    Pose(38.000, 72.000),
                    Pose(17.000, 73.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootFirst = follower.pathBuilder()
            .addPath(
                BezierLine(Pose(17.000, 73.000), Pose(58.750, 73.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pickupSecond = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 73.000),
                    Pose(39.500, 54.393),
                    Pose(10.000, 58.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootSecond = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(10.000, 58.500),
                    Pose(39.500, 54.393),
                    Pose(58.750, 73.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pickupThird = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 73.000),
                    Pose(63.075, 30.000),
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
                    Pose(58.000, 23.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val leavePath = follower.pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.000, 23.000),
                    Pose(51.100, 58.250),
                    Pose(23.500, 70.500)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(180.0))
            .build()

        Paths += scorePreload
        Paths += pickupFirst
        Paths += openGate
        Paths += shootFirst
        Paths += pickupSecond
        Paths += shootSecond
        Paths += pickupThird
        Paths += shootThird
        Paths += leavePath
    }
    override fun onStop() {
        BlueTeleOpV1.startPose = follower.pose //stores robot position for teleop usage
    }
}
