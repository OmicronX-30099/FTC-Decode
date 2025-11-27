package org.firstinspires.ftc.teamcode.Finals.TeleOpModes

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.conditionals.SwitchCommand
import dev.nextftc.core.commands.conditionals.switchCommand
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.extensions.pedro.PedroDriverControlled
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.ftc.components.LoopTimeComponent
import dev.nextftc.hardware.driving.DriverControlledCommand
import org.firstinspires.ftc.teamcode.Finals.Constants
import org.firstinspires.ftc.teamcode.Finals.Systems.*
import org.firstinspires.ftc.teamcode.Finals.Util.Alliance

@TeleOp(name="Blue TeleOp V1")
class BlueTeleOpV1: NextFTCOpMode() {
    init {
        addComponents(
            BulkReadComponent,
            BindingsComponent,
            LoopTimeComponent(),
            SubsystemComponent(PassiveSystem,ShooterSystem,IndicatorSystem),
            PedroComponent(Constants::createFollower)
        )
    }

    lateinit var drivetrain: DriverControlledCommand
    lateinit var alliance: Alliance

    override fun onInit() {
        follower.setStartingPose(Pose(27.3125+8.7,141-8.9-1,Math.toRadians(90.0)))
        this.alliance = Alliance.BLUE
        ShooterSystem.setAlliance(this.alliance)
    }
    override fun onStartButtonPressed() {
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX
        )
        drivetrain.scalar = 1.0
        drivetrain.schedule()
        Gamepads.gamepad1.rightBumper
            .whenBecomesTrue(PassiveSystem.pushBallCommand)
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue(PassiveSystem.checkedTripleShootSequence())
        Gamepads.gamepad1.triangle
            .whenBecomesTrue(PassiveSystem.altTripleShootSequence)
        Gamepads.gamepad1.rightTrigger.greaterThan(0.0).or(Gamepads.gamepad1.leftTrigger.greaterThan(0.0))
            .whenBecomesTrue(PassiveSystem.openGateCommand)
            .whenTrue{ PassiveSystem.intake(Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get()) }
            .whenBecomesFalse(PassiveSystem.stopIntakeCommand)
        Gamepads.gamepad1.cross
            .toggleOnBecomesTrue()
            .whenBecomesTrue { ShooterSystem.autoAimOn() }
            .whenBecomesFalse { ShooterSystem.autoAimOff() }
        Gamepads.gamepad1.rightStickButton
            .whenBecomesTrue(drivetrainSpeedControl())
        Gamepads.gamepad1.dpadLeft.or(Gamepads.gamepad2.dpadLeft)
            .whenBecomesTrue { follower.setStartingPose(this.alliance.resetPose1) }
        Gamepads.gamepad1.dpadDown.or(Gamepads.gamepad2.dpadDown)
            .whenBecomesTrue { follower.setStartingPose(this.alliance.resetPose2) }
        Gamepads.gamepad1.dpadRight.or(Gamepads.gamepad2.dpadRight)
            .whenBecomesTrue { follower.setStartingPose(this.alliance.resetPose3) }
    }
    fun drivetrainSpeedControl(): Command {
        when (drivetrain.scalar) {
            0.2 -> {return InstantCommand { drivetrain.scalar = 1.0 }}
            0.5 -> {return InstantCommand { drivetrain.scalar = 0.2 }}
            1.0 -> {return InstantCommand { drivetrain.scalar = 0.5 }}
            else -> {return InstantCommand { error("skib, u gay") }}
        }
    }

    override fun onUpdate() {
        ActiveOpMode.telemetry.addData("Follower", "x = "+follower.pose.x.toString())
        ActiveOpMode.telemetry.addData("Follower", "y = "+follower.pose.y.toString())
        ActiveOpMode.telemetry.addData("Follower", "heading = "+follower.pose.heading.toString())
        telemetry.update()
    }
}