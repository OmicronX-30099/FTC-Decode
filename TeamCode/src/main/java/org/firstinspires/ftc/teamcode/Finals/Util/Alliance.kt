package org.firstinspires.ftc.teamcode.Finals.Util

import com.pedropathing.geometry.Pose

enum class Alliance {
    RED {
        override val goalPose: Pose = Pose(137.5,140.0)
        override val resetPose1: Pose = Pose(8.9,8.7,Math.toRadians(180.0))
        override val resetPose2: Pose = Pose(47+8.9,8.7,Math.toRadians(180.0))
        override val resetPose3: Pose = Pose(141.5-47+8.7, 141.5-8.9, Math.toRadians(90.0))
        var lastAutoPose: Pose = Pose()
    },
    BLUE {
        override val goalPose: Pose = Pose(4.0,140.0)
        override val resetPose1: Pose = Pose(141.5-8.9,8.7,Math.toRadians(0.0))
        override val resetPose2: Pose = Pose(141.5-47-8.9,8.7,Math.toRadians(0.0))
        override val resetPose3: Pose = Pose(47-8.7, 141.5-8.9, Math.toRadians(90.0))
        var lastAutoPose: Pose = Pose()
    };

    abstract val goalPose: Pose
    abstract val resetPose1: Pose
    abstract val resetPose2: Pose
    abstract val resetPose3: Pose
}