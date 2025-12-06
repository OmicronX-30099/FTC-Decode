package org.firstinspires.ftc.teamcode.Finals.Systems.PassiveSystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object IntakeGateSubsystem: Subsystem {
    val leftGateServo: ServoEx = ServoEx("leftIntakeGateServo",-0.1)
    val rightGateServo: ServoEx = ServoEx("rightIntakeGateServo",-0.1)

    var leftGateOpenPosition: Double = 0.5
    var leftGateClosePosition: Double = 0.725
    var rightGateOpenPosition: Double = 0.86
    var rightGateClosePosition: Double = 0.425

    fun openGate() {
        leftGateServo.position = leftGateOpenPosition
        rightGateServo.position = rightGateOpenPosition
    }
    fun closeGate() {
        leftGateServo.position = leftGateClosePosition
        rightGateServo.position = rightGateClosePosition
    }

    override fun initialize() {
        leftGateServo.position = leftGateClosePosition
        rightGateServo.position = rightGateClosePosition
    }
}
