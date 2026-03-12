package frc.robot.Sequences;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSequence extends Sequences{
   

    public ShooterSequence(ShooterSubsystem shooterSubsystem){
        this.subsystem = shooterSubsystem;



        sequence = Commands.sequence(

            Commands.runOnce(()-> ((ShooterSubsystem) subsystem).setShooterSpeed(1), 
            subsystem),

            new WaitCommand(0.5),

            Commands.runOnce(()-> ((ShooterSubsystem) subsystem).setBeltSpeed(1), 
            subsystem)

        );



        endSeq = Commands.sequence(
            
           Commands.runOnce(()-> ((ShooterSubsystem) subsystem).setShooterSpeed(0), 
            subsystem),

            Commands.runOnce(()-> ((ShooterSubsystem) subsystem).setBeltSpeed(0), 
            subsystem)

        );


        
    }





}