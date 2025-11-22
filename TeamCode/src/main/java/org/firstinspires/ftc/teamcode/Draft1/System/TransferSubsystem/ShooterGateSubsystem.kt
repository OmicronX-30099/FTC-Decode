package org.firstinspires.ftc.teamcode.Draft1.System.TransferSubsystem

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

// Subsystem singleton to control shooter gate servo
object ShooterGateSubsystem: Subsystem {
    // Declaration of gate servo
    val shooterGateServo: ServoEx = ServoEx("s_gate",-0.1)

    // Definition of servo position
    val openPos: Double = 0.0
    val closePos: Double = 0.0

    // Commands for opening and closing gate
    val openShooterGateCommand: Command = SetPosition(shooterGateServo, openPos).requires(this)
    val closeShooterGateCommand: Command = SetPosition(shooterGateServo, closePos).requires(this)

    // Function for initializing shooter gate
    override fun initialize() {
        // Initialized the shooter gate to be closed
        shooterGateServo.position = closePos
    }
}