package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OperatorSub extends SubsystemBase{

   // BeltSubsystem belt;
    ClimbSubsystem climb;
    ShooterSubsystem shooterSubsystem;
    IntakeSubsystem intake;


    public OperatorSub (//BeltSubsystem belt,
     ClimbSubsystem climb, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem){
       // this.belt = belt;
        this.climb = climb;
        this.shooterSubsystem = shooterSubsystem;
        this.intake = intakeSubsystem;

    }

    public void operatorControls (double beltSpeed, double shooterSpeed,double turretUpSpeed, double turretDownspeed){
        moveBelt(beltSpeed);
        moveTurret(shooterSpeed);
        moveIntake(turretUpSpeed+turretDownspeed);

    }

     public void operatorControls (double beltSpeed, double shooterSpeed){
        moveBelt(beltSpeed);
        moveTurret(shooterSpeed);

    }

    public void moveBelt (double speed){
       shooterSubsystem.setBeltSpeed(speed);
       shooterSubsystem.setShooterSpeed(speed);
        
    }

    public void moveIntake(double speed){
        intake.pidSetSpeed(speed);
    }

    public void moveTurret (double speed){
        shooterSubsystem.setTurretSpeed(speed, true);
    }
    
}
