package frc.robot;

import choreo.auto.AutoFactory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.auto.TimeClimb;
import frc.robot.commands.auto.TurretPIDCmd;
import frc.robot.Sequences.ShooterSequence;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.Drivesubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autos {
    public  Drivesubsystem driveSub;
    public  ShooterSubsystem shooterSub;
    public  ClimbSubsystem climbSub;

    public Autos(Drivesubsystem drivesubsystem, ShooterSubsystem shooterSub2, ClimbSubsystem climbSub2){
        driveSub = drivesubsystem;
        shooterSub = shooterSub2;
        climbSub = climbSub2;
    }
  
    AutoFactory autoFactory =new AutoFactory(
    //these are method pointers they basically tell the code "Hey the method you want is right here"
        driveSub::getPose,
        driveSub::resetOdometry,
        driveSub::followTrajectory,
        false, 
        driveSub
    );

    //this will be an example for like the lineup n stuff
    public Command midToLineup(){
        return Commands.sequence(
        autoFactory.resetOdometry("go to middle"),
         new TurretPIDCmd(shooterSub, 90),
        autoFactory.resetOdometry("go back to start"),
        Commands.runOnce(() -> System.out.println("reset")));
    }


    public Command shootThenClimb(String start, String mid, String end){
        autoFactory.resetOdometry(start + "t" + mid);
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new TurretPIDCmd(shooterSub, 90),
            autoFactory.trajectoryCmd(start+"t"+end),
            new TimeClimb(climbSub,1)
        ); // change setpoints 
    }

    public Command test(String name){
        return Commands.sequence(
              autoFactory.resetOdometry(name),
              autoFactory.trajectoryCmd(name)
            );
    }


    public Command shootOnly(){
        return Commands.sequence(
        new TurretPIDCmd(shooterSub, 0.5), 
        new ShooterSequence(shooterSub,1,.5).getSeq(), 
        new ShooterSequence(shooterSub, 0, 0).endSeq);
    }

     public Command shoot(String start, String mid){
        autoFactory.resetOdometry(start + "t" + mid);
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new TurretPIDCmd(shooterSub, 90)
            );
    }

    /*
     public Command shootReloadClimb(String start,String mid, String reload, String end){
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(),
            autoFactory.trajectoryCmd(mid+"t"+reload),
            new IntakeCmd(.intakeSub, 0.5),
            autoFactory.trajectoryCmd(reload+"t"+mid),
            new ShooterLineUpCmd(),
            autoFactory.trajectoryCmd(mid+"t"+end),
            new TimeClimb(null, 0)
        );
    }

      public Command shootReloadShoot(String start,String mid, String reload, String end){
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(),
            autoFactory.trajectoryCmd(mid+"t"+reload),
            new IntakeCmd(intakeSub, 0.5),
            autoFactory.trajectoryCmd(reload+"t"+mid),
            new ShooterLineUpCmd()
        );

    }
        */
    
}
