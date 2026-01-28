package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.DisabledAutoAssortment

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
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import org.firstinspires.ftc.teamcode.Finals.Constants
import org.firstinspires.ftc.teamcode.Finals.Systems.IndicatorSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.TeleOpModes.RedTeleOpV1
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance

@Disabled
@Autonomous(name="Blue Far Auto 15",group="Blue Tests")
class BlueAuto15: NextFTCOpMode() {
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
        ShooterSystem.setAlliance(Alliance.RED)
        PedroComponent.Companion.follower.setStartingPose(
            Pose(
                61.9400,
                7.9800,
                Math.toRadians(90.0)
            )
        )
        this.buildPaths()
    }
    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            ShooterSystem.autoAimOnCommand, //auto adjusts turret and flywheel velocity
            Delay(0.75),
            PassiveSystem.altTripleShootSequence, //shoots 3 balls
            ShooterSystem.autoAimOffCommand, //turns off autoaim command
            PassiveSystem.maxIntakeCommand, //sets intake motor power to 1.0 and opens intake gate
            FollowPath(Paths[0], true, 1.0), //goes to pickup first set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand, //sets intake motor power to 0.0 and closes intake gate
            ShooterSystem.autoAimOnCommand,
            Delay(0.25),
            FollowPath(Paths[1], true, 1.0), //goes to shoot first set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[2], true, 1.0),
            Delay(1.5),
            FollowPath(Paths[3], true, 1.0), //goes to pickup second set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand,
            Delay(0.25),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[4], true, 1.0), //goes to shoot second set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[5], true, 1.0), //goes to pickup third set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand,
            Delay(0.25),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[6], true, 1.0), //goes to shoot third set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[7], true, 1.0),
            Delay(0.25),
            PassiveSystem.stopIntakeCommand,
            Delay(0.25),
            ShooterSystem.autoAimOnCommand,
            FollowPath(Paths[8], true, 1.0), //goes to shoot third set of balls
            PassiveSystem.altTripleShootSequence,
            ShooterSystem.autoAimOffCommand,
        )
        main.schedule()
    }

    //the following builds robot movement pathings
    fun buildPaths() {
        val Path1 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(61.9400, 7.9800),
                    Pose(48.700, 35.000),
                    Pose(10.000, 35.500)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(180.0))
            .build()

        val Path2 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(10.000, 35.500), Pose(61.600, 25.750))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val Path3 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(61.600, 25.750),
                    Pose(49.750, 68.800),
                    Pose(10.750, 58.200)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val Path4 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(10.750, 58.200),
                    Pose(38.120, 61.000),
                    Pose(17.160, 65.160)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val Path5 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(17.160, 65.160), Pose(58.750, 82.250))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val Path6 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(58.750, 82.250), Pose(17.000, 82.250))
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val Path7 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(17.000, 82.250), Pose(58.750, 82.250))
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(180.0))
            .build()

        val Path8 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(58.750, 82.250),
                    Pose(12.650, 66.700),
                    Pose(9.000, 9.500)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(-90.0))
            .build()
        val Path9 = PedroComponent.Companion.follower
            .pathBuilder()
            .addPath(BezierLine(Pose(9.000, 9.500), Pose(62.500, 13.340)))
            .setConstantHeadingInterpolation(Math.toRadians(-90.0))
            .build()


        Paths += Path1
        Paths += Path2
        Paths += Path3
        Paths += Path4
        Paths += Path5
        Paths += Path6
        Paths += Path7
        Paths += Path8
        Paths += Path9
    }
    override fun onStop() {
        RedTeleOpV1.Companion.startPose = PedroComponent.Companion.follower.pose //stores robot position for teleop usage
    }
}