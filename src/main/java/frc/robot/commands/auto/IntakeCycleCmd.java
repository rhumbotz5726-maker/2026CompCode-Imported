package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCycleCmd extends Command {
    private final IntakeSubsystem sub;
    private boolean fin = false;
    public IntakeCycleCmd(IntakeSubsystem sub){
            this.sub = sub;
            this.fin = false;
    }

    @Override
    public void initialize() {
        this.fin = false;
        Commands.runOnce(
            ()-> {Commands.sequence(
                new IntakePIDCmd(sub, Constants.intakeConstants.INTAKE_EXTEND_SETPOINT),
                new IntakeCmd(sub, 0.8)
            );}
        , sub
        );
    }

    @Override
    public void end(boolean interrupted) {
        Commands.runOnce(
            ()-> {Commands.sequence(
                new IntakeCmd(sub, 0),
                new IntakePIDCmd(sub, Constants.intakeConstants.INTAKE_PUTBACKf_SETPOINT),
                new Command() {
                    @Override
                    public void initialize() {
                        fin = true;
                    }
                }
            );}
        , sub
        );
    }

    @Override public boolean isFinished() {return fin;}
}
