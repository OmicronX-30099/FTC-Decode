package org.firstinspires.ftc.teamcode.Tests.ShooterTuning

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.extensions.pedro.PedroDriverControlled
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.DriverControlledCommand
@TeleOp
class FollowerTest: NextFTCOpMode() {
    init {
        addComponents(
//            PedroComponent(Constants::createFollower),
            BulkReadComponent,
            BindingsComponent
        )
    }

    lateinit var drivetrain: DriverControlledCommand

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(8.7,8.9,Math.toRadians(90.0)))
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX,
            true
        )
    }

    override fun onUpdate() {
        telemetry.addData("X", follower.pose.x)
        telemetry.addData("Y", follower.pose.y)
        telemetry.addData("Heading", follower.heading)
        telemetry.update()
    }
}