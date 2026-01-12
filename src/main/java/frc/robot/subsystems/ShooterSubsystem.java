package frc.robot.subsystems;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;

public class ShooterSubsystem {

    SparkMax shooterMotor = new SparkMax(1, MotorType.kBrushless);
    Servo servo = new Servo(0);
    SparkAbsoluteEncoder encoder = shooterMotor.getAbsoluteEncoder();

    public void setSpeed(double speed) {
        shooterMotor.set(speed);
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
