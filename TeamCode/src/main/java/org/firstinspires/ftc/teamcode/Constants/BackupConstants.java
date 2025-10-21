package org.firstinspires.ftc.teamcode.Constants;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BackupConstants {
    public static FollowerConstants followerConstants = new FollowerConstants();

    public static TwoWheelConstants localizerConstants = new TwoWheelConstants()
        .forwardEncoder_HardwareMapName("fl")
        .strafeEncoder_HardwareMapName("intake")
        .IMU_HardwareMapName("imu")
        .IMU_Orientation(
            new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
            )
        )
        .forwardPodY(2.5)
        .strafePodX(-6.375)
        .forwardEncoderDirection(Encoder.FORWARD)
        .strafeEncoderDirection(Encoder.REVERSE)
        ;

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .twoWheelLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}