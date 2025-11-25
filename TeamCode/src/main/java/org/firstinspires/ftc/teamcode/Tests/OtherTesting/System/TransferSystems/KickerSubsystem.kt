package org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.TransferSystems

import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

object KickerSubsystem: Subsystem {
    val kickerServo: ServoEx = ServoEx("kicker",-0.1)

    val kickBallPosition: Double = 0.0;
    val kickerResetPosition: Double = 0.0

    val kickBallCommand: Command = SetPosition(kickerServo,kickBallPosition).requires(this)
    val resetKickerCommand: Command = SetPosition(kickerServo,kickerResetPosition).requires(this)

    override fun initialize() {
        kickerServo.position = kickerResetPosition
        ActiveOpMode.telemetry.addData("KickerSubsystem", "kickerPosition = " + kickerServo.position.toString())
        ActiveOpMode.telemetry.addData("System", "KickerSubsystem Initialized")
    }
}