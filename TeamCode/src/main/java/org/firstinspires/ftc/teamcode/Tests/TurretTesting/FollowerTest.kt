package org.firstinspires.ftc.teamcode.Tests.TurretTesting

import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.extensions.pedro.PedroDriverControlled
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.DriverControlledCommand

class FollowerTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            PedroComponent(Constants::createFollower),
            SubsystemComponent(RGBSubsystem)
        )
    }

    lateinit var drivetrain: DriverControlledCommand

    override fun onStartButtonPressed() {
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX
        )
        drivetrain.schedule()
    }

    override fun onUpdate() {
        telemetry.addData("Follower","pose = " + follower.pose.toString())
        telemetry.addData("Follower","x = " + follower.pose.x.toString())
        telemetry.addData("Follower","y = " + follower.pose.y.toString())
        telemetry.addData("Follower","heading = " + follower.pose.heading.toString())
    }
}