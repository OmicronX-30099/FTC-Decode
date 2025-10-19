package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.HoodSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;

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
                new SubsystemComponent(ShooterSystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }


    private final MotorEx frontLeftMotor = new MotorEx("fl");
    private final MotorEx frontRightMotor = new MotorEx("fr");
    private final MotorEx backLeftMotor = new MotorEx("bl");
    private final MotorEx backRightMotor = new MotorEx("br");
    private MecanumDriverControlled driverControlled;

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
    }

    @Override
    public void onUpdate() {;
        double x = follower().getPose().getX();
        double y = follower().getPose().getY();
        double heading = follower().getHeading();
        TurretSubsystem.INSTANCE.setTurretHeading(x, y, heading);
        ActiveOpMode.telemetry().addData("x", x);
        ActiveOpMode.telemetry().addData("y", y);
        ActiveOpMode.telemetry().addData("headings", heading);
        ActiveOpMode.telemetry().update();
    }
}