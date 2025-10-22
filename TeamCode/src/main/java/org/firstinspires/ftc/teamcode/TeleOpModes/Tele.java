package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Robot.Constants;
import org.firstinspires.ftc.teamcode.Robot.ShooterSubsystems.TurretSubsystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="simple")
public class Tele extends NextFTCOpMode {
    public Tele() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                new SubsystemComponent(TurretSubsystem.INSTANCE),
                new PedroComponent(Constants::createFollower)
        );
    }
    public MotorEx fl = new MotorEx("fl");
    public MotorEx fr = new MotorEx("fr");
    public MotorEx bl = new MotorEx("bl");
    public MotorEx br = new MotorEx("br");
    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");
    public MotorEx intake = new MotorEx("intake");
    public ServoEx hoodServo = new ServoEx("hood");
    public ServoEx k = new ServoEx("k");

    public DriverControlledCommand drive;

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> {fwl.setPower(Gamepads.gamepad1().rightTrigger().get()); fwr.setPower(Gamepads.gamepad1().rightTrigger().get()); })
                .whenBecomesFalse(() -> {fwl.setPower(0); fwr.setPower(0);})
        ;
        Gamepads.gamepad1().leftTrigger().greaterThan(0)
                .whenTrue(() -> intake.setPower(Gamepads.gamepad1().leftTrigger().get()))
                .whenBecomesFalse(() -> intake.setPower(0))
        ;
        drive = new MecanumDriverControlled(fl, fr, bl,br, Gamepads.gamepad1().leftStickY().negate(), Gamepads.gamepad1().leftStickX(), Gamepads.gamepad1().rightStickX());
        drive.schedule();
        Gamepads.gamepad1().rightBumper().whenTrue(() -> k.setPosition(0.25))
                .whenBecomesFalse(() -> k.setPosition(0));
        Gamepads.gamepad1().triangle().whenBecomesTrue(() -> hoodServo.setPosition(0));
        Gamepads.gamepad1().circle().whenBecomesTrue(() -> hoodServo.setPosition(0.5));
        Gamepads.gamepad1().square().whenBecomesTrue(() -> hoodServo.setPosition(1));

    }

    @Override
    public void onUpdate() {
        TurretSubsystem.INSTANCE.setTurretHeading(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading());
        telemetry.addData("vel", fwr.getVelocity());
        telemetry.update();
    }
}
