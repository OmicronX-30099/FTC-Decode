package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.AutoAssortment

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
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
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.BlueTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance


@Autonomous(name="BLUE-FarStart-MixedZones-12ball",group="Tests")
class Blue12AutoFarStart: NextFTCOpMode() {
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
        follower.setStartingPose(Pose(61.9400, 7.9800, Math.toRadians(180.0)))
        this.buildPaths()
    }
    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[0],true,0.75),
            Delay(0.5),
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[1], true, 1.0), //goes to pickup first set of balls
            Delay(0.3),
            PassiveSystem.stopIntakeCommand, //sets intake motor power to 0.0 and closes intake gate
            Delay(0.1),
            FollowPath(Paths[2], true, 0.65), //goes to open gate
            Delay(1.0),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[3], true, 1.0), //goes to shoot first set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[4], true, 1.0), //goes to pickup second set of balls
            Delay(0.3),
            PassiveSystem.stopIntakeCommand,
            Delay(0.1),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[5], true, 1.0), //goes to shoot second set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[6], true, 1.0), //goes to pickup third set of balls
            Delay(0.3),
            PassiveSystem.stopIntakeCommand,
            Delay(0.1),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[7], true, 1.0), //goes to shoot third set of balls
            Delay(0.2),
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            FollowPath(Paths[8], true, 1.0), //goes to front of gate
        )
        main.schedule()
    }
    fun buildPaths() {
        val pushBot = follower
            .pathBuilder()
            .addPath(BezierLine(Pose(61.940, 7.980), Pose(47.470, 7.980)))
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val firstIntake = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(47.470, 7.980),
                    Pose(48.835, 66.370),
                    Pose(12.000, 58.810)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val openGate = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(12.000, 58.810),
                    Pose(37.3600, 63.650),
                    Pose(19.125, 64.630)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val firstShoot = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(19.125, 64.630),
                    Pose(43.400, 66.200),
                    Pose(57.300, 82.380)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val secondIntake = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(57.300, 82.380), Pose(19.250, 82.380))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val secondShoot = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(19.250, 82.380), Pose(57.300, 82.380))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val thirdIntake = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(57.300, 82.380),
                    Pose(63.250, 31.930),
                    Pose(11.060, 38.280)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val thirdShoot = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(11.060, 38.280),
                    Pose(34.450, 29.300),
                    Pose(62.300, 29.750)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val leavePath = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(62.300, 29.750), Pose(35.280, 70.560))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(0.0))
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
        BlueTeleOpV1.startPose = follower.pose //stores robot position for teleop usage
    }
}