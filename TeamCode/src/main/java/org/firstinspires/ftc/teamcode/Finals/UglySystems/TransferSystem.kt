package org.firstinspires.ftc.teamcode.Finals.UglySystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Finals.UglySystems.TransferSystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Finals.UglySystems.TransferSystems.ShooterGateSubsystem

object TransferSystem: SubsystemGroup(KickerSubsystem, ShooterGateSubsystem) {
    val transferBallCommand: Command
        get() = SequentialGroup(
            ShooterGateSubsystem.gateReleaseCommand,
            KickerSubsystem.kickBallCommand,
            Delay(0.15),
            KickerSubsystem.resetKickerCommand,
            ShooterGateSubsystem.gateBlockCommand,
            Delay(0.15)
        )
    
    val tripleShootSequence: Command
        get() = SequentialGroup(
            transferBallCommand,
            Delay(0.3),
            transferBallCommand,
            Delay(0.3),
            transferBallCommand
        )

    override fun initialize() {
    }
}
