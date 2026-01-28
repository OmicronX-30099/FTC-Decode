package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.DisabledAutoAssortment

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.delays.WaitUntil
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.FollowPath
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import org.firstinspires.ftc.teamcode.Finals.Constants
import org.firstinspires.ftc.teamcode.Finals.Systems.IndicatorSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.RedTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.abs

@Disabled
@Autonomous(name="Red far auto", group="Red Final")
class RedFarAutoOld: NextFTCOpMode() {
    init {
        addComponents(
            LoopTimeComponent(),
            BulkReadComponent,
            PedroComponent(Constants::createFollower),
            SubsystemComponent(ShooterSystem, PassiveSystem, IndicatorSystem)
        )
    }

    var Paths: Array<PathChain> = arrayOf()

    override fun onInit() {
        ShooterSystem.setAlliance(Alliance.RED)
        PedroComponent.Companion.follower.setStartingPose(
            Pose(
                79.56,
                7.9800,
                Math.toRadians(0.0)
            )
        )
        this.buildPaths()
    }

    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            FollowPath(Paths[0], true, 0.7),
            ShooterSystem.autoAimOnCommand,
            WaitUntil { abs(FlywheelSubsystem.flywheelControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0 },
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[1], true, 1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            Delay(0.2),
            FollowPath(Paths[2], true, 0.55),
            ShooterSystem.autoAimOnCommand,
            Delay(0.75),
            FollowPath(Paths[3], true, 1.0),
            WaitUntil { abs(FlywheelSubsystem.flywheelControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0 },
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[4], true, 1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            ShooterSystem.autoAimOnCommand,
            Delay(0.2),
            FollowPath(Paths[5], true, 1.0),
            WaitUntil { abs(FlywheelSubsystem.flywheelControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0 },
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[6], true, 1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            ShooterSystem.autoAimOnCommand,
            Delay(0.2),
            FollowPath(Paths[7], true, 1.0),
            WaitUntil { abs(FlywheelSubsystem.flywheelControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0 },
            Delay(0.1),
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            FollowPath(Paths[8], true, 1.0),
            ShooterSystem.autoAimOffCommand
        )
        main.schedule()
    }

    fun buildPaths() {
        val pushBot = PedroComponent.Companion.follower.pathBuilder()
            .addPath(
                BezierLine(
                    Pose(79.56, 7.98),
                    Pose(96.5, 7.98)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val firstIntake = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(96.500, 7.980),
                    Pose(79.7, 71.600),
                    Pose(130.500, 58.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val openGate = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(130.500, 58.500),
                    Pose(95.100, 62.850),
                    Pose(123.250, 63.80)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val firstShoot = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(123.250, 62.750),
                    Pose(94.000, 62.850),
                    Pose(82.250, 82.20)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val secondIntake = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(82.250, 82.200), Pose(125.250, 82.200))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val secondShoot = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(125.250, 82.200), Pose(82.250, 82.200))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val thirdIntake = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(82.250, 82.200),
                    Pose(82.350, 29.500),
                    Pose(130.500, 35.250)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val thirdShoot = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(130.500, 37.750), Pose(81.500, 23.375))
            )
            .setConstantHeadingInterpolation(Math.toRadians(0.0))
            .build()
        val leavePath = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(81.500, 23.750), Pose(93.500, 72.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(180.0))
            .build()

        Paths += pushBot
        Paths += firstIntake
        Paths += openGate
        Paths += firstShoot
        Paths += secondIntake
        Paths += secondShoot
        Paths += thirdIntake
        Paths += thirdShoot
        Paths += leavePath
    }
    override fun onStop() {
        RedTeleOpV1.Companion.startPose = PedroComponent.Companion.follower.pose
    }
}