package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem {

    SparkMax intakeMotor = new SparkMax(0, MotorType.kBrushless);

    public void setSpeed(int speed) {
        intakeMotor.set(speed);
    }




}