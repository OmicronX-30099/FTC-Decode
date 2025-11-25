package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.Controllable
import dev.nextftc.hardware.impl.MotorEx

@Configurable
object Testsubsys: Subsystem {
    val turretMotor: MotorEx = MotorEx("turret")

    @JvmField
    var turretPID = PIDCoefficients(0.02,0.0,0.001)

    @JvmField
    var turretFF = BasicFeedforwardParameters(0.0,0.0,0.0)

    var turretControl = ControlSystem.builder()
        .posPid(turretPID)
        .basicFF(turretFF)
        .build()

    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}