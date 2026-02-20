package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCycleCmd extends Command{
    private ShooterSubsystem shooterSub;

    public ShooterCycleCmd(ShooterSubsystem shooterSub) {
        this.shooterSub = shooterSub;
    }

    @Override
    public void initialize() {

    }
    
}
