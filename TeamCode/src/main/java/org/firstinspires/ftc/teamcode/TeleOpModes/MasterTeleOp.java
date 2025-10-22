package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Robot.Constants;
import org.firstinspires.ftc.teamcode.Robot.IntakeSubsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Robot.IntakeSystem;
import org.firstinspires.ftc.teamcode.Robot.ShooterSystem;

import dev.nextftc.core.commands.conditionals.IfElseCommand;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Master TeleOp")
public class MasterTeleOp extends NextFTCOpMode {
    public MasterTeleOp() {
        addComponents(
                new SubsystemComponent(IntakeSystem.INSTANCE, ShooterSystem.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    @Override
    public void onInit() {
        follower().setStartingPose(new Pose(24,24));
    }
    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightBumper()
                .whenBecomesTrue(IntakeSystem.INSTANCE.kick)
        ;
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(IntakeSubsystem.INSTANCE.activateIntake)
        ;
        ;
        Gamepads.gamepad1().leftTrigger().greaterThan(0)
                .whenTrue(IntakeSubsystem.INSTANCE.outtake)
        ;
    }

    @Override
    public void onUpdate() {
        ShooterSystem.INSTANCE.autoAim(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading());
        telemetry.update();
        IntakeSubsystem.INSTANCE.intakeMotor.setPower(Gamepads.gamepad1().rightTrigger().get()-Gamepads.gamepad1().leftTrigger().get());
    }
}
