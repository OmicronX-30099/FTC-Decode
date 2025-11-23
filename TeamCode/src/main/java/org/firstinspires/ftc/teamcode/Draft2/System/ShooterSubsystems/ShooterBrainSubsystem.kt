package org.firstinspires.ftc.teamcode.Draft2.System.ShooterSubsystems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.subsystems.Subsystem
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.properties.Delegates

object ShooterBrainSubsystem: Subsystem {
    // Coefficient of x^2 term
    val flywheelEquationA: Double = -24.993970450383305
    // Coefficient of xy term
    val flywheelEquationB: Double = -0.07154455960209152
    // Coefficient of y^2 term
    val flywheelEquationC: Double = 0.00019278705563574583
    // Coefficient of x
    val flywheelEquationD: Double = 116.26277827556967
    // Coefficient of y
    val flywheelEquationE: Double = -0.272675259634784
    // Constant term
    val flywheelEquationF: Double = 122.99485320234648

    lateinit var goalPos: Pose

    fun calibrateHoodPosition(llPos: Pose3D? = null, pedroPos: Pose = Pose(0.0,0.0)): Double {
        var targetHoodPosition: Double by Delegates.notNull()
        var distanceFromGoal: Double = getDistanceFromGoal(llPos, pedroPos)
        when (distanceFromGoal) {
            in 0.0..< 5.0 ->  {targetHoodPosition = 0.0}
            in 5.0..< 10.0 -> {targetHoodPosition = 0.5}
            else ->              {targetHoodPosition = 1.0}
        }
        return targetHoodPosition
    }
    fun calibrateTurretPosition(currentTurretPos: Double, tx: Double? = null, pedroPos: Pose = Pose(0.0,0.0)): Double {
        var targetTurretPosition: Double by Delegates.notNull()
        if (tx != null) {
            targetTurretPosition = ((tx / 360) * (100 / 24) * 384.5 * -1) + currentTurretPos
        } else {
            var angle = atan2(goalPos.x-pedroPos.x,goalPos.y-pedroPos.y)
            targetTurretPosition = (((pedroPos.heading-(PI/2))+angle) / (2*PI)) * (100/24) * 384.5 * -1
        }
        targetTurretPosition %= (384.5 * (100/24))
        return targetTurretPosition
    }
    fun calibrateFlywheelVelocity(llPos: Pose3D? = null, pedroPos: Pose = Pose(0.0,0.0), hoodPos: Double): Double {
        var targetFlywheelVelocity: Double by Delegates.notNull()
        var distanceFromGoal: Double = getDistanceFromGoal(llPos, pedroPos)

        var quadCoeffA: Double = flywheelEquationC
        var quadCoeffB: Double = (flywheelEquationB * hoodPos) + flywheelEquationE
        var quadCoeffC: Double = (flywheelEquationA * hoodPos * hoodPos) + (flywheelEquationD * hoodPos) + (flywheelEquationF - distanceFromGoal)

        var discriminant: Double = Math.sqrt((quadCoeffB * quadCoeffB) - (4 * quadCoeffA * quadCoeffC))
        targetFlywheelVelocity = ((-1 * quadCoeffB) + discriminant) / (2 * quadCoeffA)
        return targetFlywheelVelocity
    }
    fun getDistanceFromGoal(llPos: Pose3D?, pedroPos: Pose): Double {
        lateinit var currentPos: Pose
        var distanceFromGoal: Double by Delegates.notNull()
        if (llPos != null) {
            currentPos = Pose(llPos.position.x, llPos.position.y)
        } else {
            currentPos = pedroPos
        }
        distanceFromGoal = currentPos.distanceFrom(goalPos)
        return distanceFromGoal
    }
}