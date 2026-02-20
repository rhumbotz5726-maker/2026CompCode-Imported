package frc.robot;

import java.util.Optional;

import choreo.Choreo;
import choreo.auto.AutoFactory;
import choreo.trajectory.SwerveSample;
import choreo.trajectory.Trajectory;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.ClimbPIDcmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShooterLineUpCmd;
import frc.robot.commands.ShooterPIDCmd;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.Drivesubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autos {
    public static Drivesubsystem driveSub;
    public static ShooterSubsystem shooterSub;
    public static ClimbSubsystem climbSub;

    public Autos(Drivesubsystem drivesubsystem, ShooterSubsystem shooterSub, ClimbSubsystem climbSub){
        this.driveSub = drivesubsystem;
        this.shooterSub = shooterSub;
        this.climbSub = climbSub;
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
         new ShooterLineUpCmd(shooterSub, 90),
        autoFactory.resetOdometry("go back to start"),
        Commands.runOnce(() -> System.out.println("reset")));
    }


    public Command shootThenClimb(String start, String mid, String end){
        autoFactory.resetOdometry(start + "t" + mid);
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(shooterSub, 90),
            autoFactory.trajectoryCmd(start+"t"+end),
            new ClimbPIDcmd(climbSub, 0)
        ); // change setpoints 
    }

    public Command test(String name){
        
    
        return Commands.sequence(
              autoFactory.resetOdometry(name),
              autoFactory.trajectoryCmd(name)
            );
    }
     public Command shoot(String start, String mid){
        autoFactory.resetOdometry(start + "t" + mid);
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(shooterSub, 90)
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
            new ClimbPIDcmd(null, 0)
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
