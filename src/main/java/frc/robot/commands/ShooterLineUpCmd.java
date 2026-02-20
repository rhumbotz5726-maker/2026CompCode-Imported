package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
// think about using the limelight to turn the 
public class ShooterLineUpCmd extends Command {
    ShooterSubsystem shooterSub;
    PIDController autoController;
    double setpoint; 

    public ShooterLineUpCmd (ShooterSubsystem shooterSub, double setpoint) {
        this.shooterSub = shooterSub;
        this.autoController = new PIDController(1,0,0);
        this.setpoint = setpoint;
    }

    @Override
    public void initialize() {
        shooterSub.setTurretSpeed(autoController.calculate(shooterSub.getPos()));
    }

    @Override
    public void execute() {
        autoController.calculate(setpoint);
    }

}