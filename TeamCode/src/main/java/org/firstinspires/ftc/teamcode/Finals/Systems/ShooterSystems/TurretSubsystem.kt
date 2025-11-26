package org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystems

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

@Configurable
object TurretSubsystem: Subsystem {
    val turretMotor: MotorEx = MotorEx("turretMotor")

    @JvmField var turretPID = PIDCoefficients(0.0,0.0,0.0)
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(turretPID)
        .build()

    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}