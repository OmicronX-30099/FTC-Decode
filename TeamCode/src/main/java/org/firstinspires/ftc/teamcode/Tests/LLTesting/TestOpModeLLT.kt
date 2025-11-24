package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Draft1.System.ShooterSubsystems.TurretSubsystemLLT

@TeleOp(name="LL Test")
class  TestOpModeLLT: NextFTCOpMode() {
    init {
        addComponents(
            BulkReadComponent,
            BindingsComponent,
            SubsystemComponent(LimelightSubsystemLLT, TurretSubsystemLLT)
        )
    }

    override fun onInit() {
        telemetry.addData("Init", "Check")
        telemetry.update()
        LimelightSubsystemLLT.startLL()
    }

    override fun onStartButtonPressed() {
        telemetry.addData("Starting", "limelight")
    }

    override fun onUpdate() {
        var turretState = TurretSubsystemLLT.getTurretState()
        var turretTarget = LimelightSubsystemLLT.calculateTurretAngle(turretState.currentTurretPosition)
        telemetry.addData("Turret target", turretTarget)
        TurretSubsystemLLT.setTurretPosition(turretTarget)
        telemetry.update()
    }
}
