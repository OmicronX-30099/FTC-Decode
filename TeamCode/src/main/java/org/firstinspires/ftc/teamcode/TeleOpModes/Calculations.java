package org.firstinspires.ftc.teamcode.TeleOpModes;

public class Calculations {
    public double hood_angle(double x_r, double y_r, double z_max, double x_t, double y_t, double z_t) {
        double angle = (180 / Math.PI) * Math.atan((2 * ((z_max - 15) + Math.sqrt((z_max - 15) * (z_max - z_t)))) / (((x_t - x_r) * (x_t - x_r)) + ((y_t - y_r) * (y_t - y_r))));
        return angle;
    }
    public void flywheel_vel() {}
}