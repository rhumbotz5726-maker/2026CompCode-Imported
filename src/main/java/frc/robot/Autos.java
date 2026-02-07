package frc.robot;

import choreo.auto.AutoFactory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ClimbPIDcmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShooterLineUpCmd;
import frc.robot.commands.ShooterPIDCmd;
import frc.robot.subsystems.IntakeSubsystem;

public class Autos {

    private final IntakeSubsystem intakeSub = new IntakeSubsystem();

    AutoFactory autoFactory =new AutoFactory(
    //these are method pointers they basically tell the code "Hey the method you want is right here"
        RobotContainer.driveSub::getPose,
        RobotContainer.driveSub::resetOdometry,
        RobotContainer.driveSub::followTrajectory,
        false, 
        RobotContainer.driveSub
    );

    //this will be an example for like the lineup n stuff
    public Command midToLineup(){
        return Commands.sequence(
        autoFactory.resetOdometry("go to middle"),
         new ShooterPIDCmd(null, 0),
        autoFactory.resetOdometry("go back to start"),
        Commands.runOnce(() -> System.out.println("reset")));
    }


    public Command shootThenClimb(String start, String mid, String end){
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(),
            autoFactory.trajectoryCmd(start+"t"+end),
            new ClimbPIDcmd(null, 0)
        );
    }

     public Command shoot(String start, String mid){
        return Commands.sequence(
              autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd()
            );
    }

     public Command shootReloadClimb(String start,String mid, String reload, String end){
        return Commands.sequence(
            autoFactory.trajectoryCmd(start+"t"+mid),
            new ShooterLineUpCmd(),
            autoFactory.trajectoryCmd(mid+"t"+reload),
            new IntakeCmd(intakeSub, 0.5),
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


    
    
}
