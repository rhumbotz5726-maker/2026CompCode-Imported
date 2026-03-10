package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class BeltCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double speed;

    public BeltCmd(ShooterSubsystem shooterSub, double speed){
        this.shooterSub = shooterSub;
        this.speed = speed;
    }

    @Override
    public void execute() {
        shooterSub.setBeltSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSub.setBeltSpeed(0);
    }

}
