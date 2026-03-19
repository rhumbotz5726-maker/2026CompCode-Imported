package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
//import com.revrobotics.spark.config.AbsoluteEncoderConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class IntakeSubsystem extends SubsystemBase{
    SparkMax intakeMotor = new SparkMax(9, MotorType.kBrushless);
    SparkMax pidMotor = new SparkMax(10, MotorType.kBrushless);
    RelativeEncoder encoder = pidMotor.getEncoder();
    SparkMaxConfig config = new SparkMaxConfig();
    
    public IntakeSubsystem(){
        config.idleMode(IdleMode.kBrake);
        pidMotor.configure(config, com.revrobotics.spark.SparkBase.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void setIntakeSpeed(double speed) {
        intakeMotor.set(speed);
    } 

    public void pidSetSpeed(double speed) {
        pidMotor.set(speed);
    }

    public double getPos() {
        return encoder.getPosition();
    }

    public void resetEncoder() {
       encoder.setPosition(0.0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake encoder: ", getPos());
    }


}