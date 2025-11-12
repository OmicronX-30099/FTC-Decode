package org.firstinspires.ftc.teamcode.TeleOpModes

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.core.units.deg
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Constants
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem
import java.lang.Math.toRadians

@TeleOp(name="Hood and Flywheel Test")
class HoodFlywheelTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(IntakeSystem, ShooterSystem),
            PedroComponent(Constants::createFollower),
            BindingsComponent,
            BulkReadComponent
        )
    }

    override fun onInit() {
        telemetry.addLine("This OpMode is meant to test the auto aim toggling feature with Hood and Flywheel. Run it and press the circle to test. Look at telemetry to see if auto aim status has changed, as well as flywheel and hood values")
        telemetry.update()
    }

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(96.0,120.0,toRadians(90.0)))
        Gamepads.gamepad1.circle
            .whenBecomesTrue { ShooterSystem.autoAim() }
    }

    override fun onUpdate() {
        ShooterSystem.calibrateHoodPosition(follower.pose)
        ShooterSystem.calibrateFlywheelVelocity(follower.pose)

        telemetry.update()
    }
}
