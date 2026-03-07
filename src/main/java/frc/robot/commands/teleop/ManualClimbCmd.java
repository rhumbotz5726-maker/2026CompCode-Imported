package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ManualClimbCmd extends Command{

    private double speed;
    private ClimbSubsystem climbSub;

    public ManualClimbCmd(ClimbSubsystem climbSub, double speed){
        this.climbSub = climbSub;
        this.speed = speed;
    }

    @Override
    public void initialize () {

    }

    @Override
    public void execute() {
        climbSub.setSpeed(1, speed);
        climbSub.setSpeed(2, speed);
    }

    @Override
    public void end(boolean interrupted) {
        climbSub.setSpeed(0);
    }
    
}
