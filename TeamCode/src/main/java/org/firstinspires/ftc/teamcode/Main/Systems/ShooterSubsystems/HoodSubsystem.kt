package org.firstinspires.ftc.teamcode.Main.Systems.ShooterSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object HoodSubsystem: Subsystem {
    val hoodServo: ServoEx = ServoEx("hood")
    val closeRangePosition: Double = 0.0
    val midRangePosition: Double = 0.0
    val farRangePosition: Double = 0.0

    var currentHoodPosition: Double = 0.0
    var hoodAutoAim: Boolean = false

    fun hoodAutoAimToggle() {
        hoodAutoAim = !hoodAutoAim
    }

    fun setHoodPosition(hoodPos: Double) {
        if (!hoodAutoAim) {
            return
        }
        when (hoodPos) {
            closeRangePosition -> {hoodServo.position = closeRangePosition
                                   currentHoodPosition = closeRangePosition}
            midRangePosition ->   {hoodServo.position = midRangePosition
                                   currentHoodPosition = midRangePosition}
            farRangePosition ->   {hoodServo.position = farRangePosition
                                   currentHoodPosition = farRangePosition}
            else ->               {error("You stupid little dum dum, u gotta use one of the 3 positions")}
        }
    }

    override fun initialize() {
        hoodServo.position = 0.0
    }
}