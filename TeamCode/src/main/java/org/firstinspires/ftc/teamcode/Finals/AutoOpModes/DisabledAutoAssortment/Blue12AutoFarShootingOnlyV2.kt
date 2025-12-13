package org.firstinspires.ftc.teamcode.Finals.AutoOpModes.DisabledAutoAssortment

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
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
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance

@Disabled
@Autonomous(name="BLUE-far zone only-12ball-v2",group="Tests")
class Blue12AutoFarShootingOnlyV2: NextFTCOpMode() {
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
        follower.setStartingPose(Pose(56.200, 8.900, Math.toRadians(90.0)))
        this.buildPaths()
    }
    override fun onStartButtonPressed() {
        val main = SequentialGroup(
            InstantCommand { ShooterSystem.calibrateTurret(follower.pose) },
            InstantCommand { ShooterSystem.calibrateHood(follower.pose)},
            InstantCommand { FlywheelSubsystem.flywheelControl.goal = KineticState(0.0,1520.0) },
            InstantCommand { FlywheelSubsystem.flywheelAutoAim = true},
            Delay(1.25),
            PassiveSystem.altTripleShootSequence,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[0], true, 1.0), //goes to pickup first set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand, //sets intake motor power to 0.0 and closes intake gate
            Delay(0.25),
            FollowPath(Paths[1], true, 1.0), //goes to open gate
            InstantCommand { FlywheelSubsystem.flywheelControl.goal = KineticState(0.0,1480.0) },
            Delay(1.0),
            FollowPath(Paths[2], true, 1.0), //goes to shoot first set of balls
            InstantCommand { ShooterSystem.calibrateHood(follower.pose)},
            Delay(0.5),
            InstantCommand { ShooterSystem.calibrateTurret(follower.pose) },
            Delay(0.75),
            PassiveSystem.altTripleShootSequence,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[3], true, 1.0), //goes to pickup second set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand,
            Delay(0.25),
            InstantCommand { FlywheelSubsystem.flywheelControl.goal = KineticState(0.0,1480.0) },
            FollowPath(Paths[4], true, 1.0), //goes to shoot second set of balls
            InstantCommand { ShooterSystem.calibrateHood(follower.pose)},
            Delay(0.5),
            InstantCommand { ShooterSystem.calibrateTurret(follower.pose) },
            Delay(0.75),
            PassiveSystem.altTripleShootSequence,
            PassiveSystem.maxIntakeCommand,
            FollowPath(Paths[5], true, 1.0), //goes to pickup third set of balls
            Delay(0.25),
            PassiveSystem.stopIntakeCommand,
            Delay(0.25),
            InstantCommand { FlywheelSubsystem.flywheelControl.goal = KineticState(0.0,1480.0) },
            FollowPath(Paths[6], true, 1.0), //goes to shoot third set of balls
            InstantCommand { ShooterSystem.calibrateHood(follower.pose)},
            Delay(0.5),
            InstantCommand { ShooterSystem.calibrateTurret(follower.pose) },
            Delay(0.75),
            PassiveSystem.altTripleShootSequence,
            FollowPath(Paths[7], true, 1.0), //goes to front of gate
        )
        main.schedule()
    }
    fun buildPaths() {
        val pickupFirst = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(56.200, 8.900),
                    Pose(65.000, 60.000),
                    Pose(12.000, 57.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(180.0))
            .build()

        val openGate = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(12.000, 57.000),
                    Pose(38.000, 62.000),
                    Pose(17.000, 67.000)
                )
            )
            .setConstantHeadingInterpolation(Math.toRadians(180.0))
            .build()

        val shootFirst = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(17.000, 67.000),
                    Pose(41.000, 63.425),
                    Pose(60.000, 23.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val pickupSecond = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 23.000),
                    Pose(60.000, 83.500),
                    Pose(17.000, 83.500)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(180.0))
            .build()

        val shootSecond = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(17.000, 83.500),
                    Pose(46.615, 85.085),
                    Pose(60.000, 23.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val pickupThird = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 23.000),
                    Pose(60.000, 28.500),
                    Pose(10.000, 35.250)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(180.0))
            .build()

        val shootThird = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(10.000, 35.250),
                    Pose(46.100, 38.300),
                    Pose(60.000, 23.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(180.0), Math.toRadians(90.0))
            .build()

        val leavePath = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(60.000, 23.000),
                    Pose(51.100, 58.250),
                    Pose(23.500, 58.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(90.0), Math.toRadians(160.0))
            .build()
        
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