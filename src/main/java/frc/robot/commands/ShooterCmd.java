package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double shooterSpeed;
    private double beltSpeed;

    public ShooterCmd( ShooterSubsystem shooterSub, double shooterSpeed, double beltSpeed) {
        this.shooterSub = shooterSub;
        this.shooterSpeed = shooterSpeed;
        this.beltSpeed = beltSpeed;
    }

    @Override
    public void execute() {
        shooterSub.setShooterSpeed(shooterSpeed);
        shooterSub.setBeltSpeed(beltSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSub.setShooterSpeed(0);
        shooterSub.setBeltSpeed(0);
    }
}
