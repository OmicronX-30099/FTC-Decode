package org.firstinspires.ftc.teamcode.TeleOpModes;

/**
 * This is Team 30099 OmicronX's Code
 * Authors: Achintya Akula, Maximus Xiao, Alex Zhang
 * Season: FTC Decode (2025-2026)
 * Event: SoCal Group H League Meet 0
 * Type: TeleOp
 * Alliance: Any
 * Contains a triple ball sequence change from Version 1
 */

import org.firstinspires.ftc.teamcode.Constants.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOp Version 2")
public class TeleOpV2 extends NextFTCOpMode {
    public TeleOpV2() {
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public MotorEx fl = new MotorEx("fl");
    public MotorEx fr = new MotorEx("fr");
    public MotorEx bl = new MotorEx("bl");
    public MotorEx br = new MotorEx("br");
    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwr,fwl);
    public MotorEx intake = new MotorEx("intake");
    public ServoEx kicker = new ServoEx("k");
    public ServoEx hood = new ServoEx("hood");

    public Command push;
    public Command flywheelAccelShort;
    public Command flywheelAccelFar;
    public Command shootSequenceShort;
    public Command shootSequenceFar;

    public DriverControlledCommand drive;

    @Override
    public void onInit() {
        buildCommands();
    }

    public void buildCommands() {
        push = new SequentialGroup(
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0))
        );

        flywheelAccelShort = new SequentialGroup(
                new InstantCommand(() -> fwm.setPower(1)),
                new InstantCommand(() -> hood.setPosition(0)),
                new WaitUntil(() -> fwm.getVelocity() >= 1030),
                new InstantCommand(() -> fwm.setPower(0.42))
        );
        flywheelAccelFar = new SequentialGroup(
                new InstantCommand(() -> fwm.setPower(1)),
                new InstantCommand(() -> hood.setPosition(0.3)),
                new WaitUntil(() -> fwm.getVelocity() >= 1260),
                new InstantCommand(() -> fwm.setPower(0.52))
        );
        shootSequenceShort = new SequentialGroup(
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.0005),
                new InstantCommand(() -> kicker.setPosition(0)),
                flywheelAccelShort,
                new Delay(0.25),
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                new InstantCommand(() -> intake.setPower(1)),
                flywheelAccelShort,
                new Delay(0.2),
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                flywheelAccelShort,
                new Delay(0.2),
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                new Delay(0.5),
                new InstantCommand(() -> fwm.setPower(-0.3))
        );
        shootSequenceFar = new SequentialGroup(
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.0005),
                new InstantCommand(() -> kicker.setPosition(0)),
                flywheelAccelFar,
                new InstantCommand(() -> intake.setPower(1)),
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                flywheelAccelFar,
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                flywheelAccelFar,
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                new Delay(0.5),
                new InstantCommand(() -> fwm.setPower(-0.3))
        );
    }
    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightBumper().whenBecomesTrue(
                new SequentialGroup(
                    shootSequenceShort,
                    new InstantCommand(() ->fwm.setPower(0) )
                )
        );
        Gamepads.gamepad1().leftBumper().whenBecomesTrue(
                new SequentialGroup(
                        shootSequenceFar,
                        new InstantCommand(() ->fwm.setPower(0) )
                )
        );
        drive = new MecanumDriverControlled(fl, fr, bl,br, Gamepads.gamepad1().leftStickY().negate(), Gamepads.gamepad1().leftStickX(), Gamepads.gamepad1().rightStickX());
        drive.schedule();
        Gamepads.gamepad1().leftTrigger().greaterThan(0)
                .whenTrue(() -> intake.setPower(-1*Gamepads.gamepad1().leftTrigger().get()))
                .whenBecomesFalse(() -> intake.setPower(0))
        ;
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> intake.setPower(1*Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> intake.setPower(0))
        ;
    }
}
