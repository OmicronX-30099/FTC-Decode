package org.firstinspires.ftc.teamcode.Finals.UglySystems.TransferSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object KickerSubsystem: Subsystem {
    val kickerServo: ServoEx = ServoEx("kickerServo",-0.1)

    var kickBallPosition: Double = 0.2
    var kickerResetPosition: Double = 0.02

    val kickBallCommand: Command = SetPosition(kickerServo,kickBallPosition).requires(this)
    val resetKickerCommand: Command = SetPosition(kickerServo,kickerResetPosition).requires(this)

    override fun initialize() {
        kickerServo.position = kickerResetPosition
    }
}