package org.firstinspires.ftc.teamcode.Resources.ShooterTuning

import com.bylazar.telemetry.PanelsTelemetry
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.impl.MotorEx

@TeleOp
class OpMode: NextFTCOpMode() {
    val panelsTelemetry = PanelsTelemetry.telemetry
    val intake = MotorEx("intakeMotor")

    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(HoodSubsystem, FlywheelSubsystem)
        )
    }

    override fun onStartButtonPressed() {
        intake.power = 1.0
    }

    override fun onUpdate() {
        panelsTelemetry.addData("Flywheel Goal", FlywheelSubsystem.goal)
        panelsTelemetry.addData("Flywheel Live vel", FlywheelSubsystem.fwm.velocity)
        panelsTelemetry.addData("HoodPose", HoodSubsystem.hoodPos)
        panelsTelemetry.update()
    }
}