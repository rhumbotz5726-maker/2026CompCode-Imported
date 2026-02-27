package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class manualClimb extends Command{

    double speed1;
    double speed2;
    ClimbSubsystem climbSub;

    public manualClimb(double speed1, double speed2, ClimbSubsystem climbSub){
        this.climbSub = climbSub;
        this.speed1 = speed1;
        this.speed2 = speed2;
    }


    @Override
    public void execute() {
        climbSub.setSpeed(0,speed1);
        climbSub.setSpeed(1,speed2);
    }

    @Override
    public void end(boolean interrupted) {
        climbSub.setSpeed(0);
    }
    
}
