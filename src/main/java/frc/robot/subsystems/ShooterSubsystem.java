package frc.robot.subsystems;

import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{

    SparkMax shooterMotor = new SparkMax(11, MotorType.kBrushless); //Shoot
    SparkMax turretMotor = new SparkMax(12, MotorType.kBrushless); //Spin PID
    SparkMax beltMotor1 = new SparkMax(13, MotorType.kBrushless); //Belt
    SparkAbsoluteEncoder encoder = turretMotor.getAbsoluteEncoder();

    public static final double torque = 1;
    public static final double COF = 1;
    public static final double Mass = 1;
    public static final double angle = 1;
    
    public static final double force = COF*torque;
    public static final double accel = force/Mass;
    public static final double velAt1Sec = accel*1;

    public static final double distance = (velAt1Sec*velAt1Sec)*Math.sin(2*angle)/(-9.8);
    
    double turretMax;
    double turretMin;
    double nullval =  -100;

    public ShooterSubsystem(){
        this.turretMax = nullval;
        this.turretMin = nullval;
    }

    public void setTurrentVal(double max, double min){
        this.turretMax = max;
        this.turretMin = min;
    }

    public void setShooterSpeed(double speed) {
        shooterMotor.set(speed);
    }

    public void setTurretSpeed(double speed,boolean override) {
        if (turretMax == nullval || turretMin == nullval || override){
            turretMotor.set(speed);
        } else {
            if(encoder.getPosition() > turretMax || encoder.getPosition() < turretMin){
                turretMotor.set(0);
            }
        }
    }

    public void setBeltSpeed(double speed) {
        beltMotor1.set(speed);
    }

    public double getPos(){
        return encoder.getPosition();
    }

    public void perodic() {
        SmartDashboard.putNumber("Turret encoder: ", getPos());
    }
    
}
