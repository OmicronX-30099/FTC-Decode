package org.firstinspires.ftc.teamcode.Tests.ShooterTests.ShooterSystems

import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx

object HoodSubsystem: Subsystem {
    val hoodServo: ServoEx = ServoEx("hoodServo")
}