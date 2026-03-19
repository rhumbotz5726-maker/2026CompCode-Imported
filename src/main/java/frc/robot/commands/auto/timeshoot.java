package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class timeshoot extends Command{
    private ShooterSubsystem shooterSub;
    private double time;
    Timer timer = new Timer();


    public timeshoot( ShooterSubsystem shooterSub, double time) {
        this.shooterSub = shooterSub;
        this.time = time;
    }

    @Override
    public void initialize() {
    shooterSub.setBeltSpeed(0.9);
    shooterSub.setShooterSpeed(0.9);
    timer.reset();
    }

    @Override
    public void end(boolean interrupted) {
        shooterSub.setShooterSpeed(0);
        shooterSub.setBeltSpeed(0);
    }

    @Override
    public boolean isFinished() {
            if (timer.get() < time){
                return true;
            } else {
                return false;
            }
    }
}
