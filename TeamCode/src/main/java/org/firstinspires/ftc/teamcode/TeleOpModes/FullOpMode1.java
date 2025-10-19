package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Constants.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.HoodSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSystem;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSystem;
import org.firstinspires.ftc.teamcode.Subsystems.TurretSubsystem;

import dev.nextftc.core.commands.conditionals.IfElseCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.ActiveOpMode;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(group="FullOpModes", name = "Final OpMode Draft 1")
public class FullOpMode1 extends NextFTCOpMode {
    public FullOpMode1() {
        addComponents(
                new SubsystemComponent(ShooterSystem.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    private Follower follower;

    private final MotorEx frontLeftMotor = new MotorEx("fl");
    private final MotorEx frontRightMotor = new MotorEx("fr");
    private final MotorEx backLeftMotor = new MotorEx("bl");
    private final MotorEx backRightMotor = new MotorEx("br");
    private MecanumDriverControlled driverControlled;

    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");
    public MotorGroup fwmotor = new MotorGroup(fwl,fwr);

    @Override
    public void onInit() {
        follower = Constants.createFollower(ActiveOpMode.hardwareMap());
    }

    @Override
    public void onStartButtonPressed() {
        driverControlled = new MecanumDriverControlled(frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.named("Drivetrain").schedule();

        // Commented out with intention to test/tune/calculate auto flywheel

        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> fwmotor.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> fwmotor.setPower(0)
        );

        Gamepads.gamepad1().circle()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.highAngle)
        ;
        Gamepads.gamepad1().square()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.lowAngle)
        ;
        Gamepads.gamepad1().triangle()
                .whenBecomesTrue(HoodSubsystem.INSTANCE.midAngle)
        ;
        Gamepads.gamepad1().cross()
                .whenTrue(new IfElseCommand(
                        () -> IntakeSystem.INSTANCE.getPower()==0,
                        IntakeSystem.INSTANCE.activateIntake,
                        IntakeSystem.INSTANCE.deactivateIntake
                ))
                .whenBecomesFalse(IntakeSystem.INSTANCE.deactivateIntake)
        ;
    }

    @Override
    public void onUpdate() {
        double x = follower.getPose().getX();
        double y = follower.getPose().getY();
        double heading = follower.getHeading();
        TurretSubsystem.INSTANCE.setTurretHeading(x, y, heading);

        /*
        FlywheelSubsystem.INSTANCE.calibrateFlywheelVelocity();
        */
    }
}