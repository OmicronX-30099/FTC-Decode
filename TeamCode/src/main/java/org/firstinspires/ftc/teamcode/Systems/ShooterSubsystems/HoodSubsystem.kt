package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object HoodSubsystem : Subsystem {
    val hoodServo: ServoEx = ServoEx("hood")

    val lowMode: Command = SetPosition(hoodServo, 0.0)
    val mediumMode: Command = SetPosition(hoodServo, 0.4)
    val highMode: Command = SetPosition(hoodServo, 1.0)

    override fun initialize() {
        hoodServo.position = 0.0
    }
}