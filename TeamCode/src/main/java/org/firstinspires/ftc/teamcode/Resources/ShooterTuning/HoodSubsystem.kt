package org.firstinspires.ftc.teamcode.Resources.ShooterTuning

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

@Configurable
object HoodSubsystem: Subsystem {
    val hoodServo: ServoEx = ServoEx("hoodServo",-0.1)

    @JvmField
    var hoodPos: Double = 0.0;

    override fun periodic() {
        hoodServo.position = hoodPos
    }
}