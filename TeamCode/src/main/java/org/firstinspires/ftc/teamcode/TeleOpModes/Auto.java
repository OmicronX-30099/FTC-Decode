package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
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

    public PathChain score_preloaded;

    public Pose pre_load = new Pose(-2,1);
    public Pose start = new Pose(0,0);
    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwr,fwl);
    public MotorEx intake = new MotorEx("intake");
    public ServoEx kicker = new ServoEx("k");
    public Command kick;

    @Override
    public void onInit() {
        buildPaths();
        buildCommands();
    }

    @Override
    public void onStartButtonPressed() {
            kicker.setPosition(0);
        new SequentialGroup(
            //new InstantCommand(() -> fwm.setPower(1)),
            //new FollowPath(score_preloaded, true, 0.5),
            //new Delay(0.075),
            //new InstantCommand(() -> fwm.setPower(0.8)),
            //new InstantCommand(() -> intake.setPower(1)),
            //new Delay(2),
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15)//,

        ).schedule();

    }
    public void buildPaths() {
        score_preloaded = follower().pathBuilder()
                .addPath(new BezierLine(start, pre_load))
                .setLinearHeadingInterpolation(0,Math.toRadians(-10))
                .build();
    }
    public void buildCommands() {
        kick = new SequentialGroup(
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0))
        );
    }
}
