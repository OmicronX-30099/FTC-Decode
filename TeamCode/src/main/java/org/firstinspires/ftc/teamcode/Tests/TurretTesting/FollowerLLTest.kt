package org.firstinspires.ftc.teamcode.Tests.TurretTesting

import com.pedropathing.geometry.Pose
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.extensions.pedro.PedroDriverControlled
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.DriverControlledCommand
import kotlin.math.PI

@TeleOp(name="Follower tester with ll correction")
class FollowerLLTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            PedroComponent(Constants::createFollower),
            SubsystemComponent(RGBSubsystem)
        )
    }

    lateinit var ll: Limelight3A
    lateinit var drivetrain: DriverControlledCommand

    override fun onInit() {
        ll = hardwareMap.get(Limelight3A::class.java, "limelightSmartCamera")
        ll.pipelineSwitch(1)
        ll.start()
    }
    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(8.7, 8.9, Math.toRadians(90.0)))
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX,
            true
        )
        drivetrain.schedule()
    }
    override fun onUpdate() {
        var result = ll.latestResult
        if (result != null && result.isValid) {
            telemetry.addData("Limelight", "Target Detected")
            var adjusted_y = (result.botpose.position.y * (100/2.54)+72) + Math.sin(follower.pose.heading) * 4
            var adjusted_x = (result.botpose.position.x * (100/2.54)+72) + Math.cos(follower.pose.heading) * 4
            follower.setStartingPose(Pose(adjusted_x,adjusted_y,follower.pose.heading))
            telemetry.addData("Limelight", "Reset follower to = " + Pose(adjusted_x,adjusted_y,Math.toDegrees(follower.pose.heading)))
            telemetry.addData("llx", (result.botpose.position.y * (100 / 2.54)) + 72)
            telemetry.addData("lly", (result.botpose.position.x * (100 / 2.54) * -1) + 72)
        } else {
            telemetry.addData("Limelight", "No targets detected")
        }
        telemetry.addData("Follower","pose = " + follower.pose.toString())
        telemetry.addData("Follower","x = " + follower.pose.x.toString())
        telemetry.addData("Follower","y = " + follower.pose.y.toString())
        telemetry.addData("Follower","heading = " + (follower.pose.heading * (180/ PI)).toString())
    }
}
