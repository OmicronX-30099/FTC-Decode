package org.firstinspires.ftc.teamcode.Master.System.ShooterSubsystems

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Util.Alliance
import org.firstinspires.ftc.teamcode.Util.LimelightStatus
import kotlin.properties.Delegates

// Subsystem to manage limelight camera vision
object LimelightSubsystem: Subsystem {
    // Declaration of hardware
    val limelight: Limelight3A = ActiveOpMode.hardwareMap["limelight"] as Limelight3A

    // Variables to track current status and results
    var currentPipeline: Int by Delegates.notNull()
    var currentStatus: LimelightStatus = LimelightStatus.NO_TARGETS_DETECTED
    lateinit var latestResult: LLResult

    // Function to switch between tracking to blue goal and red goal
    fun setTrackingColor(alliance: Alliance) {
        // Uses Alliance enum class to check for blue vs red alliance
        when (alliance) {
            Alliance.BLUE -> {currentPipeline = 6}
            Alliance.RED -> {currentPipeline = 7}
        }
        limelight.pipelineSwitch(currentPipeline)
    }
    // Functions to start and stop limelight data polling
    fun startLimelight() {
        limelight.start()
    }
    fun stopLimelight() {
        limelight.stop()
    }
    // Function to get limelight status with enums IS_VALID, NO_TARGETS_DETECTED and TARGETS_DETECTED
    fun getStatus(): LimelightStatus {
        // Checks for result validity
        if (latestResult.isValid) {
            // Checks for result nullness, if null, return NO_TARGETS_DETECTED
            if (latestResult == null) {
                currentStatus = LimelightStatus.NO_TARGETS_DETECTED
                return currentStatus
            } // Else return TARGETS_DETECTED with tx,ty,ta and botPose
            else {
                currentStatus =  LimelightStatus.TARGETS_DETECTED
                currentStatus.setResults(latestResult.tx, latestResult.ty, latestResult.ta, latestResult.botpose)
                return currentStatus
            }
        } // If invalid results, return INVALID_RESULTS
        else {
            currentStatus = LimelightStatus.INVALID_RESULTS
            return currentStatus
        }
    }

    // Limelight should get latest results every loop
    override fun periodic() {
        latestResult = limelight.latestResult
    }
}