package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

@Autonomous(name="InitialAuto")
public class Auto extends NextFTCOpMode {
    public Auto() {
        addComponents(
                new PedroComponent(Constants::createFollower),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );
    }

    // Shooter Commands
    public Command push;
    public Command realignBalls;
    public Command accelerateFlywheel;
    public Command quickAcceleration;
    public Command tripleBallSequence;

    public PathChain score_preloaded;

    public Pose pre_load = new Pose(-2,1);
    public Pose start = new Pose(0,0);
    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwr,fwl);
    public MotorEx intake = new MotorEx("intake");
    public ServoEx kicker = new ServoEx("k");
    public ServoEx hood = new ServoEx("hood");

    public Command flywheelAccelShort = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new InstantCommand(() -> hood.setPosition(0)),
            new WaitUntil(() -> fwm.getVelocity() >= 1005),
            new InstantCommand(() -> fwm.setPower(0.4))
    );
    @Override
    public void onInit() {
        buildPaths();
        buildCommands();
    }

    @Override
    public void onStartButtonPressed() {
        new SequentialGroup(
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.005),
                new InstantCommand(() -> kicker.setPosition(0)),

                flywheelAccelShort,
                new InstantCommand(() -> intake.setPower(1)),

                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),

                new Delay(1),
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),

                new Delay(1.15),
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),
                new Delay(0.25),
                new InstantCommand(() -> fwm.setPower(-0.3))
        ).schedule();


    }
    public void buildPaths() {

    }
    public void buildCommands() {
        push = new SequentialGroup(
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0))
        );

        realignBalls = new ParallelGroup(
            new InstantCommand(() -> {fwm.setPower(-0.3); intake.setPower(-0.2);}),
            new Delay(0.3)
        );

        accelerateFlywheel = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new Delay(0.2),
            new InstantCommand(() -> fwm.setPower(0.75))
        );

        quickAcceleration = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(0.85)),
            new Delay(0.05),
            new InstantCommand(() -> fwm.setPower(0.75))
        );

        tripleBallSequence = new SequentialGroup(
            new InstantCommand(() -> intake.setPower(1)),
            new Delay(0.3),
            realignBalls,
            new InstantCommand(() -> intake.setPower(0)),
            accelerateFlywheel,
            new InstantCommand(() -> intake.setPower(1)),
            quickAcceleration,
            push
        );
    }
}
