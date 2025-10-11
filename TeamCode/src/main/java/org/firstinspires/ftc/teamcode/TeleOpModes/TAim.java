package org.firstinspires.ftc.teamcode.TeleOpModes;

import static org.firstinspires.ftc.teamcode.Systems.TurretSubsystem.*;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Systems.TurretSubsystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

@TeleOp(name = "Turret AutoAim Opmode")
public class TAim extends NextFTCOpMode {
    public TAim() {
        addComponents(
                new SubsystemComponent(TurretSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    @Override
    public void onUpdate() {
        TurretSubsystem.INSTANCE.aimBot(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading());
        ActiveOpMode.telemetry().addData("heading",follower().getHeading());
        ActiveOpMode.telemetry().addData("X",follower().getPose().getX());
        ActiveOpMode.telemetry().addData("Y",follower().getPose().getY());
        ActiveOpMode.telemetry().addData("ticks",TurretSubsystem.INSTANCE.calculate(follower().getPose().getX(), follower().getPose().getY(), follower().getHeading()));
        ActiveOpMode.telemetry().addData("power",turretControl.calculate(turretMotor.getState()));
        ActiveOpMode.telemetry().update();
    }
}