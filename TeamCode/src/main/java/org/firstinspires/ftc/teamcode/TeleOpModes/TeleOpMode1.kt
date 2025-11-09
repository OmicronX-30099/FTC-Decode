package org.firstinspires.ftc.teamcode.TeleOpModes

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.IntakeSubsystem

@TeleOp(group = "TeleOpModes", name = "TeleOp1")
class TeleOpMode1 : NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(ShooterSystem, IntakeSystem),
            PedroComponent(Constants::createFollower),
            BulkReadComponent,
            BindingsComponent
        )
    }

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(96.0,120.0,90.0))
    }

    override fun onUpdate() {
        ShooterSystem.calibrateHoodPosition(follower.pose)
    }
}