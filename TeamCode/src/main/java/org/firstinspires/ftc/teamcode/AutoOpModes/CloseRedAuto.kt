package org.firstinspires.ftc.teamcode.AutoOpModes

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
import kotlin.math.*;

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

    lateinit var path1: PathChain;
    lateinit var path2: PathChain;
    lateinit var path3: PathChain;
    lateinit var path4: PathChain;
    lateinit var path5: PathChain;
    lateinit var path6: PathChain;
    lateinit var path7: PathChain;
    lateinit var path8: PathChain;
    lateinit var path9: PathChain;
    lateinit var path10: PathChain;
    lateinit var path11: PathChain;

    lateinit var routine: Command

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(109.6, 134.37, (-PI/2)))
        buildPaths()
        routine = SequentialGroup(
            InstantCommand(ShooterSystem::autoAim),
            FollowPath(path1, true, 1.0),
            IntakeSystem.startIntake,
        )
    }
    fun buildPaths() {

    }
}