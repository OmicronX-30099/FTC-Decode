package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Robot.Constants;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.delays.WaitUntil;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.extensions.pedro.PedroDriverControlled;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;
import static org.firstinspires.ftc.teamcode.TeleOpModes.Pathing.current_pose;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Test This Maximus For Teleop")
public class NewTeleOp extends NextFTCOpMode {
    public NewTeleOp() {
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }
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

    public PathChain toShort;
    public PathChain toFar;

    public DriverControlledCommand driverControlled;

    @Override
    public void onInit() {
        //follower().setStartingPose(current_pose);
        buildCommands();
    }
    /*
    public void buildPaths() {
        toShort = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(follower().getPose(), new Pose(105.000, 105.000))
                )
                .setLinearHeadingInterpolation(follower().getHeading(), Math.toRadians(-135))
                .build();
        toFar = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(follower().getPose(), new Pose(85.000, 85.000))
                )
                .setLinearHeadingInterpolation(follower().getHeading(), Math.toRadians(-130))
                .build();
    }*/

    public void buildCommands() {
        push = new SequentialGroup(
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0))
                );

        flywheelAccelShort = new SequentialGroup(
                new InstantCommand(() -> fwm.setPower(1)),
                new InstantCommand(() -> hood.setPosition(0)),
                new WaitUntil(() -> fwm.getVelocity() >= 1020),
                new InstantCommand(() -> fwm.setPower(0.4))
        );
        flywheelAccelFar = new SequentialGroup(
                new InstantCommand(() -> fwm.setPower(1)),
                new InstantCommand(() -> hood.setPosition(0.3)),
                new WaitUntil(() -> fwm.getVelocity() >= 1260),
                new InstantCommand(() -> fwm.setPower(0.52))
        );
        shootSequenceShort = new SequentialGroup(
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.0005),
                new InstantCommand(() -> kicker.setPosition(0)),

                flywheelAccelShort,
                new InstantCommand(() -> intake.setPower(1)),

                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),

                flywheelAccelShort,
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),

                flywheelAccelShort,
                //push fix
                new InstantCommand(() -> kicker.setPosition(0.25)),
                new Delay(0.15),
                new InstantCommand(() -> kicker.setPosition(0)),

                new Delay(0.75),
                new InstantCommand(() -> fwm.setPower(-0.3))
        );
        shootSequenceFar = new SequentialGroup(
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

                new Delay(0.72),
                new InstantCommand(() -> fwm.setPower(-0.3))
        );
    }
    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightBumper().whenBecomesTrue(
                shootSequenceShort
        );
        Gamepads.gamepad1().leftBumper().whenBecomesTrue(
                shootSequenceFar
        );
        driverControlled = new PedroDriverControlled(
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad2().rightStickX()
        );
        driverControlled.setScalar(0.7);
        driverControlled.schedule();
    }

    @Override
    public void onUpdate() {
        intake.setPower(Gamepads.gamepad1().rightTrigger().get()-Gamepads.gamepad1().leftTrigger().get());
    }
}
