package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class TurretPIDCmd  extends Command {
    ShooterSubsystem shooterSub;
    PIDController controller;
    double setpoint; 

    public TurretPIDCmd (ShooterSubsystem shooterSub, double setpoint) {
        this.shooterSub = shooterSub;
        this.controller = new PIDController(1,0,0);
        this.setpoint = setpoint;
    }

    @Override
    public void initialize() {
        controller.setSetpoint(setpoint);
    }

    @Override
    public void execute() {
        shooterSub.setTurretSpeed(controller.calculate(shooterSub.getPos()), false);
        //shooterSub.setTurretSpeed(controller.calculate(tx)); if limelight is possible
    }

    @Override
    public void end(boolean interrupted) {

    }
}
