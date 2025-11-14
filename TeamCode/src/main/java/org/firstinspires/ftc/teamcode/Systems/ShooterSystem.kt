package org.firstinspires.ftc.teamcode.Systems

import com.pedropathing.geometry.Pose
import dev.nextftc.control.ControlSystem
import dev.nextftc.control.KineticState
import dev.nextftc.core.commands.Command
import dev.nextftc.core.commands.delays.Delay
import dev.nextftc.core.commands.groups.SequentialGroup
import dev.nextftc.core.subsystems.Subsystem
import dev.nextftc.hardware.controllable.MotorGroup
import dev.nextftc.hardware.impl.MotorEx
import dev.nextftc.hardware.impl.ServoEx
import dev.nextftc.hardware.positionable.SetPosition

class ShooterSystem: Subsystem {
    // Defining system variables at top for easy access
    var AUTO_AIM: Boolean = false;
    var TURRET_GOAL: Double = 0.0
    var FLYWHEEL_GOAL: Double = 0.0
    var HOOD_GOAL: Double = 0.0
    var GOAL_POSE: Pose = Pose(144.0,144.0)
    var FLYWHEEL_EQ: Int = 1

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
    val hoodServo: ServoEx = ServoEx("hood")

    // Kicker
    val kickServo: ServoEx = ServoEx("k")

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

    fun calibrateHoodPosition(currPose: Pose) {
        var distance = currPose.distanceFrom(GOAL_POSE)
        when (distance) {
            in 0.0..84.0 ->   {FLYWHEEL_EQ = 1;
                                     HOOD_GOAL = 0.0}
            in 84.0..120.0 -> {
                                     HOOD_GOAL = 0.4}
            else ->                 {
                                     HOOD_GOAL = 1.0}
        }
    }

    // Function to toggle autoAim on and off
    fun autoAim(on: Boolean) {
        AUTO_AIM = on
    }

    // Function to organize looped things including motor to ControlSystem bindings with autoAim toggle
    // Also uses a write to static object variable method
    override fun periodic() {
        if (AUTO_AIM) {
            turretControl.goal = KineticState(TURRET_GOAL)
            flywheelControl.goal = KineticState(0.0,FLYWHEEL_GOAL)
            fwm.power = flywheelControl.calculate(fwm.state)
            hoodServo.position = HOOD_GOAL
        }
        // Turret should be powered no matter the case
        turretMotor.power = turretControl.calculate(turretMotor.state)
    }
}