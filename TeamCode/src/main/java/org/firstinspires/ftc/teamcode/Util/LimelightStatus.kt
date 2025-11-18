package org.firstinspires.ftc.teamcode.Util

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D
import kotlin.properties.Delegates

enum class LimelightStatus {
    // Status when targets are detected by limelight, includes tx, ty, ta values, botPose may need to be added
    TARGETS_DETECTED {
        // Override variables from the open variables in class
        // Went through lot of struggle to do Delegates.notNull() because lateinit does not work on primitive types
        override var targetX: Double by Delegates.notNull()
        override var targetY: Double by Delegates.notNull()
        override var targetArea: Double by Delegates.notNull()
        override var botPose: Pose3D by Delegates.notNull()

        // Method to set the tx, ty, and ta values, as defaults are null
        override fun setResults(tx: Double, ty: Double, ta: Double, bPose: Pose3D) {
            targetX = tx
            targetY = ty
            targetArea = ta
            botPose = bPose
        }
    },

    // Status for when results are null
    NO_TARGETS_DETECTED {},

    // Status for when results are invalid
    INVALID_RESULTS{};

    // Variables for the object
    // Went through lot of struggle to do Delegates.notNull() because lateinit does not work on primitive types
    open var targetX: Double by Delegates.notNull()
    open var targetY: Double by Delegates.notNull()
    open var targetArea: Double by Delegates.notNull()
    open var botPose: Pose3D by Delegates.notNull()

    open fun setResults(tx: Double, ty: Double, ta: Double, bPose: Pose3D) {}
}