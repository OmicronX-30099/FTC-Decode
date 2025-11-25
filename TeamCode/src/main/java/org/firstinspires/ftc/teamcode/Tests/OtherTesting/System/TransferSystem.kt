package org.firstinspires.ftc.teamcode.Tests.OtherTesting.System

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystems.ShooterGateSubsystem

object TransferSystem: SubsystemGroup(KickerSubsystem, ShooterGateSubsystem) {
    val transferBallCommand: Command
        get() = SequentialGroup(
            ShooterGateSubsystem.gateReleaseCommand,
            KickerSubsystem.kickBallCommand,
            Delay(0.2),
            KickerSubsystem.resetKickerCommand,
            ShooterGateSubsystem.gateBlockCommand
        )

    override fun initialize() {
        ActiveOpMode.telemetry.addData("System", "TransferSystem initialized")
    }
}