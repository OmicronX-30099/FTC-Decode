package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.commands.utility.InstantCommand
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.round

object ShooterSystem: Subsystem {
    // Defining system variables at top for easy access
    var AUTO_AIM: Boolean = false;
    // Change this variable to -384.5 for Blue, and 0 for red
    var TURRET_GOAL: Double = 0.0
    var FLYWHEEL_GOAL: Double = 0.0
    var HOOD_GOAL: Double = 0.0
    // Change goal pose to (4,144) for Blue
    var GOAL_POSE: Pose = Pose(140.0,140.0)
    var FLYWHEEL_EQ: Int = 1

    // Function to toggle autoAim on and off
    fun autoAim(on: Boolean) {
        AUTO_AIM = on
    }

    // Command for autonomous use to turn on auto aiming
    val autoAimOnCommand: Command
        get() = InstantCommand {
            this.autoAim(true)
        }

    // Command for autonomous use to turn off auto aiming
    val autoAimOffCommand: Command
        get() = InstantCommand {
            this.autoAim(false)
        }

    // Defining key mechanical components
    // Flywheel components
    val fwl: MotorEx = MotorEx("fwl")
    val fwr: MotorEx = MotorEx("fwr")
    val fwm: MotorGroup = MotorGroup(fwr,fwl)
    // ControlSystem with velocity PID for flywheel
    val flywheelControl: ControlSystem = ControlSystem.builder()
        .velPid(0.01,0.0,0.0)
        .basicFF(0.00033,0.0,0.07)
        .build()

    // Turret
    val turretMotor: MotorEx = MotorEx("tur")
    // ControlSystem with positional PID for turret
    val turretControl: ControlSystem = ControlSystem.builder()
        .posPid(0.03,0.0,0.0)
        .build()

    // Hood
    val hoodServo: ServoEx = ServoEx("hood",-0.1)

    // Kicker
    val kickServo: ServoEx = ServoEx("k",-0.1)

    // Servo SetPosition commands for two different kicker positions
    val engageKickerCommand: Command = SetPosition(kickServo, 0.25).requires(kickServo)
    val disengageKickerCommand: Command = SetPosition(kickServo, 0.0).requires(kickServo)

    /**
     * Compound command for launching ball
     * Includes:
     *     Engage Kicker
     *     Wait for 0.15 seconds for it to move up
     *     Disengage Kicker
    **/
    val kickCommand: Command
        get() = SequentialGroup(
            engageKickerCommand,
            Delay(0.15),
            disengageKickerCommand
        )

    // Method to calculate hood position, writes to system variable
    fun calibrateHoodPosition(currPose: Pose) {
        // Dont execute rest if auto aim is false
        if (!AUTO_AIM) {
            return
        }
        var distance = currPose.distanceFrom(GOAL_POSE)
        when (distance) {
            in 0.0..84.0 ->   {FLYWHEEL_EQ = 1;
                                     HOOD_GOAL = 0.0}
            in 84.0..120.0 -> {FLYWHEEL_EQ = 1;
                                     HOOD_GOAL = 0.4}
            else ->                 {FLYWHEEL_EQ = 1;
                                     HOOD_GOAL = 1.0}
        }
    }

    // Function to calculate flywheel velocity and write it to system variable, will be set to controlSystem in periodic function
    fun calibrateFlywheelVelocity(currPose: Pose) {
        // Dont execute rest if auto aim is false
        if (!AUTO_AIM) {
            return
        }
        var distance = currPose.distanceFrom(GOAL_POSE)
        var vel: Double = 0.0;
        when (FLYWHEEL_EQ) {
            1 -> {
                vel = 5.7 * distance + 805.5
            }

            2 -> {
                vel = 5.9 * distance + 675.75
            }

            3 -> {
                vel = 5.875 * distance + 730.8
            }
        }
        if (vel != FLYWHEEL_GOAL) {
            FLYWHEEL_GOAL = vel
        }
    }

    // Function to calculate turret position and write to system variable, will be assigned to control sys in the periodic
    fun calibrateTurretPosition(currPose: Pose) {
        // Dont execute rest if auto aim is false
        if (!AUTO_AIM) {
            return
        }
        var angle = atan2(GOAL_POSE.x-currPose.x,GOAL_POSE.y-currPose.y)
        var ticks = (((currPose.heading-(PI/2))+angle) / (2*PI)) * (100/24) * 384.5 * -1
        if (abs(round(ticks) - TURRET_GOAL) >= 0) {
            TURRET_GOAL = ticks
        }
    }

    fun turnOffShooter() {
        FLYWHEEL_GOAL = 0.0
        flywheelControl.goal = KineticState(0.0, 0.0)
    }

    // Function to organize looped things including motor to ControlSystem bindings with autoAim toggle
    // Also uses a write to static object variable method
    override fun periodic() {
        if (AUTO_AIM) {
            turretControl.goal = KineticState(TURRET_GOAL)
            flywheelControl.goal = KineticState(0.0,FLYWHEEL_GOAL)
            fwm.power = flywheelControl.calculate(fwm.state)
            hoodServo.position = HOOD_GOAL
        } else {
            fwm.power = -0.3
        }
        // Turret should be powered no matter the case
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}