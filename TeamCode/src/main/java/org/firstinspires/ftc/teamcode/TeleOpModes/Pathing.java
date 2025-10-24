package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.ParallelGroup;
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

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import org.firstinspires.ftc.teamcode.Robot.Constants;

@Autonomous(name="Full pathing")
public class Pathing extends NextFTCOpMode {

    public Pathing() {
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

    public Command realignBalls = new SequentialGroup(
            new InstantCommand(() -> {fwm.setPower(-0.3); intake.setPower(-0.2);}),
            new Delay(0.75)
        );
    public Command push = new SequentialGroup(
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0))
            );

    public void Paths() {
        Path1 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(105.000, 135), new Pose(115, 115))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-135))
                .build();

        Path2 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(115, 115),
                                new Pose(99.182, 112.000),
                                new Pose(102.895, 84.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(0))
                .build();

        Path3 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.895, 84.000), new Pose(125.000, 84.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path4 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(125.000, 84.000), new Pose(115, 115))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-135))
                .build();

        Path5 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(115, 115),
                                new Pose(86.718, 76.110),
                                new Pose(102.630, 59.500)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(0))
                .build();

        Path6 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(102.630, 59.500), new Pose(128.000, 59.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path7 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(128.000, 59.500),
                                new Pose(98.387, 60.464),
                                new Pose(115, 115)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-135))
                .build();

        Path8 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(115, 115), new Pose(100.000, 35.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-135), Math.toRadians(0))
                .build();

        Path9 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(100.000, 35.500), new Pose(130.000, 35.500))
                )
                .setTangentHeadingInterpolation()
                .build();

        Path10 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(130.000, 35.500),
                                new Pose(106.343, 56.486),
                                new Pose(115, 115)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-135))
                .build();
    }

    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(108, 134.718232044198, Math.toRadians(270)));
        Paths();
    }
    @Override
    public void onStartButtonPressed() {
        kicker.setPosition(0);
        push.schedule();
        new SequentialGroup(
                push,
                new FollowPath(Path1, true),
                new Delay(2),
                new FollowPath(Path2, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path3, true),
                new InstantCommand(() -> intake.setPower(0.25)),
                new FollowPath(Path4, true),
                realignBalls,
                new InstantCommand(() -> fwm.setPower(0.7)),
                new InstantCommand(() -> intake.setPower(1)),
                new Delay(1),
                new InstantCommand(() -> fwm.setPower(0.4)),
                new Delay(1),
                push,
                new InstantCommand(() -> fwm.setPower(0.2)),
                new FollowPath(Path5, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path6, true),
                new InstantCommand(() -> intake.setPower(0.2)),
                new FollowPath(Path7, true),
                realignBalls,
                new InstantCommand(() -> fwm.setPower(0.7)),
                new InstantCommand(() -> intake.setPower(1)),
                new Delay(1),
                new InstantCommand(() -> fwm.setPower(0.4)),
                new Delay(1),
                push,
                new InstantCommand(() -> fwm.setPower(0.2)),
                new FollowPath(Path8, true),
                new InstantCommand(() -> intake.setPower(1)),
                new FollowPath(Path9, true),
                new InstantCommand(() -> intake.setPower(0.2)),
                new FollowPath(Path10, true),
                realignBalls,
                new InstantCommand(() -> fwm.setPower(0.7)),
                new InstantCommand(() -> intake.setPower(1)),
                new Delay(1),
                new InstantCommand(() -> fwm.setPower(0.4)),
                new Delay(1),
                push,
                new InstantCommand(() -> fwm.setPower(0.2))
        ).schedule();
    }
}
