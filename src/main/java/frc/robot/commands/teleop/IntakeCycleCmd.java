package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.*;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCycleCmd extends Command {
    private IntakeSubsystem intakeSub;

    public IntakeCycleCmd(IntakeSubsystem intakeSub){
            this.intakeSub = intakeSub;
    }

    @Override
    public void initialize() {
        Commands.runOnce(
            ()-> {Commands.sequence(
                    new IntakePIDCmd(intakeSub, Constants.intakeConstants.INTAKE_EXTEND_SETPOINT));}, 
                intakeSub);
    }

    @Override
    public void execute() {
        new IntakeCmd(intakeSub, 0.8);
    }

    @Override
    public void end(boolean interrupted) {
        Commands.runOnce(
            ()-> {Commands.sequence(
                    new IntakeCmd(intakeSub, 0),
                    new IntakePIDCmd(intakeSub, Constants.intakeConstants.INTAKE_PUTBACKf_SETPOINT));}, 
                intakeSub);
    }
    
}
