package org.firstinspires.ftc.teamcode.Draft2.AutoOpModes

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.FollowPath
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Finals.Constants
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.PI


@Autonomous(name = "Dan Blue Auto")
class BlueAuto: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(ShooterSystem, PassiveSystem),
            PedroComponent(Constants::createFollower)
        )
    }
    var PathArray: Array<PathChain> = arrayOf()

    override fun onInit() {
        createPaths()
        follower.setStartingPose(Pose(32.4, 134.05, PI))
        ShooterSystem.setAlliance(Alliance.BLUE)
        /*
        telemetry.addData("Autoaim: ", ShooterSystem.)
        telemetry.addData("FLywheel Goal", ShooterSystem.FLYWHEEL_GOAL)
        telemetry.update()
        */

    }

    fun createPaths() {
        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(32.400, 134.050), Pose(48.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(48.000, 96.000),
                    Pose(76.600, 77.850),
                    Pose(-48.500, 85.000),
                    Pose(71.400, 75.000),
                    Pose(19.750, 77.000)
                    /*
                    Pose(96.000, 96.000),
                    Pose(87.650, 77.85),
                    Pose(190.000, 85.000),
                    Pose(72.60, 75.000),
                    Pose(125.2, 71.0)
                    */
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(19.750, 77.000), Pose(48.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(48.000, 96.000),
                    Pose(69.000, 61.350),
                    Pose(12.000, 57.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierCurve(Pose(12.000, 57.000),
                    Pose(46.000,60.000),
                    Pose(48.000, 96.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(48.000, 96.000),
                    Pose(44.000, 23.000),
                    Pose(12.000, 35.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        PathArray += follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(12.000, 35.500), Pose(48.000, 96.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(135.0))
            .build()
    }
    /*
    TODO From Dan:
    Near the end of the Auto, I have to manually rotate the bot so the teleop doesn't make itself a pain
     */
    override fun onStartButtonPressed() {
        var main = SequentialGroup(
            ShooterSystem.autoAimOnCommand,
            // Section 1: First throwing of Artifacts
            FollowPath(PathArray[0],true,1.0),
            Delay(0.75),
            PassiveSystem.maxIntakeCommand,
            PassiveSystem.tripleShootSequence,
            // Section 1 End. Section 2: Grab 1st set of Artifacts and throw
            ShooterSystem.autoAimOffCommand,
            FollowPath(PathArray[1],true,0.7),
            PassiveSystem.stopIntakeCommand,
            FollowPath(PathArray[2],true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(1.95),
            // start throwing
            PassiveSystem.maxIntakeCommand,
            PassiveSystem.tripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            // Section 2 End. Section 3: Grab 2nd set of Artifacts and throw
            FollowPath(PathArray[3],true,1.0),
            PassiveSystem.stopIntakeCommand,
            FollowPath(PathArray[4],true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(1.95),
            // start throwing
            PassiveSystem.maxIntakeCommand,
            PassiveSystem.tripleShootSequence,
            // Section 3 End. Section 4: Grab last set and throw.
            FollowPath(PathArray[5],true,1.0),
            PassiveSystem.stopIntakeCommand,
            FollowPath(PathArray[6],true,1.0),
            Delay(1.95),
            // throw balls
            PassiveSystem.maxIntakeCommand,
            PassiveSystem.tripleShootSequence
            // Section 5 End.
        )
        main.schedule()
    }

}