package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class BeltCmd extends Command{
    private ShooterSubsystem shooterSub;
    private IndexerSubsystem indexerSub;
    private double shooterSpeed;
    private double indexerSpeed;

    public BeltCmd(ShooterSubsystem shooterSub, IndexerSubsystem indexerSub, double shooterSpeed, double indexerSpeed){
        this.shooterSub = shooterSub;
        this.indexerSub = indexerSub;
        this.shooterSpeed = shooterSpeed;
        this.indexerSpeed = indexerSpeed;
    }

    @Override
    public void execute() {
        shooterSub.setBeltSpeed(shooterSpeed);
        indexerSub.setSpeed(indexerSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        shooterSub.setBeltSpeed(0.0);
        indexerSub.setSpeed(0.0);
    }

}
