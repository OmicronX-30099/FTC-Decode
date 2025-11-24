package org.firstinspires.ftc.teamcode.Tests.LLTesting

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.ftc.ActiveOpMode

object LimelightSubsystemLLT: Subsystem {
    lateinit var limelight: Limelight3A

    lateinit var latestResult: LLResult

    fun getResult(): LLResult {
        latestResult = limelight.latestResult
        return latestResult
    }
    fun calculateTurretAngle(turretTicks: Double): Double {
        var result = this.getResult()
        var theta = -1 * result.tx
        var ticks = (theta / 360) * (100 / 24) * 384.5
        ActiveOpMode.telemetry.addData("Limelight target", Math.round(this.normalizeTurret(ticks+turretTicks)).toDouble())
        return Math.round(this.normalizeTurret(ticks+turretTicks)).toDouble()
    }
    fun normalizeTurret(ticks: Double): Double {
        var normalized = ticks % (38450/24)
        ActiveOpMode.telemetry.addData("normalizer", normalized)
        return normalized
    }
    fun startLL() {
        limelight.pipelineSwitch(1)
        limelight.start()
    }

    override fun initialize() {
        limelight = ActiveOpMode.hardwareMap.get(Limelight3A::class.java, "Limelight")
    }
}