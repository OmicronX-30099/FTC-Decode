package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Util.HoodState
import kotlin.properties.Delegates

object HoodSubsystem: Subsystem {
    val hoodServo: ServoEx = ServoEx("hood")

    var currentHoodPos: Double by Delegates.notNull()
    var hoodAutoAim: Boolean = false

    fun hoodAutoAimToggle() {
        hoodAutoAim = !hoodAutoAim
    }
    fun setHoodPosition(hoodPos: Double) {
        if (hoodAutoAim) {
            hoodServo.position = hoodPos
            currentHoodPos = hoodPos
        }
    }
    fun getHoodState(): HoodState {
        return HoodState(currentHoodPos, hoodAutoAim)
    }

    override fun initialize() {
        hoodServo.position = 0.5
    }
}