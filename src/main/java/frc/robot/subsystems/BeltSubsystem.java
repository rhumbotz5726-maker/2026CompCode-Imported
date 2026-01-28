package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class BeltSubsystem extends SubsystemBase{
    SparkMax beltMotor1 = new SparkMax(3, MotorType.kBrushless);
    SparkMax beltMotor2 = new SparkMax(4, MotorType.kBrushless);

    public void setSpeed(double speed) {
        beltMotor1.set(speed);
        beltMotor2.set(speed);
    }
}
