package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object KickerSubsystem: Subsystem {
    val kickerServo: ServoEx = ServoEx("k",-0.1)

    val engageKicker: Command = SetPosition(kickerServo, 0.25).requires(this)
    val disengageKicker: Command = SetPosition(kickerServo, 0.0).requires(this)

    override fun initialize() {
        kickerServo.position = 0.0
    }
}