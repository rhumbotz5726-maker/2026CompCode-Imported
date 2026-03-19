package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class manIntake extends Command {

    IntakeSubsystem intake;
    double speed;

    public manIntake(IntakeSubsystem intake, double speed){
        this.intake = intake;
        this.speed = speed;
    }

    @Override
    public void execute() {
        intake.pidSetSpeed(speed);
    }

    @Override
    public void end(boolean interrupted) {
        intake.pidSetSpeed(0);
    }
    
}
