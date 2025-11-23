package org.firstinspires.ftc.teamcode.Draft2.System.ShooterSubsystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

// Subsystem to manage Hood
object HoodSubsystem: Subsystem {
    // Definition of hardware
    val hoodServo: ServoEx = ServoEx("hoodServo")

    // Variables to track hood autoAim and hood position
    var hoodAutoAim: Boolean = false
    var currentHoodPosition: Double = 0.3

    fun setHoodPosition(hoodGoal: Double) {
        if (hoodAutoAim) {
            hoodServo.position = hoodGoal
            currentHoodPosition = hoodGoal
        }
    }

    // Initialization function
    override fun initialize() {
        // Initializes servo to 0.3
        hoodServo.position = 0.3
    }
}