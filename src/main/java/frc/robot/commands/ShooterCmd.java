package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double speed;

    public ShooterCmd( ShooterSubsystem shooterSub, double speed) {
        this.shooterSub = shooterSub;
        this.speed = speed;
    }

    @Override
    public void execute() {
        shooterSub.setShooterSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSub.setShooterSpeed(0);
    }
}
