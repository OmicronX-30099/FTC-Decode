package org.firstinspires.ftc.teamcode.Swerve;

import java.util.function.Supplier
import kotlin.math.*;

class SwerveDrivetrain
    (swerveDriveMotorConfigs: Array<String>, swerveTurnCRServoConfigs: Array<String>, swerveAnalogInputConfigs: Array<String>, wheelBase: Double, trackWidth: Double) {
    init {
        require(swerveDriveMotorConfigs.size == 4) {"Must have 4 Drive Motor Configs"}
        require(swerveTurnCRServoConfigs.size == 4) {"Must have 4 Turn CRServo Configs"}
        require(swerveAnalogInputConfigs.size == 4) {"Must have 4 Analog Input Configs"}
    }
    var wheelBaseLen: Double = wheelBase
    var trackWidthLen: Double = trackWidth
    var moduleOffset: Double = hypot(wheelBase, trackWidth);

    var frontLeftSwerveModule: SwerveModule = SwerveModule(swerveDriveMotorConfigs.get(0), swerveTurnCRServoConfigs.get(0), swerveAnalogInputConfigs.get(0))
    var frontRightSwerveModule: SwerveModule = SwerveModule(swerveDriveMotorConfigs.get(1), swerveTurnCRServoConfigs.get(1), swerveAnalogInputConfigs.get(1))
    var backLeftSwerveModule: SwerveModule = SwerveModule(swerveDriveMotorConfigs.get(2), swerveTurnCRServoConfigs.get(2), swerveAnalogInputConfigs.get(2))
    var backRightSwerveModule: SwerveModule = SwerveModule(swerveDriveMotorConfigs.get(3), swerveTurnCRServoConfigs.get(3), swerveAnalogInputConfigs.get(3))

    fun calculateAndSetPower(x: Double, y: Double, rx: Double) {
    }
}
