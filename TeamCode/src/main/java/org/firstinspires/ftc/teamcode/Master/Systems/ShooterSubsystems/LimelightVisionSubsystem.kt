package org.firstinspires.ftc.teamcode.Master.Systems.ShooterSubsystems

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Util.LimelightStatus
import kotlin.properties.Delegates

object LimelightVisionSubsystem: Subsystem {
    val limelight: Limelight3A = ActiveOpMode.hardwareMap["limelight"] as Limelight3A
    var currentPipeline: Int by Delegates.notNull()
    var currentStatus: LimelightStatus = LimelightStatus.NO_TARGETS_DETECTED
    lateinit var latestResult: LLResult;

    fun getStatus(): LimelightStatus {
        if (!latestResult.isValid) {
            return LimelightStatus.INVALID_RESULTS
        } else if (latestResult == null) {
            return LimelightStatus.NO_TARGETS_DETECTED
        } else {
            var result = LimelightStatus.TARGETS_DETECTED
            result.setResults(latestResult.tx, latestResult.ty, latestResult.ta)
            return result
        }
    }

    fun setTrackingColor(color: String) {
        when (color) {
            "blue" -> {currentPipeline = 6}
            "red" -> {currentPipeline = 7}
            else -> error("U stupid ahh dumass, check ur code for capitalization retard")
        }
        limelight.pipelineSwitch(currentPipeline)
    }

    fun startLimelight() {
        limelight.start()
    }

    override fun periodic() {
        latestResult = limelight.latestResult
    }
}