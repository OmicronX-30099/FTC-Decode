package org.firstinspires.ftc.teamcode.TeleOpModes

import com.bylazar.opmodecontrol.ActiveOpMode
import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower;
import dev.nextftc.ftc.Gamepads
import org.firstinspires.ftc.robotcore.internal.hardware.android.GpioPin
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.GateSubsystem
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.IntakeSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem

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
        GateSubsystem.gateServo.position = 0.25
        HoodSubsystem.hoodServo.position = 0.0
    }

    override fun onStartButtonPressed() {
        follower.setStartingPose(Pose(96.0,120.0,0.0))
        GateSubsystem.gateServo.position = 0.25
        GateSubsystem.gateServo.position = 0.25
        Gamepads.gamepad1.rightBumper
            .toggleOnBecomesTrue()
            .whenBecomesTrue(GateSubsystem.openCommand)
            .whenBecomesFalse(GateSubsystem.closeCommand)
        Gamepads.gamepad1.rightTrigger.greaterThan(0.0).or(Gamepads.gamepad1.leftTrigger.greaterThan(0.0))
            .whenBecomesTrue(GateSubsystem.openCommand)
            .whenTrue{IntakeSubsystem.intakeMotor.power = (Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get())}
            .whenBecomesFalse(GateSubsystem.closeCommand.and(IntakeSubsystem.intake(0.0)))
    }


    override fun onUpdate() {
        ShooterSystem.calibrateHoodPosition(follower.pose)
        telemetry.addData("Hood Pos", HoodSubsystem.hoodServo.position)
        telemetry.addData("Flywheel Velocity: ", FlywheelSubsystem.flywheelMotors.velocity)
        telemetry.addData("X", follower.pose.x)
        telemetry.addData("y", follower.pose.y)
        telemetry.addData("h", follower.pose.heading)
        telemetry.update()
        ShooterSystem.calibrateFlywheelVelocity(follower.pose)
    }
}