package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ManualClimbCmd extends Command{

    private int motorNumber;
    private double speedOne;
    private double speedTwo;
    private ClimbSubsystem climbSub;

    public ManualClimbCmd(ClimbSubsystem climbSub, int motorNumber, double speedOne, double speedTwo){
        this.climbSub = climbSub;
        this.speedOne = speedOne;
        this.speedTwo = speedTwo;
        this.motorNumber = motorNumber;
    }

    @Override
    public void initialize () {

    }

    @Override
    public void execute() {
        if (motorNumber == 1) {
            climbSub.setSpeed(1, speedOne);
            
        } else {
            climbSub.setSpeed(2, speedTwo);
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        climbSub.setSpeed(1, 0);
        climbSub.setSpeed(2, 0);
    }
    
}
