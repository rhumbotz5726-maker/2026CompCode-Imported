package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public class BeltSubsystem {
    SparkMax beltMotor1 = new SparkMax(3, MotorType.kBrushless);
}
