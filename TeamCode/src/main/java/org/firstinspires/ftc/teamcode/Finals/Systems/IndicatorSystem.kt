package org.firstinspires.ftc.teamcode.Finals.Systems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Finals.Util.RGB
import kotlin.math.abs

object IndicatorSystem: Subsystem {
    val rgbIndicator: ServoEx = ServoEx("RGBLight1")

    override fun periodic() {
        if (ShooterSystem.fullAutoAim) {
            if (abs(FlywheelSubsystem.flywheeControl.goal.velocity - FlywheelSubsystem.flywheelMotors.velocity) <= 20.0) {
                rgbIndicator.position = RGB.GREEN.position
            } else {
                rgbIndicator.position = RGB.RED.position
            }
        } else {
            rgbIndicator.position = RGB.INDIGO.position
        }
    }
}