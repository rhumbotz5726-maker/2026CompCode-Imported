package frc.robot.subsystems;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;

public class ShooterSubsystem {

    SparkMax shooterMotor = new SparkMax(1, MotorType.kBrushless); //Shoot
    SparkMax turretMotor = new SparkMax(5, MotorType.kBrushless); //Spin PID
    SparkMax beltMotor = new SparkMax(7, MotorType.kBrushless); //Belt
    Servo servo = new Servo(0);
    SparkAbsoluteEncoder encoder = turretMotor.getAbsoluteEncoder();

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

    public void setTurretSpeed(double speed) {
        turretMotor.set(speed);
    }

    public void setBeltSpeed(double speed) {
        beltMotor.set(speed);
    }

    public void setServo(double point){
        servo.set(point);
    }

    public double getServo(){
        return servo.get();
    }

    public double getPos(){
        return encoder.getPosition();
    }
    
}
