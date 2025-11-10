package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object KickerSubsystem : Subsystem {
    val kickServo: ServoEx = ServoEx("k")

    val restMode: Command = SetPosition(kickServo, 0.0)
    val engagedMode: Command = SetPosition(kickServo, 0.25)

    val kick: Command = SequentialGroup(
        engagedMode,
        Delay(0.15),
        restMode
    ).requires(this)
}