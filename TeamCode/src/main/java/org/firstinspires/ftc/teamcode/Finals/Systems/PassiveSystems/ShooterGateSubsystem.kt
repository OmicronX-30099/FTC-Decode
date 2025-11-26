package org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object ShooterGateSubsystem: Subsystem {
    val shooterGateServo: ServoEx = ServoEx("shooterGateServo",-0.1)

    var gateBlockingPosition: Double = 0.2
    var gateReleasedPosition: Double = 0.3

    fun blockGate() { shooterGateServo.position = gateBlockingPosition }
    fun releaseGate() { shooterGateServo.position = gateReleasedPosition }

    override fun initialize() {
        shooterGateServo.position = gateBlockingPosition
    }
}
