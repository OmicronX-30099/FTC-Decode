package org.firstinspires.ftc.teamcode.AutoOpModes;

/**
 * This is Team 30099 OmicronX's Code
 * Authors: Achintya Akula, Maximus Xiao
 * Season: FTC Decode (2025-2026)
 * Event: SoCal Group H League Meet 0
 * Type: AutoOpMode
 * Alliance: Red
 * Autonomous OpMode to score an ideal 12 balls
 * Had an average of 10-11 balls during testing, but made one max run of 12 balls
 */

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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
import dev.nextftc.core.commands.delays.WaitUntil;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import org.firstinspires.ftc.teamcode.Constants;

@Autonomous(name="Right Side Auto - 12ball")
public class RedAuto extends NextFTCOpMode {

    public RedAuto() {
        addComponents(
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower),
                BindingsComponent.INSTANCE
        );
    }

    public MotorEx fwl = new MotorEx("fwl");
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwm = new MotorGroup(fwr,fwl);
    public MotorEx intake = new MotorEx("intake");
    public ServoEx kicker = new ServoEx("k");
    public ServoEx hood = new ServoEx("hood");

    public PathChain Path1;
    public PathChain Path2;
    public PathChain Path3;
    public PathChain Path4;
    public PathChain Path5;
    public PathChain Path6;
    public PathChain Path7;
    public PathChain Path8;
    public PathChain Path9;
    public PathChain Path10;
    public  PathChain Path11;

    public static Pose current_pose;

    public Command push = new SequentialGroup(
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0))
    );

    public Command flywheelAccelShort = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new InstantCommand(() -> hood.setPosition(0)),
            new WaitUntil(() -> fwm.getVelocity() >= 1030),
            new InstantCommand(() -> fwm.setPower(0.42))
    );
    public Command flywheelAccelFar = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new InstantCommand(() -> hood.setPosition(0.3)),
            new WaitUntil(() -> fwm.getVelocity() >= 1260),
            new InstantCommand(() -> fwm.setPower(0.52))
    );
    public Command shootSequenceShort = new SequentialGroup(
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
    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(109.6, 134.37, Math.toRadians(270)));
        buildPaths();
    }
    @Override
    public void onStartButtonPressed() {
        kicker.setPosition(0);
        push.schedule();
        new SequentialGroup(
                new FollowPath(Path1, true),
                shootSequenceShort,
                new FollowPath(Path2, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path3, true),
                new InstantCommand(() -> intake.setPower(0.5)),
                new FollowPath(Path4, true),
                new InstantCommand(() -> intake.setPower(0)),
                new Delay(0.25),
                shootSequenceShort,
                new FollowPath(Path5, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path6, true),
                new InstantCommand(() -> intake.setPower(0.5)),
                new FollowPath(Path7, true),
                new InstantCommand(() -> intake.setPower(0)),
                new Delay(0.25),
                shootSequenceShort,
                new FollowPath(Path8, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path9, true),
                new InstantCommand(() -> intake.setPower(0.5)),
                new FollowPath(Path10, true),
                new InstantCommand(() -> intake.setPower(0)),
                new Delay(0.25),
                shootSequenceShort,
                new FollowPath(Path11, true)
        ).schedule();
    }
    @Override
    public void onUpdate() {
        current_pose = follower().getPose();
    }
    public void buildPaths() {
        Path1 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(109.600, 134.370), new Pose(105.000, 105.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-130))
                .build();

        Path2 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(105.000, 105.000),
                                new Pose(89.105, 97.591),
                                new Pose(100.000, 85.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-130), Math.toRadians(0))
                .build();

        Path3 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(100.000, 85.000), new Pose(126.500, 85.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path4 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(127.000, 85.000), new Pose(105.000, 105.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-130))
                .build();

        Path5 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(105.000,105.000), new Pose(102.500,60.00))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-130), Math.toRadians(0))
                .build();

        Path6 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.500, 60.000), new Pose(132.000, 60.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path7 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(132.000, 60.000),
                                new Pose(104.486, 55.956),
                                new Pose(105.000, 105.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-130))
                .build();

        Path8 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(105, 105), new Pose(100.000, 35.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-130), Math.toRadians(0))
                .build();

        Path9 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(100.000, 35.500), new Pose(132.000, 35.500))
                )
                .setTangentHeadingInterpolation()
                .build();

        Path10 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(132.000, 35.500), new Pose(105,105 ))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-130))
                .build();
        Path11 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(105.000, 105.000), new Pose(120, 70))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-130), Math.toRadians(-180))
                .build();
    }
}