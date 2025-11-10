package org.firstinspires.ftc.teamcode.TeleOpModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

@TeleOp(name="Auto Aim Test")
class AutoAimToggleTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(IntakeSystem),
            SubsystemComponent(ShooterSystem)
        )
    }

    override fun onInit() {
        telemetry.addLine("This OpMode is meant to test the auto aim toggling feature. Run it and press the circle to test. Look at telemetry to see if auto aim status has changed")
    }
    override fun onStartButtonPressed() {
        Gamepads.gamepad1.circle
            .whenBecomesTrue { ShooterSystem.autoAim() }
    }

    override fun onUpdate() {
        telemetry.update()
    }
}