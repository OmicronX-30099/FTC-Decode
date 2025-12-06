package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.FinalAutos.Blue

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.delays.WaitUntil
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
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.BlueTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.RedTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance
import kotlin.math.abs


@Autonomous(name="Blue close auto", group="Blue Final")
class BlueCloseAuto: NextFTCOpMode() {
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
        ShooterSystem.setAlliance(Alliance.BLUE)
        follower.setStartingPose(
            Pose(
                32.9,
                132.0,
                Math.toRadians(180.0)
            )
        )
        this.buildPaths()
    }

    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            FollowPath(Paths[0],true,1.0),
            ShooterSystem.autoAimOnCommand,
            WaitUntil{abs(FlywheelSubsystem.flywheeControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0},
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[1],true,1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            Delay(0.2),
            FollowPath(Paths[2],true,0.55),
            ShooterSystem.autoAimOnCommand,
            Delay(0.75),
            FollowPath(Paths[3],true,1.0),
            WaitUntil{abs(FlywheelSubsystem.flywheeControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0},
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[4],true,1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            ShooterSystem.autoAimOnCommand,
            Delay(0.2),
            FollowPath(Paths[5],true,1.0),
            WaitUntil{abs(FlywheelSubsystem.flywheeControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0},
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[6],true,1.0),
            Delay(0.2),
            PassiveSystem.stopIntakeCommand,
            ShooterSystem.autoAimOnCommand,
            Delay(0.2),
            FollowPath(Paths[7],true,1.0),
            WaitUntil{abs(FlywheelSubsystem.flywheeControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0},
            Delay(0.1),
            PassiveSystem.altTripleShootSequence,
            Delay(0.25),
            FollowPath(Paths[8],true,0.6),
            ShooterSystem.autoAimOffCommand,
            FollowPath(Paths[9],true,0.9),
        )
        main.schedule()
    }

    fun buildPaths() {
        val shootPreload = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(32.900, 132.000), Pose(59.250, 82.200))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val firstIntake = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(59.250, 82.200), Pose(16.250, 82.200))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val openGate = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(16.250, 82.200),
                    Pose(58.750, 81.100),
                    Pose(18.250, 79.250)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val firstShoot = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(18.250, 79.250),
                    Pose(36.900, 83.000),
                    Pose(59.250, 82.200)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val secondIntake = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(59.250, 82.200),
                    Pose(48.350, 52.700),
                    Pose(11.000, 58.500)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val secondShoot = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(11.000, 58.500),
                    Pose(47.500, 62.850),
                    Pose(59.250, 82.200)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val thirdIntake = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(59.250, 82.200),
                    Pose(60.650, 25.300),
                    Pose(11.000, 37.750)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val thirdShoot = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(11.000, 37.750), Pose(60.000, 23.375))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val pushBot = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 23.375),
                    Pose(60.480, 10.750),
                    Pose(45.000, 7.980)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val leavePath = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(45.000, 7.980),
                    Pose(57.700, 40.550),
                    Pose(48.000, 72.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(0.0))
            .build()

        Paths += shootPreload
        Paths += firstIntake
        Paths += openGate
        Paths += firstShoot
        Paths += secondIntake
        Paths += secondShoot
        Paths += thirdIntake
        Paths += thirdShoot
        Paths += pushBot
        Paths += leavePath
    }
    override fun onStop() {
        BlueTeleOpV1.startPose = follower.pose
    }
}