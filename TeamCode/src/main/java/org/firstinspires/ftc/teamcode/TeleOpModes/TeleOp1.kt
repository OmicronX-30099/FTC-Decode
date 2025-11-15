package org.firstinspires.ftc.teamcode.TeleOpModes

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
import org.firstinspires.ftc.teamcode.Constants
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

@TeleOp(name="Red Teleop", group = "TeleOpModes")
class TeleOp1: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(ShooterSystem, IntakeSystem),
            PedroComponent(Constants::createFollower)
        )
    }

    lateinit var drivetrain: DriverControlledCommand;

    override fun onInit() {
        follower.setStartingPose(Pose(84.0,50.0,0.0))
    }

    override fun onStartButtonPressed() {
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX,
            true
        )
        drivetrain.scalar = 0.9
        drivetrain.schedule()
        Gamepads.gamepad1.leftBumper
            .toggleOnBecomesTrue()
            .whenBecomesTrue { drivetrain.scalar = 0.45 }
            .whenBecomesFalse { drivetrain.scalar = 0.9 }
        Gamepads.gamepad1.dpadDown
            .toggleOnBecomesTrue()
            .whenBecomesTrue { drivetrain.scalar = 0.2 }
            .whenBecomesFalse { drivetrain.scalar = 0.9 }
        Gamepads.gamepad1.
    }

    override fun onUpdate() {
        ShooterSystem.calibrateHoodPosition(follower.pose)
        ShooterSystem.calibrateTurretPosition(follower.pose)
        ShooterSystem.calibrateFlywheelVelocity(follower.pose)
    }
}