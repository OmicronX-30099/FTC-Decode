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
    lateinit var ll: Limelight3A

    override fun onInit() {
        ll = ActiveOpMode.hardwareMap.get(Limelight3A::class.java, "Limelight")
        ll.pipelineSwitch(1)
        ll.start()
    }

    override fun onUpdate() {
        var result = ll.latestResult
        if (result.isValid && result != null)
            telemetry.addData("tx", ll.latestResult.tx)
            telemetry.update()
    }
}