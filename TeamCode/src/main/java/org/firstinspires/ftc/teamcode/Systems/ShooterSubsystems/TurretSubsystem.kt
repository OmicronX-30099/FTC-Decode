package org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.impl.MotorEx

object TurretSubsystem : Subsystem {
    val turretMotor : MotorEx = MotorEx("tur")
    val turretControl = ControlSystem.builder()
        .posPid(0.025,0.0,0.0)
        .build()

    fun setTurretHeading(ticks: Double) {
        turretControl.goal = KineticState(ticks)
    }
    override fun periodic() {
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}