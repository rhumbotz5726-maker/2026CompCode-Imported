package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    SparkMax intakeMotor = new SparkMax(0, MotorType.kBrushless);
    SparkMax pidMotor = new SparkMax(2, MotorType.kBrushless);
    SparkAbsoluteEncoder encoder = pidMotor.getAbsoluteEncoder();

    public void intakeSetSpeed(double speed) {
        intakeMotor.set(speed);
    }

    public void pidSetSpeed(double speed) {
        pidMotor.set(speed);
    }

    public double getPos() {
        return encoder.getPosition();
    }

}