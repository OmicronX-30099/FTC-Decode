package org.firstinspires.ftc.teamcode.Finals.UglySystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Finals.UglySystems.IntakeSystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Finals.UglySystems.IntakeSystems.IntakeSubsystem

object IntakeSystem: SubsystemGroup(IntakeGateSubsystem, IntakeSubsystem) {
    val disengageIntake: Command
        get() = SequentialGroup(
            IntakeGateSubsystem.closeGateCommand,
            IntakeSubsystem.intakeCommand(0.0)
        )

    val openGateCommand: Command = IntakeGateSubsystem.openGateCommand

    override fun initialize() {
    }
}
