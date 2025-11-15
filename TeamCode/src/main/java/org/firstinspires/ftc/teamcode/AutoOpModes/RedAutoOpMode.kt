package org.firstinspires.ftc.teamcode.AutoOpModes

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
import org.firstinspires.ftc.teamcode.Constants
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem


@Autonomous(name = "Red 12 ball Auto", group = "AutoOpModes")
class RedAutoOpMode: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(ShooterSystem, IntakeSystem),
            PedroComponent(Constants::createFollower)
        )
    }

    lateinit var Path1: PathChain
    lateinit var Path2: PathChain
    lateinit var Path3: PathChain
    lateinit var Path4: PathChain
    lateinit var Path5: PathChain
    lateinit var Path6: PathChain
    lateinit var Path7: PathChain
    lateinit var Path8: PathChain
    val threeBallCommand = SequentialGroup(
        Delay(1.0),
        ShooterSystem.kickCommand,
        Delay(0.9),
        ShooterSystem.kickCommand,
        Delay(0.25),
        ShooterSystem.autoAimOffCommand
    )

    override fun onInit() {
        Paths()
        follower.setStartingPose(Pose(111.6,134.05, 0.0))
        telemetry.addData("Autoaim: ", ShooterSystem.AUTO_AIM)
        telemetry.addData("FLywheel Goal", ShooterSystem.FLYWHEEL_GOAL)
        telemetry.update()

    }

    override fun onStartButtonPressed() {
        var main = SequentialGroup(
            ShooterSystem.autoAimOnCommand,
            FollowPath(Path1,true,1.0),
            Delay(0.75),
            IntakeSystem.startIntakeCommand,
            threeBallCommand,
            FollowPath(Path2,true,0.7),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path3,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(2.0),
            IntakeSystem.startIntakeCommand,
            threeBallCommand,
            FollowPath(Path4,true,1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path5,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(2.0),
            IntakeSystem.startIntakeCommand,
            threeBallCommand,
            FollowPath(Path6,true,1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path7,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(2.0),
            IntakeSystem.startIntakeCommand,
            threeBallCommand,
            FollowPath(Path8, true, 1.0)
        )
        main.schedule()
    }

    override fun onUpdate() {
        telemetry.addData("Autoaim: ", ShooterSystem.AUTO_AIM)
        telemetry.addData("FLywheel Goal", ShooterSystem.FLYWHEEL_GOAL)
        telemetry.update()
        ShooterSystem.calibrateFlywheelVelocity(follower.pose)
        ShooterSystem.calibrateHoodPosition(follower.pose)
        ShooterSystem.calibrateTurretPosition(follower.pose)

    }

    fun Paths() {
        Path1 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(111.600, 134.050), Pose(96.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path2 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(96.000, 96.000),
                    Pose(67.400, 77.850),
                    Pose(192.500, 85.000),
                    Pose(72.600, 75.000),
                    Pose(124.250, 77.000)
                    /*
                    Pose(96.000, 96.000),
                    Pose(87.650, 77.85),
                    Pose(190.000, 85.000),
                    Pose(72.60, 75.000),
                    Pose(125.2, 71.0)
                    */
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path3 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(124.250, 77.000), Pose(96.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path4 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(96.000, 96.000),
                    Pose(75.000, 61.350),
                    Pose(132.000, 57.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path5 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(Pose(132.000, 57.000),
                    Pose(98.000,60.000),
                    Pose(96.000, 96.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path6 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(96.000, 96.000),
                    Pose(80.000, 23.000),
                    Pose(132.000, 35.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path7 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(132.000, 35.500), Pose(96.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        Path8 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(96.0,96.0),Pose(115.0,79.0))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

    }
}