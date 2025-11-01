package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems
import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object HoodSubsystem : Subsystem {
    val hoodServo: ServoEx = ServoEx("hood")

    fun setPos(pos: Double) {
        hoodServo.position = pos
    }
    override fun initialize() {
        hoodServo.position = 0.0
    }
}