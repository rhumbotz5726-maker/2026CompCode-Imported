package frc.robot;

import choreo.auto.AutoFactory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.ShooterPIDCmd;

public class Autos {



    AutoFactory autoFactory =new AutoFactory(
    //these are method pointers they basically tell the code "Hey the method you want is right here"
    RobotContainer.driveSub::getPose,
    RobotContainer.driveSub::resetOdometry,
    RobotContainer.driveSub::followTrajectory,

    true, 
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



    
    
}
