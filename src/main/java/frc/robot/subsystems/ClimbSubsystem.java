package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ClimbSubsystem {
    
    SparkMax climbMotor = new SparkMax(2, MotorType.kBrushless);

    public void setSpeed(int speed) {
        climbMotor.set(speed);
    }
    
}
