package org.firstinspires.ftc.teamcode.Systems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.ParallelGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.GateSubsystem
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.IntakeSubsystem

object IntakeSystem: SubsystemGroup(GateSubsystem, IntakeSubsystem) {
    val startIntake: Command
        get() = ParallelGroup(
            GateSubsystem.openGate,
            IntakeSubsystem.intake(1.0)
        )
    val stopIntake: Command
        get() = ParallelGroup(
            IntakeSubsystem.intake(1.0),
            GateSubsystem.closeGate
        )
}