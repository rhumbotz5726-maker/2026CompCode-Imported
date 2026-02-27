package frc.robot.commands.auto;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class TimeClimb extends Command{
    
    Timer timer = new Timer();
    ClimbSubsystem climbSubsystem;
    double time;

    public TimeClimb(ClimbSubsystem climbSubsystem, int time){
        this.climbSubsystem =  climbSubsystem;
        this.time = time;
        timer.reset();
    }

    @Override
    public void initialize() {
        timer.reset();
    }

    @Override
    public void execute() {
        climbSubsystem.setSpeed(1);
    }

    @Override
    public void end(boolean interrupted) {
        climbSubsystem.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return (timer.get() >= time);
    }





    
}
