package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.conditionals.IfElseCommand;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "Weirdo OpMode",group="TestOpModes")
public class    WeirdOpMode extends NextFTCOpMode {
    public WeirdOpMode() {
        addComponents(
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    // change the names and directions to suit your robot
    private final MotorEx frontLeftMotor = new MotorEx("fl").reversed();
    private final MotorEx frontRightMotor = new MotorEx("fr");
    private final MotorEx backLeftMotor = new MotorEx("bl").reversed();
    private final MotorEx backRightMotor = new MotorEx("br");
    /*private final MotorEx fwl = new MotorEx("fwl").reversed();
    private final MotorEx fwr = new MotorEx("fwr");
    private final MotorGroup fwmotors = new MotorGroup(fwl,fwr);
    private final MotorEx turret = new MotorEx("tur");*/

    private MecanumDriverControlled driverControlled;

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
        /*Gamepads.gamepad1().dpadDown()
                .whenBecomesTrue(
                        new IfElseCommand(
                                () -> driverControlled.getScalar() == 1,
                                new InstantCommand(() -> driverControlled.setScalar(0.5)),
                                new InstantCommand(() -> driverControlled.setScalar(1))
                        )
                )
        ;

        Gamepads.gamepad1().rightTrigger().greaterThan(0.05)
                .whenTrue(() -> fwmotors.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> fwmotors.setPower(0))
        ;
        Gamepads.gamepad1().rightBumper()
                .whenTrue(() -> turret.setPower(0.1))
                .whenBecomesFalse(() -> turret.setPower(0))
        ;
        Gamepads.gamepad1().leftBumper()
                .whenTrue(
                        new IfElseCommand(
                                () -> !Gamepads.gamepad1().leftBumper().get(),
                                new InstantCommand(() -> turret.setPower(-0.1)),
                                new InstantCommand(() -> {})
                        )
                )
                .whenBecomesFalse(
                        new IfElseCommand(
                                () -> !Gamepads.gamepad1().rightBumper().get(),
                                new InstantCommand(() -> turret.setPower(0)),
                                new InstantCommand(() -> {})
                        )
                )
        ;*/
    }
}
