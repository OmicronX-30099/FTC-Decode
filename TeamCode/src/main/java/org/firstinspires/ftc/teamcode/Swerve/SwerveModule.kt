package org.firstinspires.ftc.teamcode.Swerve

import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.hardware.CRServo
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.impl.FeedbackCRServoEx
import dev.nextftc.hardware.impl.MotorEx

class SwerveModule
    (driveMotorConfig: String, turnCRServoConfig: String, analogInputConfig: String, offset: Double = 0.0) {

    val driveMotor = MotorEx(driveMotorConfig).brakeMode()
    val turnCRServo = FeedbackCRServoEx(0.01, { ActiveOpMode.hardwareMap[analogInputConfig] as AnalogInput }, { ActiveOpMode.hardwareMap[turnCRServoConfig] as CRServo})
    val swerveOffset = offset

    fun set(speed: Double, angle: Double) {

    }
}
