package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class IndexerSubsystem {
    SparkMax indexerMotor = new SparkMax(16, MotorType.kBrushless);

    public void setSpeed(double speed) {
        indexerMotor.set(speed);
    }
}
