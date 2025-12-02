import com.pedropathing.geometry.Pose
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem
import org.firstinspires.ftc.teamcode.Finals.Systems.ShooterSystem.goalPose
import kotlin.math.sqrt

class flywheelSpeedLookUp {
    val flywheelEquationA: Double = -24.993970450383305
    val flywheelEquationB: Double = -0.07154455960209152
    val flywheelEquationC: Double = 0.00019278705563574583
    val flywheelEquationD: Double = 116.26277827556967
    val flywheelEquationE: Double = -0.272675259634784
    val flywheelEquationF: Double = 122.99485320234648
    val goalPose: Pose = Pose(6.0, 140.0)
    val currPose: Pose  = Pose(60.0, 23.0)
    val distanceFromGoal: Double = currPose.distanceFrom(goalPose)
    val hoodPosition: Double = 1.0
    val quadCoeffA: Double = ShooterSystem.flywheelEquationC
    val quadCoeffB: Double = (ShooterSystem.flywheelEquationB * hoodPosition) + ShooterSystem.flywheelEquationE
    val quadCoeffC: Double = (ShooterSystem.flywheelEquationA * hoodPosition * hoodPosition) + (ShooterSystem.flywheelEquationD * hoodPosition) + (ShooterSystem.flywheelEquationF - distanceFromGoal)
    val discriminant: Double = (quadCoeffB * quadCoeffB) - (4 * quadCoeffA * quadCoeffC)
    val velocity = (-1 * quadCoeffB + sqrt(discriminant)) / (2 * quadCoeffA)
}