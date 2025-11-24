package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import dev.nextftc.hardware.impl.MotorEx

@TeleOp(name="new thingy pls work")
class NewLLTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
        )
    }
    lateinit var ll: Limelight3A
    var turretMotor: MotorEx = MotorEx("tur")

    override fun onInit() {
        ll = ActiveOpMode.hardwareMap.get(Limelight3A::class.java, "Limelight")
        ll.pipelineSwitch(1)
        ll.start()
    }

    override fun onUpdate() {
        var result = ll.latestResult
        if (result.isValid && result != null) {
                telemetry.addData("tx", result.tx)
                var ticks = ((result.tx)/360) * (100/24) * -1 * 384.5
                telemetry.addData("ticks calculation", ticks)
                telemetry.addData("compiled", ticks + turretMotor.currentPosition)
                telemetry.update()
        }
    }
}