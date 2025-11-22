package org.firstinspires.ftc.teamcode.Draft1.System.IntakeSubsystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPositions

// Subsystem singleton to control double-servo gate for intake
object IntakeGateSubsystem: Subsystem {
    // Declaration of gate servos
    val leftIntakeGateServo: ServoEx = ServoEx("il_gate",-0.1)
    val rightIntakeGateServo: ServoEx = ServoEx("ir_gate",-0.1)

    // Declaration of constant servo positions for gate
    val leftOpenPos: Double = 0.0;
    val leftClosePos: Double = 0.0;
    val rightOpenPos: Double = 0.0;
    val rightClosePos: Double = 0.0;

    // SetPositions commands to open and close gate, will help for ease of access with command interface
    val openIntakeGateCommand: Command = SetPositions(leftIntakeGateServo to leftOpenPos, rightIntakeGateServo to rightOpenPos).requires(this)
    val closeIntakeGateCommand: Command = SetPositions(leftIntakeGateServo to leftClosePos, rightIntakeGateServo to rightClosePos).requires(this)

    // Initialization of servos
    override fun initialize() {
        // By default, at the start of the program, initialize the double-servo gate to be closed
        leftIntakeGateServo.position = leftClosePos
        rightIntakeGateServo.position = rightClosePos
    }
}