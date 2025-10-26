package org.firstinspires.ftc.teamcode.TeleOpModes;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSubsystemGroup;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Turret Aim Tester", group="Tests")
public class TAimTest extends NextFTCOpMode {
    public TAimTest() {
        addComponents(
                new SubsystemComponent(ShooterSubsystemGroup.INSTANCE),
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }
    @Override
    public void onUpdate() {
        ShooterSubsystemGroup.INSTANCE.autoShooter(follower().getPose());
    }
}
