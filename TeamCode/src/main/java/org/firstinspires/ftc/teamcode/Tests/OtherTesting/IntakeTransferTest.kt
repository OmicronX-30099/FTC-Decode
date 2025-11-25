import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import dev.nextftc.core.components.BindingsComponent
import dev.nextftc.core.components.SubsystemComponent
import dev.nextftc.ftc.Gamepads
import dev.nextftc.ftc.NextFTCOpMode
import dev.nextftc.ftc.components.BulkReadComponent
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.IntakeSystem
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.IntakeSystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystem
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystems.ShooterGateSubsystem

@TeleOp(name="Intake Test")
class IntakeTransferTest: NextFTCOpMode() {
    init {
        addComponents(
            BindingsComponent,
            BulkReadComponent,
            SubsystemComponent(IntakeSystem, TransferSystem)
        )
    }
    override fun onInit() {
        telemetry.update()
    }
    override fun onStartButtonPressed() {
        Gamepads.gamepad1.rightTrigger.greaterThan(0.0).or(Gamepads.gamepad1.leftTrigger.greaterThan(0.0))
            .whenBecomesTrue(IntakeSystem.openGateCommand)
            .whenTrue(IntakeSystem.engageVariableIntake(Gamepads.gamepad1.rightTrigger.get() - Gamepads.gamepad1.leftTrigger.get()))
            .whenBecomesFalse(IntakeSystem.disengageIntake)
        Gamepads.gamepad1.rightBumper
            .whenBecomesTrue(TransferSystem.transferBallCommand)
        Gamepads.gamepad1.leftBumper
            .whenBecomesTrue(TransferSystem.tripleShootSequence)

        // Gamepad 2 manuals
        Gamepads.gamepad2.triangle
            .toggleOnBecomesTrue()
            .whenBecomesTrue(IntakeGateSubsystem.openGateCommand)
            .whenBecomesFalse(IntakeGateSubsystem.closeGateCommand)
        Gamepads.gamepad2.cross
            .toggleOnBecomesTrue()
            .whenBecomesTrue(KickerSubsystem.kickBallCommand)
            .whenBecomesFalse(KickerSubsystem.resetKickerCommand)
        Gamepads.gamepad2.circle
            .toggleOnBecomesTrue()
            .whenBecomesTrue(ShooterGateSubsystem.gateReleaseCommand)
            .whenBecomesFalse(ShooterGateSubsystem.gateBlockCommand)
    }
}
