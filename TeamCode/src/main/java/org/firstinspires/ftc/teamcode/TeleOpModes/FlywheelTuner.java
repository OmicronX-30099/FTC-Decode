package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name="Flywheel Tuner")
public class FlywheelTuner extends NextFTCOpMode {
    public FlywheelTuner() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );
    }

    public MotorEx leftFlywheelMotor = new MotorEx("fwl").reversed();
    public MotorEx rightFlywheelMotor = new MotorEx("fwr");
    public MotorGroup flywheelMotors = new MotorGroup(leftFlywheelMotor, rightFlywheelMotor);

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                 .whenTrue(() -> flywheelMotors.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> flywheelMotors.setPower(0))
        ;
    }

    @Override
    public void onUpdate() {
        ActiveOpMode.telemetry().addData("Velocity", FlywheelSystem.INSTANCE.getVel());
        ActiveOpMode.telemetry().update();
    }
}
