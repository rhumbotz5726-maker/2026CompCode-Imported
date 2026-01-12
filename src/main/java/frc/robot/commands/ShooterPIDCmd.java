package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterPIDCmd extends Command{
    private ShooterSubsystem shooterSub;
    private int setpoint; 

    public ShooterPIDCmd (ShooterSubsystem shooterSub, int setpoint) {
        this.shooterSub = shooterSub;
        this.setpoint = setpoint;
        PIDController controller = new PIDController(setpoint, setpoint, setpoint);
        // can't remember what else goes here for now
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shooterSub.setPIDSpeed(setpoint); // replace it with the math thingy later
    }

    @Override
    public void end(boolean isFinished) {

    }

}
