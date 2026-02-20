package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants;
import frc.robot.commands.ClimbPIDcmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.IntakePIDCmd;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCycleCmd extends Command {
    private ClimbSubsystem climbSub;

    public ClimbCycleCmd (ClimbSubsystem climbSub) {
        this.climbSub = climbSub;
    }

    @Override
    public void initialize() {
        Commands.runOnce(
            ()-> {Commands.sequence(
                    new ClimbPIDcmd(climbSub, Constants.climbConstants.CLIMB_EXTEND_SETPOINT, 1),
                    new ClimbPIDcmd(climbSub, Constants.climbConstants.CLIMB_EXTEND_SETPOINT, 2));}, 
                climbSub);
    }

    @Override
    public void end(boolean isFinished) {
        Commands.runOnce(
            ()-> {Commands.sequence(
                    new ClimbPIDcmd(climbSub, Constants.climbConstants.CLIMB_RETRACT_SETPOINT, 1), 
                    new ClimbPIDcmd(climbSub, Constants.climbConstants.CLIMB_RETRACT_SETPOINT, 2));},
                climbSub);
    }
}
