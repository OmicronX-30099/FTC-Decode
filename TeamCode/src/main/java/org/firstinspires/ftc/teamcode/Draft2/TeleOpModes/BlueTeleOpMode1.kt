package org.firstinspires.ftc.teamcode.Draft2.TeleOpModes

import com.pedropathing.geometry.Pose
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
import org.firstinspires.ftc.teamcode.Draft2.System.IntakeSystem
import org.firstinspires.ftc.teamcode.Draft2.System.ShooterSystem
import org.firstinspires.ftc.teamcode.Draft2.System.TransferSystem
import org.firstinspires.ftc.teamcode.Constants.Constants
import org.firstinspires.ftc.teamcode.Util.Alliance

@TeleOp(name = "Blue TeleOp V1")
class  BlueTeleOpMode1: NextFTCOpMode() {
    init {
        addComponents(
    BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(IntakeSystem, ShooterSystem, TransferSystem),
            PedroComponent(Constants::createFollower)
        )
    }
    lateinit var drivetrain: DriverControlledCommand

    override fun onInit() {
        follower.setStartingPose(Pose(0.0, 0.0))
        ShooterSystem.setAlliance(Alliance.BLUE)
    }
    override fun onStartButtonPressed() {
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX
        )
        drivetrain.scalar = 0.9
        drivetrain.schedule()

        // Intaking mechanism with gate
        Gamepads.gamepad1.rightTrigger.greaterThan(0.0).or(Gamepads.gamepad1.leftTrigger.greaterThan(0.0))
            .whenBecomesTrue(IntakeSystem.openGateCommand)
            .whenTrue(IntakeSystem.intakeCommand(Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get()))
            .whenBecomesFalse(IntakeSystem.stopIntakeCommand)

        Gamepads.gamepad1.triangle
            .whenBecomesTrue  { ShooterSystem.switchAutoAim() }
        Gamepads.gamepad1.rightBumper
            .whenBecomesTrue(TransferSystem.pushBallCommand)
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue(TransferSystem.tripleBallCommand)
        Gamepads.gamepad1.dpadUp
            .whenBecomesTrue {  }
    }
    override fun onUpdate() {
        ShooterSystem.updateAll()
    }
}