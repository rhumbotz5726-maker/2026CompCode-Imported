package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePIDCmd extends Command{
    
    private IntakeSubsystem intakeSub;
    private double setpoint; 
    private PIDController controller;
    private double tolerance = 0.05;

    public IntakePIDCmd(IntakeSubsystem intakeSub, double setpoint) {
        this.intakeSub = intakeSub;
        this.setpoint = setpoint;
        this.controller = new PIDController(1, 0, 0);
    }

    @Override
    public void initialize() {
        controller.setSetpoint(setpoint);
    }

    @Override 
    public void execute() {
        intakeSub.pidSetSpeed(controller.calculate(intakeSub.getPos()));
    }

    @Override
    public boolean isFinished() {
        return Math.abs(intakeSub.getPos() - setpoint) < tolerance;
    }


}
