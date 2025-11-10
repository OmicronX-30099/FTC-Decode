package org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object GateSubsystem: Subsystem {
    val gateServo: ServoEx = ServoEx("gate", -0.1)

    val openGate: Command = SetPosition(gateServo, 0.7).requires(this)
    val closeGate: Command = SetPosition(gateServo, 0.25).requires(this)

    override fun initialize() {
        gateServo.position = 0.25
    }
}