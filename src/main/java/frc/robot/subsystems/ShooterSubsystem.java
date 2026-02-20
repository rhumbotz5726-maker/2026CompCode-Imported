package frc.robot.subsystems;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{

    SparkMax shooterMotor = new SparkMax(5, MotorType.kBrushless); //Shoot
    SparkMax turretMotor = new SparkMax(6, MotorType.kBrushless); //Spin PID
    SparkMax beltMotor1 = new SparkMax(7, MotorType.kBrushless); //Belt
    SparkMax beltMotor2 = new SparkMax(8, MotorType.kBrushless); //Belt

    SparkAbsoluteEncoder encoder = turretMotor.getAbsoluteEncoder();

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

    public void setTurretSpeed(double speed) {
        turretMotor.set(speed);
    }

    public void setBeltSpeed(double speed) {
        beltMotor1.set(speed);
        beltMotor2.set(speed);
    }

    public double getPos(){
        return encoder.getPosition();
    }

    public void perodic() {
        SmartDashboard.putNumber("Turret encoder: ", getPos());
    }
    
}
