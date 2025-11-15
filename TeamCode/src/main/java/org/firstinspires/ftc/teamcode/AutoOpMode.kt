package org.firstinspires.ftc.teamcode

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.delays.WaitUntil
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.FollowPath
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem
import kotlin.math.PI


@Autonomous
class AutoOpMode: NextFTCOpMode() {
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

    val threeBallCommand = SequentialGroup(
        ShooterSystem.kickCommand,
        WaitUntil{ ShooterSystem.fwm.velocity>=1070.0},
        ShooterSystem.kickCommand,
        WaitUntil{ ShooterSystem.fwm.velocity>=1070.0},
        ShooterSystem.kickCommand,
        ShooterSystem.autoAimOffCommand
    )

    override fun onInit() {
        Paths()
        follower.setStartingPose(Pose(109.6,134.37, 0.0))
    }

    override fun onStartButtonPressed() {
        var main = SequentialGroup(
            FollowPath(Path1,true,1.0),
            ShooterSystem.autoAimOnCommand,
            WaitUntil{ ShooterSystem.fwm.velocity>=1100.0},
            InstantCommand{IntakeSystem.intakeMotor.power = 0.4},
            threeBallCommand,
            IntakeSystem.startIntakeCommand,
            /*FollowPath(Path2,true,1.0),
            Delay(1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path3,true,1.0),
            Delay(1.0),
            IntakeSystem.startIntakeCommand,
            FollowPath(Path4,true,1.0),
            Delay(1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path5,true,1.0),
            Delay(1.0),
            IntakeSystem.startIntakeCommand,
            FollowPath(Path6,true,1.0),
            Delay(1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path7,true,1.0),
            Delay(1.0)*/
        )
        main.schedule()
    }

    override fun onUpdate() {
        ShooterSystem.calibrateFlywheelVelocity(follower.pose)
        ShooterSystem.calibrateHoodPosition(follower.pose)
        ShooterSystem.calibrateTurretPosition(follower.pose)
    }

    fun Paths() {
        Path1 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(109.600, 134.370), Pose(96.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path2 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(96.000, 96.000),
                    Pose(87.650, 77.85),
                    Pose(190.000, 85.000),
                    Pose(72.60, 75.000),
                    Pose(125.2, 71.0)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path3 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(128.750, 71.150), Pose(84.000, 72.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path4 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(84.000, 72.000),
                    Pose(75.000, 61.350),
                    Pose(133.000, 57.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path5 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(133.000, 57.000), Pose(84.000, 72.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path6 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(84.000, 72.000),
                    Pose(84.700, 33.180),
                    Pose(132.000, 35.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()

        Path7 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(133.000, 35.500), Pose(84.000, 12.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
    }
}