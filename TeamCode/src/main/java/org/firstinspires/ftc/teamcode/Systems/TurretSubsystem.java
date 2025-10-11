package org.firstinspires.ftc.teamcode.Systems;

// Static Constants
import static org.firstinspires.ftc.teamcode.Constants.PositionConstants.p;
import static org.firstinspires.ftc.teamcode.Constants.PositionConstants.i;
import static org.firstinspires.ftc.teamcode.Constants.PositionConstants.d;

import static org.firstinspires.ftc.teamcode.Constants.ConfigConstants.motor_turret_config;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class TurretSubsystem implements Subsystem {
    public static TurretSubsystem INSTANCE = new TurretSubsystem();
    private TurretSubsystem() { }

    public static MotorEx turretMotor = new MotorEx("tur");
    public static ControlSystem turretControl = ControlSystem.builder()
            .posPid(0.1,0,0)
            .build()
    ;

    public static double turret_goal;

    public double calculate(double x, double y, double heading) {
        double dx = 144-x;
        double dy = 48+y;
        double angle = Math.atan(dy/dx);
        double ticks = ((angle+heading)/(2*Math.PI)) * 384.5;
        return ticks;
    }

    public void aimBot(double x, double y, double heading) {
        turret_goal = this.calculate(x, y, heading);
        turretControl.setGoal(new KineticState(turret_goal));
    }

    @Override
    public void periodic() {
        turretMotor.setPower(turretControl.calculate(turretMotor.getState()));
    }
}
