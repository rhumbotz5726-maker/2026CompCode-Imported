package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPIDcmd extends Command{

    private ClimbSubsystem climbSub;
    private float setpoint;
    private PIDController controllerOne;
    private PIDController controllerTwo;
    private int motorNumber;
    private float tolerance = 0.05f;

    public ClimbPIDcmd(ClimbSubsystem climbSub, float setpoint, int motorNumber){
        this.climbSub = climbSub;
        this.setpoint = setpoint;
        controllerOne = new PIDController(1, 0, 0);
        this.controllerTwo = new PIDController(1, 0, 0);
        this.motorNumber = motorNumber;
    }

    @Override
    public void initialize() {
        controllerOne.setSetpoint(setpoint);
        controllerTwo.setSetpoint(setpoint);
    }

    @Override
    public void execute() {
        if (this.motorNumber == 1) {
            climbSub.setSpeed(motorNumber, controllerOne.calculate(climbSub.getPosOne()));
        } else if (motorNumber == 2) {
            climbSub.setSpeed(motorNumber, controllerTwo.calculate(climbSub.getPosTwo()));
        }
        
        
    }

    @Override
    public boolean isFinished() {
        return Math.abs(climbSub.getPosOne() - setpoint) < tolerance;
    }

    
}
