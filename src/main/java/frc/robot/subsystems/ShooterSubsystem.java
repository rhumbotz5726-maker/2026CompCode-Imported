package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ShooterSubsystem {

    SparkMax shooterMotor = new SparkMax(1, MotorType.kBrushless);
    SparkMax shooterPID = new SparkMax(0, MotorType.kBrushless);

    public void setShooterSpeed(int speed) {
        shooterMotor.set(speed);
    }

    public void setPIDSpeed(int speed) {
        shooterPID.set(speed);
    }
    
}
