package org.firstinspires.ftc.teamcode.Tests.ShooterTuning

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

@Configurable
object FlywheelSubsystem: Subsystem {
    val fwl: MotorEx = MotorEx("leftFlywheelMotor")
    val fwr: MotorEx = MotorEx("rightFlywheelMotor")
    val fwm: MotorGroup = MotorGroup(fwr,fwl)

    @JvmField
    var goal: Double = 0.0;

    @JvmField
    var pid: PIDCoefficients = PIDCoefficients(0.01,0.0,0.0)

    @JvmField
    var ff: BasicFeedforwardParameters = BasicFeedforwardParameters(0.00031,0.0,0.063)

    var flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(pid)
        .basicFF(ff)
        .build()

    override fun periodic() {
        flywheelControl.goal = KineticState(0.0,goal,0.0)
        fwm.power = flywheelControl.calculate(fwm.state)
    }
}