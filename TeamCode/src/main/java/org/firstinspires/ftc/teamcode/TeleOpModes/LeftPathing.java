package org.firstinspires.ftc.teamcode.TeleOpModes;

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

import org.firstinspires.ftc.teamcode.Robot.Constants;

@Autonomous(name="Left Side Auto - 12ball")
public class LeftPathing extends NextFTCOpMode {

    public LeftPathing() {
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
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.0005),
            new InstantCommand(() -> kicker.setPosition(0)),

            flywheelAccelShort,
            new Delay(0.25),

            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),
            new InstantCommand(() -> intake.setPower(1)),

            flywheelAccelShort,
            new Delay(0.2),
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),

            flywheelAccelShort,
            new Delay(0.2),
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),

            new Delay(0.5),
            new InstantCommand(() -> fwm.setPower(-0.3))
    );
    public Command shootSequenceFar = new SequentialGroup(
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.0005),
            new InstantCommand(() -> kicker.setPosition(0)),

            flywheelAccelFar,
            new InstantCommand(() -> intake.setPower(1)),

            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),

            flywheelAccelFar,
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),

            flywheelAccelFar,
            //push fix
            new InstantCommand(() -> kicker.setPosition(0.25)),
            new Delay(0.15),
            new InstantCommand(() -> kicker.setPosition(0)),

            new Delay(0.5),
            new InstantCommand(() -> fwm.setPower(-0.3))
    );




    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(105, 135, Math.toRadians(270)));
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
                        new BezierLine(new Pose(39.000, 135.000), new Pose(39.000, 105.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-50))
                .build();

        Path2 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(39.000, 105.000),
                                new Pose(54.895, 97.591),
                                new Pose(44.000, 85.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-50), Math.toRadians(-180))
                .build();

        Path3 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(44.000, 85.000), new Pose(17.5, 85.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-180))
                .build();

        Path4 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(17.000, 85.000), new Pose(39.000, 105.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-50))
                .build();
        //complete
        Path5 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(39.000,105.000), new Pose(41.500,60.00))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-50), Math.toRadians(-180))
                .build();

        Path6 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(41.500, 60.000), new Pose(12.000, 60.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-180))
                .build();

        Path7 = follower()
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(12.000, 60.000),
                                new Pose(39.514, 55.956),
                                new Pose(39.000, 105.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-50))
                .build();

        Path8 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(39, 105), new Pose(44.000, 35.500))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-50), Math.toRadians(-180))
                .build();

        Path9 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(44.000, 35.500), new Pose(12.000, 35.500))
                )
                .setTangentHeadingInterpolation()
                .build();

        Path10 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(12.000, 35.500), new Pose(39,105 ))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-50))
                .build();
        Path11 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(105.000, 105.000), new Pose(120, 70))
                )
                .setLinearHeadingInterpolation(Math.toRadians(-50), Math.toRadians(0))
                .build();
    }
}