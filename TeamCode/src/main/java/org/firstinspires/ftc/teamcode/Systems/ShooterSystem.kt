package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.SubsystemGroup
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.FlywheelSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.HoodSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.KickerSubsystem
import org.firstinspires.ftc.teamcode.Systems.ShooterSubsystems.TurretSubsystem
import kotlin.math.*

object ShooterSystem: SubsystemGroup(
    FlywheelSubsystem,
    HoodSubsystem,
    TurretSubsystem,
    KickerSubsystem
) {
    var PREV_TURRET_POS: Double = 0.0;
    var CURRENT_HOOD_POS: Double = 0.0;
    var CURRENT_FLYWHEEL_VEL: Double = 0.0;

    val kick: Command = SequentialGroup(
        KickerSubsystem.launch,
        Delay(0.15),
        KickerSubsystem.reset
    )

    fun calibrateShooter(curPos: Pose) {
        calibrateHood(curPos)
        calibrateFlywheel(curPos)
        calibrateTurret(curPos)
    }
    fun calibrateHood(curPos: Pose) {
        // MATH HERE
        HoodSubsystem.setPos(0.0)
        CURRENT_HOOD_POS = 0.0;
    }
    fun calibrateFlywheel(curPos: Pose) {
        // MATH HERE
        FlywheelSubsystem.setGoal(0.0)
        CURRENT_FLYWHEEL_VEL = 0.0;
    }
    fun calibrateTurret(curPos: Pose) {
        var x = curPos.x
        var y = curPos.y
        var heading = curPos.heading
        var angle = atan((-144 - y) / (144 - x))
        var ticks = round((angle - heading) / (2 * PI) * 384.5 * (100 / 24))

        if (abs(ticks - PREV_TURRET_POS) >= 5) {
            TurretSubsystem.setTurretHeading(ticks)
            PREV_TURRET_POS = ticks
        }
    }
}