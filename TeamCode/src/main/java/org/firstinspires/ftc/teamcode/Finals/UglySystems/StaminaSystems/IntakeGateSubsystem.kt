package org.firstinspires.ftc.teamcode.Finals.UglySystems.StaminaSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPositions

object IntakeGateSubsystem: Subsystem {
    val leftGateServo: ServoEx = ServoEx("leftIntakeGateServo",-0.1)
    val rightGateServo: ServoEx = ServoEx("rightIntakeGateServo",-0.1)

    var leftGateOpenPosition: Double = 0.0
    var leftGateClosePosition: Double = 0.5
    var rightGateOpenPosition: Double = 1.0
    var rightGateClosePosition: Double = 0.5

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
