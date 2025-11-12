package org.firstinspires.ftc.teamcode.TeleOpModes

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.extensions.pedro.PedroDriverControlled
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.DriverControlledCommand
import kotlinx.coroutines.Delay
import org.firstinspires.ftc.teamcode.Constants
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.GateSubsystem
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.IntakeSubsystem
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

@TeleOp(name="Auto Aim Test")
class AutoAimToggleTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(IntakeSystem, ShooterSystem),
            BindingsComponent,
            BulkReadComponent,
            PedroComponent(Constants::createFollower)
        )
    }

    lateinit var drivetrain: DriverControlledCommand;

    override fun onInit() {
        telemetry.addLine("This OpMode is meant to test the auto aim toggling feature. Run it and press the circle to test. Look at telemetry to see if auto aim status has changed")
        telemetry.update()
    }
    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(0.0, 0.0, 0.0))
        Gamepads.gamepad1.circle
            .whenBecomesTrue { ShooterSystem.autoAim()}
        Gamepads.gamepad1.rightBumper
            .toggleOnBecomesTrue()
            .whenBecomesTrue(GateSubsystem.openGate)
            .whenBecomesFalse(GateSubsystem.closeGate)
        Gamepads.gamepad1.rightTrigger.greaterThan(0.0).or(Gamepads.gamepad1.leftTrigger.greaterThan(0.0))
            .whenBecomesTrue(GateSubsystem.openGate)
            .whenTrue{ IntakeSubsystem.intakeMotor.power = (Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get())}
            .whenBecomesFalse(GateSubsystem.closeGate.and(IntakeSubsystem.intake(0.0)))
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue(SequentialGroup(KickerSubsystem.engageKicker, dev.nextftc.core.commands.delays.Delay(0.15),
                KickerSubsystem.disengageKicker))
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX,
            true,
        )
        drivetrain.schedule()
        Gamepads.gamepad1.dpadUp
            .toggleOnBecomesTrue()
            .whenBecomesTrue {drivetrain.scalar = 0.2}
            .whenBecomesFalse { drivetrain.scalar = 1.0 }
    }

    override fun onUpdate() {
        telemetry.update()
        ShooterSystem.FullTurretAim(follower.pose)
    }
}