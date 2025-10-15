package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import java.util.Map;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.ftc.ActiveOpMode;

public class LimelightSubsystem implements Subsystem {
    public static final LimelightSubsystem INSTANCE = new LimelightSubsystem();
    private LimelightSubsystem() { }

    public Limelight3A limelight;

    public Map<Integer, String> motif = Map.of(
            21, "GPP",
            22, "PGP",
            23, "PPG"
    );

    @Override
    public void initialize() {
        limelight = ActiveOpMode.hardwareMap().get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(5);
        limelight.start();
    }

    public void getResult() {
        LLResult result = limelight.getLatestResult();
        ActiveOpMode.telemetry().addData("Limelight", "Data");
        if (result != null && result.isValid()) {
            // Get AprilTag data from the result
            int tagId = result.getFiducialResults().get(0).getFiducialId();
            String sequence = motif.get(tagId);
            // Display data to the Driver Station
            ActiveOpMode.telemetry().addData("Limelight", "TagId"+tagId);
            ActiveOpMode.telemetry().addData("Limelight", "Sequence: "+sequence);

        } else {
            ActiveOpMode.telemetry().addData("Limelight", "Status: No AprilTag visible");
        }
        ActiveOpMode.telemetry().update();
    }
}
