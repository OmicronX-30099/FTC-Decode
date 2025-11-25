package org.firstinspires.ftc.teamcode.Tests.OtherTesting;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import dev.nextftc.hardware.impl.ServoEx;

@Configurable
@TeleOp
public class ServoTester extends OpMode {

    public Servo testServo;
    public static String configName = "";
    public static Double position = 0.0;

    @Override
    public void init() {

    }
    @Override
    public void loop() {
        testServo = hardwareMap.get(Servo.class, configName);
        testServo.setPosition(position);
    }
}
