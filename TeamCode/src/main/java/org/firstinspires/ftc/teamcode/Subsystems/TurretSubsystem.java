package org.firstinspires.ftc.teamcode.Subsystems;

// Static Constants

import com.acmerobotics.dashboard.config.Config;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.hardware.impl.MotorEx;

@Config
public class TurretSubsystem implements Subsystem {
    public static TurretSubsystem INSTANCE = new TurretSubsystem();
    private TurretSubsystem() { }

    public static double INITIAL_TICKS = 0;

    public MotorEx turretMotor = new MotorEx("tur");
    public ControlSystem turretControl = ControlSystem.builder()
            .posPid(0.1,0,0)
            .build()
    ;

    public void calibrateTurretAngle(double x, double y, double heading) {
        double angle = Math.atan((48+y)/(144-x));
        double ticks = (((-heading-angle)/(2*Math.PI)) * 384.5 * (100/24)) + INITIAL_TICKS;
        ActiveOpMode.telemetry().addData("Turret Target", ticks);
        turretControl.setGoal(new KineticState(ticks));
    }

    @Override
    public void periodic() {
        turretMotor.setPower(turretControl.calculate(turretMotor.getState()));
    }
}
