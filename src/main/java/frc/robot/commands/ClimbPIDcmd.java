package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPIDcmd extends Command{

    private ClimbSubsystem climbSub;
    private float setpoint;
    private PIDController controller;
    private float tolerance = 0.05f;

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
        climbSub.setSpeed(1, controller.calculate(climbSub.getPosOne()));
        
    }

    @Override
    public boolean isFinished() {
        return Math.abs(climbSub.getPosOne() - setpoint) < tolerance;
    }

    
}
