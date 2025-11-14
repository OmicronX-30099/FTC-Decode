package org.firstinspires.ftc.teamcode.AutoOpModes
import dev.nextftc.core.commands.delays.Delay
import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
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
import kotlin.math.PI


@Autonomous(name="Red side auto - close start")
class CloseRedAuto: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(IntakeSystem, ShooterSystem),
            BindingsComponent,
            BulkReadComponent,
            PedroComponent(Constants::createFollower)
        )
    }

    lateinit var Path1: PathChain
    lateinit var Path2: PathChain
    lateinit var Path3: PathChain
    lateinit var Path4: PathChain
    lateinit var PathToOpener: PathChain
    lateinit var Path5: PathChain
    lateinit var Path6: PathChain
    lateinit var Path7: PathChain
    lateinit var Path8: PathChain
    lateinit var Path9: PathChain
    lateinit var Path10: PathChain
    lateinit var Path11: PathChain

    lateinit var fullAuto: Command
    // IntakeSystem.startIntake,
    var ThreeKicker: Command = SequentialGroup(
        ShooterSystem.kickBall,
        Delay(0.15),
        ShooterSystem.kickBall,
        Delay(0.15),
        ShooterSystem.kickBall,
        Delay(0.15)
    )

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(109.6, 134.37, (-PI/2)))
        buildPaths()
        fullAuto = SequentialGroup(
            InstantCommand{ShooterSystem.autoAim()},
            FollowPath(Path1, true, 1.0),
            InstantCommand{ShooterSystem.calibrateShooter(Path1.endPose())},
            ThreeKicker,
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.TurnOffShooter()},
            FollowPath(Path2, true),
            FollowPath(Path3, true),
            IntakeSystem.startIntake,
            FollowPath(Path4, true),
            IntakeSystem.stopIntake,
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.calibrateShooter(Path4.endPose())},
            Delay(0.25),
            ThreeKicker,
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.TurnOffShooter()},
            FollowPath(PathToOpener, true),
            FollowPath(Path5, true),
            IntakeSystem.startIntake,
            FollowPath(Path6, true),
            IntakeSystem.stopIntake,
            FollowPath(Path7, true),
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.calibrateShooter(Path7.endPose())},
            Delay(0.25),
            ThreeKicker,
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.TurnOffShooter()},
            FollowPath(Path8, true),
            IntakeSystem.startIntake,
            FollowPath(Path9, true),
            IntakeSystem.stopIntake,
            FollowPath(Path10, true),
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.calibrateShooter(Path10.endPose())},
            Delay(0.25),
            ThreeKicker,
            InstantCommand{ShooterSystem.autoAim()},
            InstantCommand{ShooterSystem.TurnOffShooter()},
            FollowPath(Path11, true)
        )
        fullAuto.schedule()
    }
    fun buildPaths() {
        Path1 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(109.600, 134.370), Pose(105.000, 105.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(-90.0), Math.toRadians(-130.0))
            .build()

        Path2 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(105.000, 105.000),
                    Pose(89.105, 97.591),
                    Pose(100.000, 85.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(-130.0), Math.toRadians(0.0))
            .build()

        Path3 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(100.000, 85.000), Pose(126.500, 85.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
            .build()

        Path4 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(127.000, 85.000), Pose(105.000, 105.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(-130.0))
            .build()
        PathToOpener = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(105.000, 105.000), Pose(130.563, 83.533))
            )
            .setLinearHeadingInterpolation(Math.toRadians(-130.0), Math.toRadians(0.0))
            .build()

        Path5 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(130.787, 70.096),
                    Pose(70.320, 84.877),
                    Pose(102.000, 60.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
            .build()
        Path6 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(102.500, 60.000), Pose(132.000, 60.000))
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(0.0))
            .build()

        Path7 = follower
            .pathBuilder()
            .addPath(
                BezierCurve(
                    Pose(132.000, 60.000),
                    Pose(104.486, 55.956),
                    Pose(105.000, 105.000)
                )
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(-130.0))
            .build()

        Path8 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(105.0, 105.0), Pose(100.000, 35.500))
            )
            .setLinearHeadingInterpolation(Math.toRadians(-130.0), Math.toRadians(0.0))
            .build()

        Path9 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(100.000, 35.500), Pose(132.000, 35.500))
            )
            .setTangentHeadingInterpolation()
            .build()

        Path10 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(132.000, 35.500), Pose(105.0, 105.0))
            )
            .setLinearHeadingInterpolation(Math.toRadians(0.0), Math.toRadians(-130.0))
            .build()
        Path11 = follower
            .pathBuilder()
            .addPath(
                BezierLine(Pose(105.000, 105.000), Pose(120.0, 70.0))
            )
            .setLinearHeadingInterpolation(Math.toRadians(-130.0), Math.toRadians(-180.0))
            .build()
    }
}