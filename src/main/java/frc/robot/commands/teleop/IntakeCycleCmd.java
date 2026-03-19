package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.*;
import frc.robot.Constants;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCycleCmd extends Command {
    private IntakeSubsystem intakeSub;
    private IndexerSubsystem indexerSub;
    private double intakeSpeed; 
    private double indexerSpeed;

    public IntakeCycleCmd(IntakeSubsystem intakeSub, IndexerSubsystem indexerSub, double intakeSpeed, double indexerSpeed){
            this.intakeSub = intakeSub;
            this.indexerSub = indexerSub;
            this.intakeSpeed = intakeSpeed;
            this.indexerSpeed = indexerSpeed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        intakeSub.setIntakeSpeed(intakeSpeed);
        indexerSub.setSpeed(indexerSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSub.setIntakeSpeed(0.0);
        indexerSub.setSpeed(0.0);
    }
}
