package org.firstinspires.ftc.teamcode.Robot.Subsystems;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class TurretSubsystem implements Subsystem {
    public static TurretSubsystem INSTANCE = new TurretSubsystem();
    private TurretSubsystem() { }

    public static double INITIAL_TICKS = 0;

    public MotorEx turretMotor = new MotorEx("tur");
    public ControlSystem turretControl = ControlSystem.builder()
            .posPid(0.025,0,0)
            .build()
    ;

    public void setTurretHeading(double x, double y, double heading) {
        double angle = Math.atan((48+y)/(48-x));
        double ticks = Math.round(((-heading-angle)/(2*Math.PI)) * 384.5 * (100/24) + INITIAL_TICKS);
        turretControl.setGoal(new KineticState(ticks));
    }

    @Override
    public void periodic() {
        turretMotor.setPower(turretControl.calculate(turretMotor.getState()));
    }
}