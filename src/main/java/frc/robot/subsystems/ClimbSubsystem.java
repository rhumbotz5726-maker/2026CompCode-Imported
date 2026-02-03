package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ClimbSubsystem extends SubsystemBase{
    
    SparkMax climbMotor = new SparkMax(2, MotorType.kBrushless);
    SparkMax climbMotor2 = new SparkMax(5, MotorType.kBrushless);
    AbsoluteEncoder encoder = climbMotor.getAbsoluteEncoder();

    public void setSpeed(double speed) {
        climbMotor.set(speed);
        climbMotor2.set(speed);
    }

    public void setSpeed(int motorNum, double speed) {
        if (motorNum == 1) {
            climbMotor.set(speed);
        } else if (motorNum == 2) {
            climbMotor2.set(speed);
        }
    }

    public double getPos() {
        return encoder.getPosition();
    }

    public void periodic() {
        SmartDashboard.putNumber("Climb encoder: ", getPos());
    }
    
}
