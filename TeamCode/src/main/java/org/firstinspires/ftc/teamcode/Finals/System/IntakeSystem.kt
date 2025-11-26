package org.firstinspires.ftc.teamcode.Finals.System

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Finals.System.IntakeSystems.IntakeGateSubsystem
import org.firstinspires.ftc.teamcode.Finals.System.IntakeSystems.IntakeSubsystem

object IntakeSystem: SubsystemGroup(IntakeGateSubsystem, IntakeSubsystem) {
    val disengageIntake: Command
        get() = SequentialGroup(
            IntakeGateSubsystem.closeGateCommand,
            IntakeSubsystem.intakeCommand(0.0)
        )

    val openGateCommand: Command = IntakeGateSubsystem.openGateCommand

    override fun initialize() {
        ActiveOpMode.telemetry.addData("System", "IntakeSystem initialized")
    }
}
