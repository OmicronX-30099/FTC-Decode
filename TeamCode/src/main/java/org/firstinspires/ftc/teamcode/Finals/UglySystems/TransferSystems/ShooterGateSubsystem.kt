package org.firstinspires.ftc.teamcode.Finals.UglySystems.TransferSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object ShooterGateSubsystem: Subsystem {
    val shooterGateServo: ServoEx = ServoEx("shooterGateServo",-0.1)

    var gateBlockingPosition: Double = 0.2
    var gateReleasedPosition: Double = 0.3

    val gateBlockCommand: Command = SetPosition(shooterGateServo,gateBlockingPosition).requires(this)
    val gateReleaseCommand: Command = SetPosition(shooterGateServo,gateReleasedPosition).requires(this)

    override fun initialize() {
        shooterGateServo.position = gateBlockingPosition
    }
}