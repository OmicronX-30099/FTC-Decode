package org.firstinspires.ftc.teamcode.Resources

import com.acmerobotics.dashboard.config.Config
import dev.nextftc.control.KineticState
import dev.nextftc.control.builder.controlSystem
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx

@Config
class FlywheelTuner: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent
        )
    }

    companion object Constants {
        @JvmField
        val pidCoefficients = PIDCoefficients(0.0,0.0, 0.0)
        @JvmField
        val ffCoefficients = BasicFeedforwardParameters(0.0,0.0,0.0)
        @JvmField
        val vel = 0.0;
    }

    private val fwl = MotorEx("fwl").reversed()
    private val fwr = MotorEx("fwr")
    private val fwm = MotorGroup(fwr, fwl)

    val flywheelControl = controlSystem {
        velPid(pidCoefficients)
        basicFF(ffCoefficients)
    }

    override fun onUpdate() {
        flywheelControl.goal = KineticState(0.0, vel);
        fwm.power = flywheelControl.calculate(fwm.state)
    }
}