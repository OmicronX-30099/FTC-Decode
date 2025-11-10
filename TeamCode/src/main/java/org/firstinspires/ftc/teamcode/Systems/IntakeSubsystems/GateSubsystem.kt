package org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object GateSubsystem: Subsystem {
    val gateServo: ServoEx = ServoEx("gate")

    val openCommand = SetPosition(gateServo, 0.7).requires(this)
    val closeCommand = SetPosition(gateServo, 0.25).requires(this)
}