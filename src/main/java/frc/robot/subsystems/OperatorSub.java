package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OperatorSub extends SubsystemBase{

    BeltSubsystem belt;
    ClimbSubsystem climb;
    ShooterSubsystem shooterSubsystem;


    public OperatorSub ( BeltSubsystem belt, ClimbSubsystem climb, ShooterSubsystem shooterSubsystem){
        this.belt = belt;
        this.climb = climb;
        this.shooterSubsystem = shooterSubsystem;

    }

    public void operatorControls(double beltSpeed, double shooterSpeed){
        moveBelt(beltSpeed);
        moveShooter(shooterSpeed);

    }
    public void moveBelt(double speed){
        belt.setSpeed(speed);
        
    }

    public void moveShooter(double speed){
        shooterSubsystem.setTurretSpeed(speed);
    }
    
}
