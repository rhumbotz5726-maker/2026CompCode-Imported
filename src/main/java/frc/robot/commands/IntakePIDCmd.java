package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePIDCmd extends Command{
    
    private IntakeSubsystem intakeSub;
    //private double setpoint; 
    private PIDController controller;

    public IntakePIDCmd(IntakeSubsystem intakeSub, double setpoint) {
        this.intakeSub = intakeSub;
        this.controller = new PIDController(0.01, 0.0, 0.0);
        controller.setTolerance(0.5);
        controller.setSetpoint(setpoint);
        addRequirements(intakeSub);
    }


    @Override
    public void initialize () {

    }

    @Override 
    public void execute() {
        intakeSub.pidSetSpeed(controller.calculate(intakeSub.getPos()));
    }

    @Override
    public void end(boolean isFinished) {
        intakeSub.pidSetSpeed(0);
        //super.end(isFinished);
    }

}
