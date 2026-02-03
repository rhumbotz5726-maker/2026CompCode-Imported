package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class LimelightSubsystem extends SubsystemBase{
    
    public void periodic() {
    SmartDashboard.putNumber("limelightTx", LimelightHelpers.getTX(""));
    SmartDashboard.putNumber("limelightTy", LimelightHelpers.getTY(""));
    SmartDashboard.putBoolean("limelightTz", LimelightHelpers.getTV(""));
    //SmartDashboard.putNumber("Limelight Distance", LimelightHelpers.getDistance(""));
    SmartDashboard.putNumber("Apriltag ID", LimelightHelpers.getFiducialID(""));
    }
}
