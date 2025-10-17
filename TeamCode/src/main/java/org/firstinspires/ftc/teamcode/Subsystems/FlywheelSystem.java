package org.firstinspires.ftc.teamcode.Subsystems;

// Static Constants

import com.acmerobotics.dashboard.config.Config;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedforward.BasicFeedforwardParameters;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.powerable.SetPower;


public class FlywheelSystem implements Subsystem {
    public static FlywheelSystem INSTANCE = new FlywheelSystem();
    private FlywheelSystem() { }

    public static double INITIAL_TICKS = 0;

    public MotorEx leftFlywheelMotor = new MotorEx("fwl").reversed();
    public MotorEx rightFlywheelMotor = new MotorEx("fwr");
    public MotorGroup flywheelMotors = new MotorGroup(leftFlywheelMotor, rightFlywheelMotor);

    public Command setPower(double power) {
        return new SetPower(flywheelMotors, power);
    }

    public double getVel() {
        return flywheelMotors.getVelocity();
    }
}
