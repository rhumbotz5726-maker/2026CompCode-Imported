package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class TurretLimelightCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double setpoint = 0; 
    private PIDController controller;
    private double tx;
    double stopPointA = 1;
    double stopPointB = 2;


    public TurretLimelightCmd (ShooterSubsystem shooterSub, double setpoint, double tx) {
        this.shooterSub = shooterSub;
        this.setpoint = setpoint;
        this.tx = tx;
        this.controller = new PIDController(1, 0, 0);
        this.controller.enableContinuousInput(stopPointA, stopPointB);
    }

 
    public TurretLimelightCmd (ShooterSubsystem shooterSub, double tx) {
        this.shooterSub = shooterSub;
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
        //shooterSub.setTurretSpeed(tx);
    }

    @Override
    public void end(boolean isFinished) {
        shooterSub.setTurretSpeed(0);
    }

}
