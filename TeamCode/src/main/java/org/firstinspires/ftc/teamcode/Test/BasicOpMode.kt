package org.firstinspires.ftc.teamcode.Test

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import dev.frozenmilk.dairy.mercurial.continuations.Closure
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.exec
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.loop
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.match
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.wait
import dev.frozenmilk.dairy.mercurial.continuations.Continuations.sequence
import dev.frozenmilk.dairy.mercurial.ftc.Mercurial
import kotlin.math.abs
import kotlin.math.max

val basicMercurialTeleop = Mercurial.teleop {
    val frontLeftMotor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "frontLeftDriveMotor")
    val frontRightMotor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "frontRightDriveMotor")
    val backLeftMotor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "backLeftDriveMotor")
    val backRightMotor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "backRightDriveMotor")

    frontLeftMotor.direction = DcMotorSimple.Direction.REVERSE
    backLeftMotor.direction = DcMotorSimple.Direction.REVERSE

    var drivetrainScalar = 1.0

    val intakeMotor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, "intakeMotor")

    val drivetrain: Closure =
        exec {
            val drivePower: Double = -gamepad1.left_stick_y.toDouble()//.pow(2)
            val strafePower: Double = gamepad1.left_stick_x.toDouble()//.pow(2)
            val turnPower: Double = gamepad1.right_stick_x.toDouble()//.pow(2)

            val powerLimit: Double = max(abs(drivePower) + abs(strafePower) + abs(turnPower), 1.0)

            val frontLeftDrivePower: Double = (drivePower + strafePower + turnPower) / powerLimit
            val frontRightDrivePower: Double = (drivePower - strafePower + turnPower) / powerLimit
            val backLeftDrivePower: Double = (drivePower - strafePower - turnPower) / powerLimit
            val backRightDrivePower: Double = (drivePower + strafePower - turnPower) / powerLimit

            frontLeftMotor.power = frontLeftDrivePower * drivetrainScalar
            frontRightMotor.power = frontRightDrivePower * drivetrainScalar
            backLeftMotor.power = backLeftDrivePower * drivetrainScalar
            backRightMotor.power = backRightDrivePower * drivetrainScalar
        }
    val intake: Closure =
        exec {
            val intakePower: Double = (gamepad1.right_trigger - gamepad1.left_trigger).toDouble()
            intakeMotor.power = intakePower
        }

    val drivetrainSpeedControl: Closure =
        match { drivetrainScalar }
            .branch(1.0, exec { drivetrainScalar = 0.6 })
            .branch(0.6, exec { drivetrainScalar = 0.2 })
            .branch(0.2, exec { drivetrainScalar = 1.0 })
            .assertExhaustive()

    schedule(
        sequence(
            wait { inLoop },
            loop(
                sequence(
                    drivetrain,
                    intake
                )
            )
        )
    )
    bindSpawn (
        risingEdge { gamepad1.right_stick_button },
        drivetrainSpeedControl
    )

    dropToScheduler()
}