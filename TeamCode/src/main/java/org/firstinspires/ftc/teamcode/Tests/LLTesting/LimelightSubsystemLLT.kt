package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode

object LimelightSubsystemLLT: Subsystem {
    val limelight: Limelight3A = ActiveOpMode.hardwareMap["limelight"] as Limelight3A

    lateinit var latestResult: LLResult

    fun getResult(): LLResult {
        latestResult = limelight.latestResult
        return latestResult
    }
    fun calculateTurretAngle(turretTicks: Double): Double {
        var result = this.getResult()
        var theta = -1 * result.tx
        var ticks = (theta / 360) * (100 / 24) * 384.5
        return Math.round(this.normalizeTurret(ticks+turretTicks)).toDouble()
    }
    fun normalizeTurret(ticks: Double): Double {
        var normalized = ticks % (38450/24)
        return normalized
    }
    fun startLL() {
        limelight.pipelineSwitch(1)
        limelight.start()
    }
}