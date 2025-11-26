package org.firstinspires.ftc.teamcode.Finals.Util

import com.pedropathing.geometry.Pose

enum class Alliance {
    BLUE { override val goalPose: Pose = Pose(2.5,137.0) },
    RED { override val goalPose: Pose = Pose(138.5,137.0) };

    abstract val goalPose: Pose
}