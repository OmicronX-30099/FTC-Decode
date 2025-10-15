package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.LimelightSubsystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp(name="Limelight Nextftc Test", group="TestOpModes")
public class LimelightTeleOp extends NextFTCOpMode {
    public LimelightTeleOp() {
        addComponents(
            new SubsystemComponent(LimelightSubsystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onUpdate() {
        LimelightSubsystem.INSTANCE.getResult();
    }
}