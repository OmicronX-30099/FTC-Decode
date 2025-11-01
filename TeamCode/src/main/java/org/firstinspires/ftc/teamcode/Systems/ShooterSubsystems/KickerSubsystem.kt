package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object KickerSubsystem : Subsystem {
    val kickerServo: ServoEx = ServoEx("k")

    val launch: Command = SetPosition(kickerServo, 0.25).requires(kickerServo)
    val reset: Command = SetPosition(kickerServo, 0.0).requires(kickerServo)

    override fun initialize() {
        kickerServo.position = 0.0;
    }
}