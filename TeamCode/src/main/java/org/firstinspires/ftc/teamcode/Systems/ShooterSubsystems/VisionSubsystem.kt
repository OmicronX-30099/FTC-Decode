package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode

object VisionSubsystem: Subsystem {
    val limelight: Limelight3A = ActiveOpMode.hardwareMap["limelight"] as Limelight3A
}