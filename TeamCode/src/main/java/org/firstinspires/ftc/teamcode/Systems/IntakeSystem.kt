package org.firstinspires.ftc.teamcode.Systems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition
import dev.nextftc.hardware.powerable.SetPower

object IntakeSystem: Subsystem {
    // Defining key mechanical components
    val intakeMotor: MotorEx = MotorEx("intake")
    val gateServo: ServoEx = ServoEx("gate")

    // Servo setPosition Commands for opening and closing gate
    val openGateCommand: Command = SetPosition(gateServo, 0.8).requires(gateServo)
    val closeGateCommand: Command = SetPosition(gateServo, 0.28).requires(gateServo)

    /**
     * Compound Command for autonomous use to start intake
     * Includes:
     *     Open the gate
     *     Set Motor Power to 1
    **/
    val startIntakeCommand: Command
        get() = SequentialGroup(
            openGateCommand,
            this.intakeCommand(1.0)
        )

    /**
     * Compound Command for autonomous use to start intake
     * Includes:
     *     Close the gate
     *     Set Motor Power to 0
    **/
    val stopIntakeCommand: Command
        get() = SequentialGroup(
            closeGateCommand,
            this.intakeCommand(0.0)
        )

    // Function that converts a decimal power value into a schedulable setPower Command
    fun intakeCommand(power: Double): Command {
        return SetPower(intakeMotor, power).requires(intakeMotor)
    }

    // Initializes the gate servo to closed position
    override fun initialize() {
        gateServo.position = 0.28
    }
}