package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.bylazar.configurables.annotations.Configurable
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.control.feedback.PIDCoefficients
import dev.nextftc.control.feedforward.BasicFeedforwardParameters
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.impl.MotorEx

@Configurable
@TeleOp(name="new thingy pls work")
class NewLLTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
        )
    }
    lateinit var ll: Limelight3A

    lateinit var elapsedtime: ElapsedTime

    @JvmField
    var turretPID = PIDCoefficients(0.0005,0.0,0.0)

    @JvmField
    var turretFF = BasicFeedforwardParameters(0.0,0.0,0.0)

    var turretControl: ControlSystem = ControlSystem.builder()
        .posPid(turretPID)
        .basicFF(turretFF)
        .build()

    var turretMotor: MotorEx = MotorEx("turret")

    override fun onInit() {
        ll = ActiveOpMode.hardwareMap.get(Limelight3A::class.java, "Limelight")
        ll.pipelineSwitch(1)
        ll.start()
        elapsedtime = ElapsedTime()
        elapsedtime.reset()

    }

    override fun onUpdate() {
        var result = ll.latestResult
        if (result.isValid && result != null) {
                telemetry.addData("tx", result.tx)
                var ticks = ((result.tx)/360) * (100/24) * -1 * 384.5
                telemetry.addData("ticks calculation", ticks)
                telemetry.addData("compiled", ticks + turretMotor.currentPosition)
                turretControl.goal = KineticState(ticks + turretMotor.currentPosition)
        }
        turretMotor.power = turretControl.calculate(turretMotor.state)
        telemetry.addData("Loop Times", elapsedtime.milliseconds());
        elapsedtime.reset();
        telemetry.update()
    }
}