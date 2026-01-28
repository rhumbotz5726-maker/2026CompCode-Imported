package frc.robot.commands;

//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends Command{
    private IntakeSubsystem intakeSub; 
    private double speed;

    public IntakeCmd(IntakeSubsystem intakeSub, double speed, double setpoint) {
        this.intakeSub = intakeSub;
        this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        intakeSub.intakeSetSpeed(speed);
    }

    @Override
    public void end(boolean isFinished) {

    }
}
