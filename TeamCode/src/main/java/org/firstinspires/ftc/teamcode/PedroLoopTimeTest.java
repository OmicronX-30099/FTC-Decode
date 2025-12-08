package org.firstinspires.ftc.teamcode;

import static java.lang.Math.PI;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp("Pedro tester", group="Loop Tests")
public class PedroLoopTimeTest extends OpMode {
    private ElapsedTime elapsedtime;
    private Follower follower;
    public static Pose startingPose = new Pose(72.0,72.0,PI);

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingPose);
        follower.update();
        elapsedtime = new ElapsedTime();
        elapsedtime.reset();
    }

    @Override
    public void start() {
        follower.startTeleopDrive();
    }
    @Override
    public void loop() {
        //Call this once per loop
        follower.update();

        follower.setTeleOpDrive(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x,
                    true // Robot Centric
        );

        telemetry.addData("position", follower.getPose());
        telemetry.addData("velocity", follower.getVelocity());
        telemetry.addData("Loop Times", elapsedtime.milliseconds());
        elapsedtime.reset();
    }
}
