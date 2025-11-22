package org.firstinspires.ftc.teamcode.Draft2.System.ShooterSystems

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode
import org.firstinspires.ftc.teamcode.Util.Alliance
import kotlin.properties.Delegates

// Subsystem to manage Limelight
object LimelightSubsystem: Subsystem {
    // Definition of hardware
    val limelight: Limelight3A = ActiveOpMode.hardwareMap["limelight"] as Limelight3A

    // Variables to track latest LLResults and current pipeline
    lateinit var currentResult: LLResult
    var currentPipeline: Int by Delegates.notNull()

    // Function to switch between red and blue goal tracking
    fun setGoal(alliance: Alliance) {
        when (alliance) {
            Alliance.RED -> {currentPipeline = 7
                             limelight.pipelineSwitch(7)}
            Alliance.BLUE -> {currentPipeline = 6
                              limelight.pipelineSwitch(6)}
        }
    }
    // Functions to start and stop the limelight
    fun startTracking() { limelight.start() }
    fun stopTracking() { limelight.stop() }
    // Subsystem periodic loop function
    override fun periodic() {
        // Retrieves latest data from limelight every iteration
        currentResult = limelight.latestResult
    }
}