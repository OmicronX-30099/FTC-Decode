package org.firstinspires.ftc.teamcode.Subsystems;

// Static Constants

import com.acmerobotics.dashboard.config.Config;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedforward.BasicFeedforwardParameters;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;


public class FlywheelSubsystem implements Subsystem {
    public static FlywheelSubsystem INSTANCE = new FlywheelSubsystem();
    private FlywheelSubsystem() { }

    public static double INITIAL_TICKS = 0;

    public MotorEx leftFlywheelMotor = new MotorEx("fwl").reversed();
    public MotorEx rightFlywheelMotor = new MotorEx("fwr");
    public MotorGroup flywheelMotors = new MotorGroup(leftFlywheelMotor, rightFlywheelMotor);
    public ControlSystem flywheelControl = ControlSystem.builder()
            .velPid(0.1,0,0)
            .basicFF(new BasicFeedforwardParameters(0,0,0))
            .build()
    ;

    public void calibrateFlywheelVelocity() {
        // math here
        double vel = 0;
        flywheelControl.setGoal(new KineticState(0, vel));
    }

    @Override
    public void periodic() {
        flywheelMotors.setPower(flywheelControl.calculate(flywheelMotors.getState()));
    }
}
