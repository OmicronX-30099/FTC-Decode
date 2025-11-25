package org.firstinspires.ftc.teamcode.Tests.OtherTesting.System.IntakeSystems

import com.bylazar.configurables.annotations.Configurable
import dev.nextftc.core.commands.Command
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPositions
@Configurable
object IntakeGateSubsystem: Subsystem {
    val leftGateServo: ServoEx = ServoEx("lgate",-0.1)
    val rightGateServo: ServoEx = ServoEx("rgate",-0.1)

    var leftGateOpenPosition: Double = 0.0
    var leftGateClosePosition: Double = 0.5
    var rightGateOpenPosition: Double = 1.0
    var rightGateClosePosition: Double = 0.5

    val openGateCommand: Command = SetPositions(leftGateServo to leftGateOpenPosition, rightGateServo to rightGateOpenPosition).requires(this)
    val closeGateCommand: Command = SetPositions(leftGateServo to leftGateClosePosition, rightGateServo to rightGateClosePosition).requires(this)

    override fun initialize() {
        leftGateServo.position = leftGateClosePosition
        rightGateServo.position = rightGateClosePosition
        ActiveOpMode.telemetry.addData("IntakeGateSubsystem", "leftGatePosition = " + leftGateServo.position.toString())
        ActiveOpMode.telemetry.addData("IntakeGateSubsystem", "rightGatePosition = " + rightGateServo.position.toString())
        ActiveOpMode.telemetry.addData("System","IntakeGateSubsystem initialized")
    }
}