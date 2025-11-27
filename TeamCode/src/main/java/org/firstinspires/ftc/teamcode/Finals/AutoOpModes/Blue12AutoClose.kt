package org.firstinspires.ftc.teamcode.Finals.AutoOpModes

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import org.firstinspires.ftc.teamcode.Finals.Constants
import kotlin.math.PI

@Autonomous(name="Achintya Blue 12 ball auto close")
class Blue12AutoClose: NextFTCOpMode() {
    init {
        addComponents(
            BulkReadComponent,
            LoopTimeComponent(),
            PedroComponent(Constants::createFollower)
        )
    }

    override fun onInit() {
        follower.setStartingPose(Pose(32.4,132.1, PI))
    }
    override fun onStartButtonPressed() {

    }
    fun buildPaths() {

    }
}