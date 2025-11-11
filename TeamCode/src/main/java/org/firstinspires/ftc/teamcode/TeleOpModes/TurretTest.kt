package org.firstinspires.ftc.teamcode.TeleOpModes

import com.pedropathing.geometry.Pose
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Constants
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem
import java.lang.Math.toRadians

class TurretTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(ShooterSystem, IntakeSystem),
            PedroComponent(Constants::createFollower),
            BindingsComponent,
            BulkReadComponent
        )
    }
    override fun onInit() {
        telemetry.addLine("This OpMode is meant to test the auto aim toggling feature with the turret. Test using the circle button to enable and disable")
        telemetry.update()
    }

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(96.0,120.0,toRadians(90.0)))
        Gamepads.gamepad1.circle
            .whenBecomesTrue { ShooterSystem.autoAim() }
    }

    override fun onUpdate() {
        if (ShooterSystem.AUTO_AIM) {
            ShooterSystem.calibrateTurretPosition(follower.pose)
        }
        telemetry.update()
    }
}