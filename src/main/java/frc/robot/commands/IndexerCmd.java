package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexerCmd extends Command{
    private IndexerSubsystem indexerSub;
    private double speed;

    public IndexerCmd(IndexerSubsystem indexerSub, double speed) {
        this.indexerSub = indexerSub;
        this.speed = speed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        indexerSub.setSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        indexerSub.setSpeed(0);
    }
    
}
