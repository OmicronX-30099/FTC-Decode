package org.firstinspires.ftc.teamcode.Systems.Subsystems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.KineticState
import dev.nextftc.control.builder.controlSystem
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.core.units.abs
import dev.nextftc.hardware.impl.MotorEx
import kotlin.math.*;

object TurretSubsystem: Subsystem {
    private val turretMotor = MotorEx("tur")
    private val turretControl = controlSystem {
        posPid(0.03, 0.0, 0.0)
    }
    var LAST_RECORDED_TICKS = 0.0;
    var LOOP_INDEX = 0
    val LOOP_LIMIT = 20

    fun setTurretHeading(currentPose: Pose) {
        var x = currentPose.x
        var y = currentPose.y
        var heading = currentPose.heading
        var angle = atan((-144 - y) / (144 - x))
        var ticks = round((angle - heading) / (2 * PI) * 384.5 * (100 / 24))
        if (abs(ticks - LAST_RECORDED_TICKS) >= 5) {
            turretControl.goal = KineticState(ticks)
            LAST_RECORDED_TICKS = ticks
        }
    }

    override fun periodic() {
        if (LOOP_INDEX == LOOP_LIMIT) {
            LOOP_INDEX = 0
            turretMotor.power = turretControl.calculate(turretMotor.state)
        } else {
            LOOP_INDEX++;
        }
    }
}