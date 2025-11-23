package org.firstinspires.ftc.teamcode.Draft2.System

import dev.nextftc.core.subsystems.SubsystemGroup
import dev.nextftc.extensions.pedro.PedroComponent.Companion.follower
import org.firstinspires.ftc.teamcode.Draft2.System.ShooterSubsystems.*
import org.firstinspires.ftc.teamcode.Util.Alliance
import org.firstinspires.ftc.teamcode.Util.LimelightStatus
import kotlin.properties.Delegates

object ShooterSystem:
    SubsystemGroup(TurretSubsystem, HoodSubsystem, FlywheelSubsystem, LimelightSubsystem, ShooterBrainSubsystem)
  {
    fun updateTurret() {
        if (TurretSubsystem.turretAutoAim) {
            var turretTarget: Double by Delegates.notNull()
            var currentTurretPos: Double = TurretSubsystem.currentTurretPosition
            var llStatus: LimelightStatus = LimelightSubsystem.getResults()
            if (llStatus == LimelightStatus.TARGETS_DETECTED) {
                turretTarget = ShooterBrainSubsystem.calibrateTurretPosition(currentTurretPos, llStatus.targetX)
            } else {
                turretTarget = ShooterBrainSubsystem.calibrateTurretPosition(currentTurretPos, pedroPos = follower.pose)
            }
            TurretSubsystem.setTurretPosition(turretTarget)
        }
    }
    fun updateHood() {
        if (HoodSubsystem.hoodAutoAim) {
            var hoodTarget: Double by Delegates.notNull()
            var llStatus: LimelightStatus = LimelightSubsystem.getResults()
            if (llStatus == LimelightStatus.TARGETS_DETECTED) {
                hoodTarget = ShooterBrainSubsystem.calibrateHoodPosition(llStatus.botPose)
            } else {
                hoodTarget = ShooterBrainSubsystem.calibrateHoodPosition(pedroPos = follower.pose)
            }
            HoodSubsystem.setHoodPosition(hoodTarget)
        }
    }
    fun updateFlywheel() {
        if (FlywheelSubsystem.flywheelAutoAim) {
            var flywheelTarget: Double by Delegates.notNull()
            var llStatus: LimelightStatus = LimelightSubsystem.getResults()
            if (llStatus == LimelightStatus.TARGETS_DETECTED) {
                flywheelTarget = ShooterBrainSubsystem.calibrateFlywheelVelocity(llStatus.botPose, hoodPos = HoodSubsystem.currentHoodPosition)
            } else {
                flywheelTarget = ShooterBrainSubsystem.calibrateFlywheelVelocity(pedroPos = follower.pose, hoodPos = HoodSubsystem.currentHoodPosition)
            }
            FlywheelSubsystem.setFlywheelVelocity(flywheelTarget)
        }
    }
    fun updateAll() {
        updateTurret()
        updateHood()
        updateFlywheel()
    }
    fun switchAutoAim() {
        TurretSubsystem.turretAutoAim = !TurretSubsystem.turretAutoAim
        HoodSubsystem.hoodAutoAim = !HoodSubsystem.hoodAutoAim
        FlywheelSubsystem.flywheelAutoAim = !FlywheelSubsystem.flywheelAutoAim
    }
    fun setAlliance(alliance: Alliance) {
        ShooterBrainSubsystem.goalPos = alliance.goalPose
        LimelightSubsystem.setGoal(alliance)
    }
}