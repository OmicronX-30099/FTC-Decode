package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Systems.HoodSubsystem;

@TeleOp(name = "Hood TeleOp")
public class AutoHood {
    HoodSubsystem subSystem = HoodSubsystem.INSTANCE;
    public AutoHood() {
    }
    public void onUpdate() {
        subSystem.SetServo();
    }
}
