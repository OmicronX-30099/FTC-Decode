package org.firstinspires.ftc.teamcode

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.delays.Delay
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
class BlueAutoOpMode: NextFTCOpMode() {
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
        Delay(0.55),
        ShooterSystem.kickCommand,
        ShooterSystem.autoAimOffCommand
    )

    override fun onInit() {
        Paths()
        follower.setStartingPose(Pose(34.4,134.37, PI))
        telemetry.addData("Autoaim: ", ShooterSystem.AUTO_AIM)
        telemetry.addData("FLywheel Goal", ShooterSystem.FLYWHEEL_GOAL)
        telemetry.update()

    }

    override fun onStartButtonPressed() {
        var main = SequentialGroup(
            FollowPath(Path1,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(0.45),
            InstantCommand{IntakeSystem.intakeMotor.power = 1.0},
            threeBallCommand,
            IntakeSystem.startIntakeCommand,
            FollowPath(Path2,true,0.7),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path3,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(0.45),
            InstantCommand{IntakeSystem.intakeMotor.power = 1.0},
            threeBallCommand,
            IntakeSystem.startIntakeCommand,
            FollowPath(Path4,true,1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path5,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(0.45),
            InstantCommand{IntakeSystem.intakeMotor.power = 1.0},
            threeBallCommand,
            IntakeSystem.startIntakeCommand,
            FollowPath(Path6,true,1.0),
            IntakeSystem.stopIntakeCommand,
            FollowPath(Path7,true,1.0),
            ShooterSystem.autoAimOnCommand,
            Delay(0.55),
            InstantCommand{IntakeSystem.intakeMotor.power = 1.0},
            threeBallCommand,
            IntakeSystem.startIntakeCommand,
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
                BezierLine(Pose(34.400, 134.370), Pose(48.000, 96.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        Path2 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(48.000, 96.000),
                    Pose(76.600, 77.85),
                    Pose(-46.000, 85.000),
                    Pose(71.400, 75.000),
                    Pose(21.080, 71.0)
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

        Path3 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(21.080, 71.0), Pose(60.000, 72.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        Path4 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 72.000),
                    Pose(69.000, 61.350),
                    Pose(11.000, 57.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        Path5 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(11.000, 57.000), Pose(60.000, 72.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        Path6 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 72.000),
                    Pose(59.300, 33.180),
                    Pose(12.000, 35.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        Path7 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(12.000, 35.500), Pose(60.000, 12.000))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()
        Path8 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(60.0,12.0),Pose(60.0,50.0))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

    }
}