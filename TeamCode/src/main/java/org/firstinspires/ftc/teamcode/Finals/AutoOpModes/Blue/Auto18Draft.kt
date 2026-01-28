package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.Blue

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.NextFTCOpMode
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance


@Autonomous(name = "18 Blue Close Draft")
class Auto18Draft: NextFTCOpMode() {
    override fun onInit() {
        // Essentially sets the autoaim to track the blue goal.
        ShooterSystem.setAlliance(Alliance.BLUE)
        // Sets where the Bot is positioned according to the code at the start
        PedroComponent.Companion.follower.setStartingPose(
            Pose(
                32.9,
                131.3,
                Math.toRadians(180.0)
            )
        )
        // Intiates the variables for the pathing
        this.buildPaths()
    }

    // Intialize pathing variable, variable is of like a dictionary type from Python
    var Paths: MutableMap<String, PathChain> = mutableMapOf<String, PathChain>()
    fun buildPaths() {
        // Function Meant to Intialize all the pathing variables

        Paths.put("ScoreLoad", follower.pathBuilder().addPath(
            BezierLine(
                Pose(32.900, 131.300),

                Pose(58.750, 84.000)
            )
        ).setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(180.0))

            .build())

        Paths.put("IntakeOutOfOrder", follower.pathBuilder().addPath(
            BezierCurve(
                Pose(58.750, 84.000),
                Pose(53.823, 56.980),
                Pose(8.000, 59.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(180.0))

            .build())

        Paths.put("PositionFirstSet", follower.pathBuilder().addPath(
            BezierLine(
                Pose(8.000, 59.000),

                Pose(40.000, 60.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(180.0))

            .build())

       Paths.put("OpenGate1", follower.pathBuilder().addPath(
            BezierLine(
                Pose(40.000, 60.000),

                Pose(10.000, 58.000)
            )
        ).setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(135.0))

            .build())
        Paths.put("MoveToFirstSet", follower.pathBuilder().addPath(
            BezierCurve(
                Pose(10.000, 58.000),
                Pose(42.226, 58.721),
                Pose(58.000, 83.000)
            )
        ).setLinearHeadingInterpolation(Math.toRadians(135.0), Math.toRadians(180.0))

            .build())

        Paths.put("PositionFirstSet", follower.pathBuilder().addPath(
            BezierLine(
                Pose(58.000, 83.000),

                Pose(14.000, 83.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(180.0))

            .build())

        Paths.put("PositionGateBalls1", follower.pathBuilder().addPath(
            BezierLine(
                Pose(14.000, 83.000),

                Pose(34.000, 58.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(135.0))

            .build())

        Paths.put("OpenGate2", follower.pathBuilder().addPath(
            BezierLine(
                Pose(34.000, 58.000),

                Pose(10.000, 58.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(135.0))

            .build())

        Paths.put("PositionGateBalls2", follower.pathBuilder().addPath(
            BezierLine(
                Pose(10.000, 58.000),

                Pose(34.000, 59.000)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(135.0))

            .build())

        Paths.put("MoveThirdSet", follower.pathBuilder().addPath(
            BezierLine(
                Pose(34.000, 59.000),

                Pose(45.986, 35.224)
            )
        ).setLinearHeadingInterpolation(Math.toRadians(135.0), Math.toRadians(180.0))

            .build())

        Paths.put("IntakeThirdSet", follower.pathBuilder().addPath(
            BezierLine(
                Pose(45.986, 35.224),

                Pose(8.266, 35.449)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(180.0))

            .build())

        Paths.put("PositionThirdSet", follower.pathBuilder().addPath(
            BezierLine(
                Pose(8.266, 35.449),

                Pose(35.723, 34.848)
            )
        ).setConstantHeadingInterpolation(Math.toRadians(180.0))

            .build())
    }
}