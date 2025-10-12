package org.firstinspires.ftc.teamcode.Systems;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.Servo;
import dev.nextftc.control.ControlSystem;
@Configurable
public class HoodSubsystem {
    public static HoodSubsystem INSTANCE = new HoodSubsystem();
    public ControlSystem HoodControl = ControlSystem.builder()
            .posPid(0, 0, 0)
            .build()
            ;
    /*
    Angle Equation
    x, y, z_m, z_tg, x_tg, y_tg
    (180/pi)*arctan([(2[z_m - 15)+ √(z_m - 15)(z_m - z_tg)]
     */
    public double calculateVal(double x, double y, double z_m, double z_tg, double x_tg, double y_tg) {
        // Achintya pls don't turn this into one variable Ik its ineffcient but it'll be absolutely horrid to look at
        double numerator = 2*((z_m - 15) + Math.sqrt((z_m -15)*(z_m - z_tg)));
        double denominator = Math.sqrt(Math.pow(x_tg - x, 2) + Math.pow(y_tg - y, 2));
        double angle = (180/Math.PI)*Math.atan(numerator / denominator);

        return angle/360;
    }
}
