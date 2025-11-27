package org.firstinspires.ftc.teamcode.Finals.Util

import com.pedropathing.geometry.Pose

enum class Alliance {
    BLUE { override val goalPose: Pose = Pose(4.0,140.0) },
    RED { override val goalPose: Pose = Pose(137.0,140.0) };

    abstract val goalPose: Pose
}