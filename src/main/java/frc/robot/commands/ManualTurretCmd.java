package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ManualTurretCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double speed;

    public ManualTurretCmd (ShooterSubsystem shooterSub, double speed) {
        this.shooterSub = shooterSub;
        this.speed = speed;
    }
    @Override
    public void initialize () {

    }

    @Override
    public void execute () {
        shooterSub.setTurretSpeed(speed,true);
    }

    @Override
    public void end (boolean interrupted) {
        shooterSub.setTurretSpeed(0,true);
    }

}
