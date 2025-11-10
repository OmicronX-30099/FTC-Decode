package org.firstinspires.ftc.teamcode.Systems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.ParallelGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.GateSubsystem
import org.firstinspires.ftc.teamcode.Systems.IntakeSubsystems.IntakeSubsystem

object IntakeSystem: SubsystemGroup(IntakeSubsystem, GateSubsystem) {
    fun intake(intakePower: Double): Command {
        return ParallelGroup(
            GateSubsystem.openCommand,
            intake(intakePower)
        ).requires(this)
    }
}