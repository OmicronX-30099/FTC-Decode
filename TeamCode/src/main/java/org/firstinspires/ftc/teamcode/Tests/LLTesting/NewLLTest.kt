package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent

@TeleOp(name="new thingy pls work")
class NewLLTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
        )
    }
    var ll: Limelight3A = ActiveOpMode.hardwareMap.get(Limelight3A::class.java, "Limelight")

    override fun onInit() {
        ll.pipelineSwitch(1)
        ll.start()
    }

    override fun onUpdate() {
        telemetry.addData("tx", ll.latestResult.tx)
        telemetry.update()
    }
}