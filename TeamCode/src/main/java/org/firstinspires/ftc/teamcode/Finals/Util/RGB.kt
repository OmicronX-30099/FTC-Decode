package org.firstinspires.ftc.teamcode.Finals.Util

enum class RGB {
    RED { override val position: Double = 0.28 },
    ORANGE { override val position: Double = 0.32 },
    YELLOW { override val position: Double = 0.388 },
    GREEN { override val position: Double = 0.475 },
    AZURE { override val position: Double = 0.59 },
    BLUE { override val position: Double = 0.64 },
    INDIGO { override val position: Double = 0.67 },
    OFF { override val position: Double = 0.0 },
    WHITE { override val position: Double = 1.0 };

    abstract val position: Double
}