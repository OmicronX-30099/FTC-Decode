package org.firstinspires.ftc.teamcode.Util

import com.pedropathing.geometry.Pose

enum class Alliance {
    BLUE {
        override val goalPose: Pose
            get() = Pose(0.0,141.0)
    },
    RED {
        override val goalPose: Pose
            get() = Pose(141.0,141.0)
    };

    abstract val goalPose: Pose
}