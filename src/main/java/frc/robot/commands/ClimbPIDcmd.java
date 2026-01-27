package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPIDcmd extends Command{

    ClimbSubsystem climbSub;
    float setpoint;
    PIDController controller;

    public ClimbPIDcmd(ClimbSubsystem climbSub, float setpoint){
        this.climbSub = climbSub;
        this.setpoint = setpoint;
        controller = new PIDController(1, 0, 0);
    }

    @Override
    public void initialize() {
        controller.setSetpoint(setpoint);
    }

    @Override
    public void execute() {
        climbSub.setSpeed(controller.calculate(climbSub.getPos()));
        
    }

    @Override 
    public void end( boolean isFinished) {

    }


    
}
