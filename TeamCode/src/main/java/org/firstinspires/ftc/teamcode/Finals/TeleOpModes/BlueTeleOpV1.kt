package org.firstinspires.ftc.teamcode.Finals.TeleOpModes

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.commands.Command
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

    lateinit var drivetrain: Command

    override fun onInit() {
        follower.setStartingPose(Pose(23.5,70.5,Math.toRadians(90.0)))
        ShooterSystem.setAlliance(Alliance.BLUE)
    }
    override fun onStartButtonPressed() {
        drivetrain = PedroDriverControlled(
            -Gamepads.gamepad1.leftStickY,
            -Gamepads.gamepad1.leftStickX,
            -Gamepads.gamepad1.rightStickX
        )
        drivetrain.schedule()
        Gamepads.gamepad1.rightBumper
            .whenBecomesTrue(PassiveSystem.pushBallCommand)
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue(PassiveSystem.tripleShootSequence)
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
    }

    override fun onUpdate() {
        ActiveOpMode.telemetry.addData("Follower", "x = "+follower.pose.x.toString())
        ActiveOpMode.telemetry.addData("Follower", "y = "+follower.pose.y.toString())
        ActiveOpMode.telemetry.addData("Follower", "heading = "+follower.pose.heading.toString())
        telemetry.update()
    }
}