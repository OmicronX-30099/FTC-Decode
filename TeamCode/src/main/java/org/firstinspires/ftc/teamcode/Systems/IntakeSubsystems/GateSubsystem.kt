package org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object GateSubsystem : Subsystem {
    val gateServo: ServoEx = ServoEx("gate")

    val open: Command = SetPosition(gateServo, 0.25).requires(gateServo)
    val close: Command = SetPosition(gateServo, 0.0).requires(gateServo)

    override fun initialize() {
        gateServo.position = 0.0
    }
}