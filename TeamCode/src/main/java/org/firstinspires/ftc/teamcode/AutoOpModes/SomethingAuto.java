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
    private final Command shootSequenceShort = new SequentialGroup(
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
                        new BezierLine(new Pose(56.000, 8.000), new Pose(39.863, 34.936))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .build());


        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(39.863, 34.936), new Pose(15.229, 35.160))
                )
                .setTangentHeadingInterpolation()
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(15.229, 35.160),
                                new Pose(25.530, 50.837),
                                new Pose(72.784, 23.739)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(72.784, 23.739), new Pose(38.743, 59.347))
                )
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(38.743, 59.347), new Pose(14.781, 59.795))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(14.781, 59.795),
                                new Pose(20.603, 71.216),
                                new Pose(40.759, 76.143),
                                new Pose(59.571, 83.981)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(59.571, 83.981), new Pose(34.936, 108.392))
                )
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(135))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(34.936, 108.392), new Pose(46.134, 83.533))
                )
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(46.134, 83.533), new Pose(14.781, 83.533))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build());

        Paths.add(follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(14.781, 83.533), new Pose(33.816, 108.168))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(135))
                .build());

    }
    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(34.4, 134.370, Math.toRadians(270)));
        BuildPath();
    }

    @Override
    public void onStartButtonPressed() {

    }
}

