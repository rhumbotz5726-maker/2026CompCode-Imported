package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ManualClimbCmd extends Command{

    private double speedOne;
    private double speedTwo;
    private ClimbSubsystem climbSub;

    public ManualClimbCmd(ClimbSubsystem climbSub, double speed1, double speed2){
        this.climbSub = climbSub;
        this.speedOne = speed1;
        this.speedTwo = speed2;
    }

    @Override
    public void initialize () {

    }

    @Override
    public void execute() {
            climbSub.setSpeed(speedOne,speedTwo);
        
    }

    @Override
    public void end(boolean interrupted) {
        climbSub.setSpeed(0, 0);
    }
    
}
