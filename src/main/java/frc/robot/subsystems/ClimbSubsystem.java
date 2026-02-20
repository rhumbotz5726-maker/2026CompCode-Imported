package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ClimbSubsystem extends SubsystemBase{
    
    SparkMax climbMotor1 = new SparkMax(2, MotorType.kBrushless);
    SparkMax climbMotor2 = new SparkMax(5, MotorType.kBrushless);
    AbsoluteEncoder encoder1 = climbMotor1.getAbsoluteEncoder();
    AbsoluteEncoder encoder2 = climbMotor2.getAbsoluteEncoder();


    public void setSpeed(double speed) {
        climbMotor1.set(speed);
        climbMotor2.set(speed);
    }

    public void setSpeed(int motorNumber, double speed) {
        if (motorNumber == 1) {
            climbMotor1.set(speed);
        } else if (motorNumber == 2) {
            climbMotor2.set(speed);
        }
    }

    public double getPosOne() {
        return encoder1.getPosition();
    }

    public double getPosTwo() {
        return encoder1.getPosition();
    }

    public void periodic() {
        SmartDashboard.putNumber("Climb encoder 1: ", getPosOne());
        SmartDashboard.putNumber("Climb encoder 2: ", getPosTwo());
    }
    
}
