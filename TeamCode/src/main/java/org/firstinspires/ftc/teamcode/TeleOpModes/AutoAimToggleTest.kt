package org.firstinspires.ftc.teamcode.TeleOpModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Systems.IntakeSystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSystem

@TeleOp(name="Auto Aim Test")
class AutoAimToggleTest: NextFTCOpMode() {
    init {
        addComponents(
            SubsystemComponent(IntakeSystem, ShooterSystem),
            BindingsComponent,
            BulkReadComponent
        )
    }

    override fun onInit() {
        telemetry.addLine("This OpMode is meant to test the auto aim toggling feature. Run it and press the circle to test. Look at telemetry to see if auto aim status has changed")
        telemetry.update()
    }
    override fun onStartButtonPressed() {
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue { ShooterSystem.autoAim(); telemetry.addData("Gamepad", "Left Bumper was just pressed") }
    }

    override fun onUpdate() {
        telemetry.update()
    }
}