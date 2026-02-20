package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterPIDCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double setpoint; 
    private PIDController controller;
    private double tx;

    public ShooterPIDCmd (ShooterSubsystem shooterSub, double setpoint, double tx) {
        this.shooterSub = shooterSub;
        this.setpoint = setpoint;
        this.tx = tx;
        this.controller = new PIDController(1, 0, 0);
    }


    @Override
    public void initialize() {
            controller.setSetpoint(setpoint);
    }

    @Override
    public void execute() {
        shooterSub.setTurretSpeed(controller.calculate(tx));
    }

    @Override
    public void end(boolean isFinished) {

    }

}
