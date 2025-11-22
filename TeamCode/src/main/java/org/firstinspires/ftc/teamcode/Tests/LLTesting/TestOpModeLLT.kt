package org.firstinspires.ftc.teamcode.Tests.LLTesting

import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Draft1.System.ShooterSubsystems.TurretSubsystemLLT

class TestOpModeLLT: NextFTCOpMode() {
    init {
        addComponents(
            BulkReadComponent,
            BindingsComponent,
            SubsystemComponent(LimelightSubsystemLLT, TurretSubsystemLLT)
        )
    }

    override fun onStartButtonPressed() {
        LimelightSubsystemLLT.startLL()
    }

    override fun onUpdate() {
        var turretState = TurretSubsystemLLT.getTurretState()
        var turretTarget = LimelightSubsystemLLT.calculateTurretAngle(turretState.currentTurretPosition)
        TurretSubsystemLLT.setTurretPosition(turretTarget)
    }
}