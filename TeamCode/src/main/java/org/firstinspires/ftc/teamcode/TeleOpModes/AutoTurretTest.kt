package org.firstinspires.ftc.teamcode.TeleOpModes

import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.extensions.pedro.PedroComponent
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.driving.MecanumDriverControlled
import dev.nextftc.hardware.impl.MotorEx
import org.firstinspires.ftc.teamcode.Constants.Constants
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

class AutoTurretTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(ShooterSystem),
            PedroComponent(Constants::createFollower),
            BulkReadComponent,
            BindingsComponent
        )
    }

    private val fl = MotorEx("fl")
    private val fr = MotorEx("fr")
    private val bl = MotorEx("bl")
    private val br = MotorEx("br")

    override fun onStartButtonPressed() {
        val drivetrain = MecanumDriverControlled(
            fl,
            fr,
            bl,
            br,
            -Gamepads.gamepad1.leftStickY,
            Gamepads.gamepad1.leftStickX,
            Gamepads.gamepad1.rightStickX
        )
        drivetrain.scalar = 0.5
        drivetrain()
    }
    override fun onUpdate() {
        ShooterSystem.autoShooter(follower.pose)
    }
}