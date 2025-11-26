package org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object HoodSubsystem: Subsystem {
    val hoodServo: ServoEx = ServoEx("hoodServo")

    override fun initialize() {
        hoodServo.position = 0.5
    }
}