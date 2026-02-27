package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class BeltSubsystem extends SubsystemBase{
    SparkMax beltMotor = new SparkMax(15, MotorType.kBrushless);

    public void setSpeed(double speed) {
        beltMotor.set(speed);
    } 
}
