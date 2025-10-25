package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Robot.Constants;

import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;

import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="simple")
public class Tele extends NextFTCOpMode {
    public Tele() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }
    public MotorEx fl = new MotorEx("fl");
    public MotorEx fr = new MotorEx("fr");
    public MotorEx bl = new MotorEx("bl");
    public MotorEx br = new MotorEx("br");
    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorEx intake = new MotorEx("intake");
    public ServoEx kicker = new ServoEx("k");
    public ServoEx hood = new ServoEx("hood");

    public DriverControlledCommand drive;

    public Boolean isRunning = false;

    @Override
    public void onStartButtonPressed() {
        hood.setPosition(0);
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> {fwl.setPower(0.4); fwr.setPower(0.4); })
                .whenBecomesFalse(() -> {fwl.setPower(0); fwr.setPower(0);})
        ;
        Gamepads.gamepad1().leftTrigger().greaterThan(0)
                .whenTrue(() -> {fwl.setPower(-0.3); fwr.setPower(-0.3); intake.setPower(-0.2);})
                .whenBecomesFalse(() -> {fwl.setPower(0); fwr.setPower(0); intake.setPower(0);})
        ;
        Gamepads.gamepad1().rightBumper()
                .whenTrue(() -> {intake.setPower(1); })
                .whenBecomesFalse(() -> {intake.setPower(0);})
        ;
        Gamepads.gamepad1().leftBumper()
                .whenTrue(() -> {intake.setPower(-1);})
                .whenBecomesFalse(() -> {intake.setPower(0);})
        ;
        Gamepads.gamepad1().circle().whenBecomesTrue(
                new SequentialGroup(
                        new InstantCommand(() -> kicker.setPosition(0.25)),
                        new Delay(0.15),
                        new InstantCommand(() -> kicker.setPosition(0))
                )
        );
        Gamepads.gamepad1().square().whenBecomesTrue(
                new SequentialGroup(
                        new InstantCommand(() -> {fwl.setPower(1); fwr.setPower(1);}),
                        new Delay(0.5),
                        new InstantCommand(() -> {fwl.setPower(0.4); fwr.setPower(0.4);})
                )
        );
        Gamepads.gamepad1().cross().whenBecomesTrue(
                new SequentialGroup(
                        new InstantCommand(() -> {fwl.setPower(-0.5); fwr.setPower(-0.5);}),
                        new Delay (1),
                        new InstantCommand(() -> {fwl.setPower(0); fwr.setPower(0);})
                )
        );

        drive = new MecanumDriverControlled(fl, fr, bl,br, Gamepads.gamepad1().leftStickY().negate(), Gamepads.gamepad1().leftStickX(), Gamepads.gamepad1().rightStickX());
        drive.schedule();
    }

    @Override
    public void onUpdate() {
        telemetry.addData("vel", fwr.getVelocity());
        telemetry.update();
    }
}
