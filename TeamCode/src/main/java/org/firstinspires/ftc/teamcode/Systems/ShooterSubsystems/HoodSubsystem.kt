package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object HoodSubsystem : Subsystem {
    val hoodServo: ServoEx = ServoEx("hood",-0.1)

    val lowMode: Command = SetPosition(hoodServo, 0.0).requires(this)
    val mediumMode: Command = SetPosition(hoodServo, 0.4).requires(this)
    val highMode: Command = SetPosition(hoodServo, 1.0).requires(this)

    override fun initialize() {
        hoodServo.position = 0.0
    }
}