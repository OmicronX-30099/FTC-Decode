package org.firstinspires.ftc.teamcode.TeleOpModes;

import static org.firstinspires.ftc.teamcode.Systems.TurretSubsystem.*;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Systems.TurretSubsystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

@TeleOp(name = "Turret AutoAim Opmode")
public class TAim extends NextFTCOpMode {
    public TAim() {
        addComponents(
                new SubsystemComponent(TurretSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    private final MotorEx frontLeftMotor = new MotorEx("fl").reversed();
    private final MotorEx frontRightMotor = new MotorEx("fr");
    private final MotorEx backLeftMotor = new MotorEx("bl").reversed();
    private final MotorEx backRightMotor = new MotorEx("br");
    private MecanumDriverControlled driverControlled;

    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwmotor = new MotorGroup(fwl,fwr);

    @Override
    public void onStartButtonPressed() {
        driverControlled = new MecanumDriverControlled(frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.named("Drivetrain").schedule();
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> fwmotor.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> fwmotor.setPower(0));
    }

    @Override
    public void onUpdate() {;

        TurretSubsystem.INSTANCE.aimBot(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading());
        ActiveOpMode.telemetry().addData("heading",follower().getHeading());
        ActiveOpMode.telemetry().addData("X",follower().getPose().getX());
        ActiveOpMode.telemetry().addData("Y",follower().getPose().getY());
        ActiveOpMode.telemetry().addData("ticks",TurretSubsystem.INSTANCE.calculate(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading()));
    }
}