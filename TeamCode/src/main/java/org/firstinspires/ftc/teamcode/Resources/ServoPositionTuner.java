package org.firstinspires.ftc.teamcode.Resources;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoPositionTuner extends OpMode {
    public static double position = 0.0;

    public Servo test_servo;
    public static String servoName = "";

    @Override
    public void init() {
        test_servo = hardwareMap.get(Servo.class, servoName);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        test_servo.setPosition(position);
        telemetry.addData("Current Position", position);
    }
}