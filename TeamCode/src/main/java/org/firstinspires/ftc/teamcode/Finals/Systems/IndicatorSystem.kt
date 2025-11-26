package org.firstinspires.ftc.teamcode.Finals.Systems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Finals.Util.RGB

object IndicatorSystem: Subsystem {
    val rgbIndicator: ServoEx = ServoEx("RGBLight1")

    var targetColor: RGB = RGB.OFF

    override fun periodic() {
        rgbIndicator.position = targetColor.position
    }
}