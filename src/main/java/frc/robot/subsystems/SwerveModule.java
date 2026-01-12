package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Configs;

public class SwerveModule {
    
    private final SparkMax driveSpark;
    private final SparkMax turnSpark;

    private final RelativeEncoder drivingEncoder;
    private final AbsoluteEncoder turningEncoder;

    private final SparkClosedLoopController drivingPID;
    private final SparkClosedLoopController turngingPID;

    private double angularOffset = 0;
    private SwerveModuleState desiredState = new SwerveModuleState(0.0, new Rotation2d());

    public SwerveModule(int drivingCANid, int turningCANId, double chassisAngularOffset){
        driveSpark = new SparkMax(drivingCANid, MotorType.kBrushless);
        turnSpark = new SparkMax(turningCANId, MotorType.kBrushless);
    
        drivingEncoder = driveSpark.getEncoder();
        turningEncoder = turnSpark.getAbsoluteEncoder();
    
        drivingPID = driveSpark.getClosedLoopController();
        turngingPID = turnSpark.getClosedLoopController();

    //configre sparkmaxes
        driveSpark.configure(Configs.MAXSwerveModule.drivingConfig, ResetMode.kNoResetSafeParameters,
         PersistMode.kPersistParameters);
         turnSpark.configure(Configs.MAXSwerveModule.turningConfig, ResetMode.kNoResetSafeParameters,
         PersistMode.kPersistParameters);
    //set angular offset
    this.angularOffset = chassisAngularOffset;
    //update the angle on the desired state
    this.desiredState.angle = new Rotation2d(turningEncoder.getPosition());
    //reset driving encoder
    this.drivingEncoder.setPosition(0);
    }


    public SwerveModuleState getState(){
        return new SwerveModuleState(drivingEncoder.getVelocity(),
         new Rotation2d(turningEncoder.getPosition() - angularOffset));
    }

    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(drivingEncoder.getPosition(), 
        new Rotation2d(turningEncoder.getPosition()-angularOffset));
    }

    public void setDesiredState(SwerveModuleState desiredState2){
        // Apply angular offset n like also setup the corrected desired state
        SwerveModuleState correctedDesiredState = new SwerveModuleState();
        correctedDesiredState.speedMetersPerSecond = desiredState2.speedMetersPerSecond;
        correctedDesiredState.angle = desiredState2.angle.plus(Rotation2d.fromRadians(angularOffset));

        //optimise it so it never rotates more than 90 degress
        correctedDesiredState.optimize(new Rotation2d(turningEncoder.getPosition()));

        //command driving and turning SPArks to start locking in on the right points

        drivingPID.setReference(correctedDesiredState.speedMetersPerSecond, ControlType.kVelocity);
        turngingPID.setReference(correctedDesiredState.angle.getRadians(), ControlType.kPosition);

        this.desiredState = desiredState2;

    }

    public void resetEncoders(){
        drivingEncoder.setPosition(0);
    }

}
