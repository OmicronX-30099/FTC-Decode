package org.firstinspires.ftc.teamcode.Tests.TurretTesting

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

@Configurable
object RGBSubsystem: Subsystem {
    val rgbLight: ServoEx = ServoEx("rgbl")

    var looper: Double = 0.0

    override fun initialize() {
        rgbLight.position = 0.5
    }
    override fun periodic() {
        rgbLight.position += 0.005
    }
}