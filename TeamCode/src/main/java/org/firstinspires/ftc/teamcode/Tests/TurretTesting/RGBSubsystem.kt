package org.firstinspires.ftc.teamcode.Tests.TurretTesting

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object RGBSubsystem: Subsystem {
    val rgbLight: ServoEx = ServoEx("rgbl")

    var looper: Double = 0.0

    override fun initialize() {
        rgbLight.position = 1.0
    }
    override fun periodic() {
        if (looper >= 200.0) {
            rgbLight.position = Math.random()
            looper = 0.0
        } else {
            looper += 1
        }
    }
}