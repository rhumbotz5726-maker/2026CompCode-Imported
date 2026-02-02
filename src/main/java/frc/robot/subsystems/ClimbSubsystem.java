package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ClimbSubsystem extends SubsystemBase{
    
    SparkMax climbMotor = new SparkMax(2, MotorType.kBrushless);
    AbsoluteEncoder encoder = climbMotor.getAbsoluteEncoder();

    public void setSpeed(double speed) {
        climbMotor.set(speed);
    }

    public double getPos() {
        return encoder.getPosition();
    }
    
}
