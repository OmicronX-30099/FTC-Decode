package org.firstinspires.ftc.teamcode.Master.System.TransferSubsystem

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

// Subsystem singleton to control kicker servo that kicks the ball
object KickerSubsystem: Subsystem {
    // Definition of kicker servo
    val kickerServo: ServoEx = ServoEx("kick",-0.1)

    // Pre-defined final values for servo positions
    val engagedPos: Double = 0.0
    val disengagedPos: Double = 0.0

    // Commands to engage and disengage kicker
    val engageKickerCommand: Command = SetPosition(kickerServo, engagedPos).requires(this)
    val disengageKickerCommand: Command = SetPosition(kickerServo, disengagedPos).requires(this)

    // Initialization function
    override fun initialize() {
        // Initializes kicker as disengaged
        kickerServo.position = disengagedPos
    }
}