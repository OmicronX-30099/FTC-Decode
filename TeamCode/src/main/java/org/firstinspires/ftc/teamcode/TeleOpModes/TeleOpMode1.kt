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

    override fun onInit() {
        var autoPose: Pose? = null;
        follower.setStartingPose(autoPose)
        telemetry.addData("OpMode Selected: ", "DEFAULT = RED")
        telemetry.update()
    }
    override fun onWaitForStart() {
        if (Gamepads.gamepad1.rightBumper.get()) {
            telemetry.addData("OpMode Selected: ", "RED")
            ShooterSystem.setAlliance(false)
        } else if (Gamepads.gamepad1.leftBumper.get()) {
            telemetry.addData("OpMode Selected: ", "BLUE")
            ShooterSystem.setAlliance(true)
        }
        telemetry.update()
    }

    override fun onStartButtonPressed() {
        Gamepads.gamepad1.dpadUp
            .whenBecomesTrue {ShooterSystem.AUTO_AIM = !ShooterSystem.AUTO_AIM}
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue {ShooterSystem.calibrateShooter(follower.pose)}
        Gamepads.gamepad1.rightTrigger
            .inRange(0.0..1.0)
            .whenBecomesTrue {IntakeSubsystem.setIntakePower(Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get())}
        Gamepads.gamepad1.rightBumper
            .whenBecomesTrue {ShooterSystem.shoot}
    }
}