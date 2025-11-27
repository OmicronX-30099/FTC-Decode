package org.firstinspires.ftc.teamcode.Finals.Util

import com.pedropathing.geometry.Pose

enum class Alliance {
    BLUE {
        override val goalPose: Pose = Pose(4.0,140.0)
        override val resetPose1: Pose = Pose()
        override val resetPose2: Pose = Pose()
        override val resetPose3: Pose = Pose()
    },
    RED {
        override val goalPose: Pose = Pose(137.0,140.0)
        override val resetPose1: Pose = Pose()
        override val resetPose2: Pose = Pose()
        override val resetPose3: Pose = Pose()
    };

    abstract val goalPose: Pose
    abstract val resetPose1: Pose
    abstract val resetPose2: Pose
    abstract val resetPose3: Pose
}