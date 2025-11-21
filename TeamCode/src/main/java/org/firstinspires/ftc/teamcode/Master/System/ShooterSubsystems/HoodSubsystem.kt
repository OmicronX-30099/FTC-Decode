package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Util.HoodState
import kotlin.properties.Delegates

// Subsystem to control servo-controlled hood
object HoodSubsystem: Subsystem {
    // Declaration of hardware
    val hoodServo: ServoEx = ServoEx("hood",-0.1)

    // Variables to track hood status
    var currentHoodPos: Double by Delegates.notNull()
    var hoodAutoAim: Boolean = false

    // Function to toggle auto aiming on and off
    fun hoodAutoAimToggle() {
        // Toggles hood on and off
        hoodAutoAim = !hoodAutoAim
    }
    // Function to set hood position
    fun setHoodPosition(hoodPos: Double) {
        // Enforces auto aim checking here
        if (hoodAutoAim) {
            hoodServo.position = hoodPos
            currentHoodPos = hoodPos
        }
    }
    // Function to get current hood status
    fun getHoodState(): HoodState {
        return HoodState(currentHoodPos, hoodAutoAim)
    }

    // Function to initialize servo to 0.5 position
    override fun initialize() {
        hoodServo.position = 0.5
    }
}