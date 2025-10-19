package org.firstinspires.ftc.teamcode.TeleOpModes;

import android.renderscript.ScriptGroup;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;

@TeleOp(name="stuff")
public class stuff extends NextFTCOpMode {
    public stuff() {
        addComponents(
                BindingsComponent.INSTANCE,
                BulkReadComponent.INSTANCE
        );

    }
    public MotorEx fwl = new MotorEx("fwl").reversed();
    public MotorEx fwr = new MotorEx("fwr");

    public MotorGroup fwm = new MotorGroup(fwl, fwr);

    public MotorEx intake = new MotorEx("intake");
    public ServoEx pusher = new ServoEx("k");

    @Override
    public void onStartButtonPressed() {
        Gamepads.gamepad1().rightTrigger().greaterThan(0)
                .whenTrue(() -> fwm.setPower(Gamepads.gamepad1().rightTrigger().get()))
                .whenBecomesFalse(() -> fwm.setPower(0))
        ;
        Gamepads.gamepad1().leftTrigger().greaterThan(0)
                .whenTrue(() -> intake.setPower(Gamepads.gamepad1().leftTrigger().get()))
                .whenBecomesFalse(() -> intake.setPower(0))
        ;
        Gamepads.gamepad1().circle().whenBecomesTrue(() -> pusher.setPosition(0));
        Gamepads.gamepad1().triangle().whenBecomesTrue(() -> pusher.setPosition(0.25));
    }
}