package org.firstinspires.ftc.teamcode.AutoOpModes;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import org.firstinspires.ftc.teamcode.Constants.Constants;

import java.util.ArrayList;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
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

@Autonomous(name = "Red Something Auto")
public class SomethingAuto extends NextFTCOpMode {
    public SomethingAuto() {
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
    public ArrayList<PathChain> Paths = new ArrayList<PathChain>();
    private final Command push = new SequentialGroup(
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0))
    ).requires(kicker);

    private final Command flywheelAccelShort = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new InstantCommand(() -> hood.setPosition(0)),
            new WaitUntil(() -> fwm.getVelocity() >= 1030),
            new InstantCommand(() -> fwm.setPower(0.42))
    );
    private final Command flywheelAccelFar = new SequentialGroup(
            new InstantCommand(() -> fwm.setPower(1)),
            new InstantCommand(() -> hood.setPosition(0.3)),
            new WaitUntil(() -> fwm.getVelocity() >= 1260),
            new InstantCommand(() -> fwm.setPower(0.52))
    );
    private final Command shootSeq = new SequentialGroup(
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.0005),
            new InstantCommand(() -> kicker.setPosition(0)),
            flywheelAccelShort,
            new Delay(0.25),
            push,
            new InstantCommand(() -> intake.setPower(1)),
            flywheelAccelShort,
            new Delay(0.2),
            push,
            flywheelAccelShort,
            new Delay(0.2),
            push,
            new Delay(0.5),
            new InstantCommand(() -> fwm.setPower(-0.3))
    );

    private void BuildPath() {
        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(56.000, 8.000), new Pose(71.664, 23.291))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(71.664, 23.291), new Pose(39.863, 35.160))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(39.863, 35.160), new Pose(14.109, 35.832))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.109, 35.832), new Pose(71.440, 23.291))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(71.440, 23.291),
                                new Pose(67.185, 58.899),
                                new Pose(38.967, 59.347)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(38.967, 59.347), new Pose(13.885, 59.347))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(13.885, 59.347),
                                new Pose(41.655, 75.471),
                                new Pose(71.664, 72.336)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(71.664, 72.336), new Pose(36.280, 106.824))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(36.280, 106.824),
                                new Pose(48.821, 88.908),
                                new Pose(39.639, 83.981)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(39.639, 83.981), new Pose(13.885, 83.533))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(13.885, 83.533), new Pose(36.280, 106.824))
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build());
    }
    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(34.4, 134.370, Math.toRadians(270)));
        BuildPath();
    }

    @Override
    public void onStartButtonPressed() {
        kicker.setPosition(0);
        new SequentialGroup(
            new FollowPath(Paths.get(0)),
            shootSeq,
            new FollowPath(Paths.get(1)),
            new InstantCommand(() -> intake.setPower(1)),
            new FollowPath(Paths.get(2)),
            new InstantCommand(() -> intake.setPower(0)),
            new Delay(0.25),
            new FollowPath(Paths.get(3)),
            shootSeq,
            new FollowPath(Paths.get(4)),
            new InstantCommand (() -> intake.setPower(1)),
            new FollowPath(Paths.get(5)),
            new InstantCommand(() -> intake.setPower(0)),
            new FollowPath(Paths.get(6)),
            shootSeq,
            new FollowPath(Paths.get(7)),
            new FollowPath(Paths.get(8)),
            new InstantCommand(() -> intake.setPower(1)),
            new FollowPath(Paths.get(9)),
            new InstantCommand(() -> intake.setPower(0)),
            new FollowPath(Paths.get(10)),
            shootSeq).schedule();




    }
}

