package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.geometry.Pose;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

public class TurretSubsystem implements Subsystem {
    public static TurretSubsystem INSTANCE = new TurretSubsystem();
    private TurretSubsystem() { }

    public MotorEx turretMotor = new MotorEx("tur");
    public ControlSystem turretControl = ControlSystem.builder()
            .posPid(0.012)
            .build()
    ;

    public static double LOOP_LIMIT = 0;
    public static double LOOP_INDEX = 0;
    public static double LAST_RECORDED_TICKS = 0;

    public void calculateHeading(Pose currentPos) {
        double x = currentPos.getX();
        double y = currentPos.getY();
        double heading = currentPos.getHeading();
        double angle = Math.atan((-48-y)/(72-x));
        double ticks = Math.round(((angle-heading)/(2*Math.PI)) * 384.5 * (100/24));
        if (Math.abs(ticks-LAST_RECORDED_TICKS) >= 3) {
            turretControl.setGoal(new KineticState(LAST_RECORDED_TICKS));
            LAST_RECORDED_TICKS = ticks;
        }
    }

    @Override
    public void periodic() {
        if (LOOP_INDEX == LOOP_LIMIT) {
            turretMotor.setPower(turretControl.calculate(turretMotor.getState()));
            LOOP_INDEX = 0;
        } else {
            LOOP_INDEX ++;
        }
    }
}
