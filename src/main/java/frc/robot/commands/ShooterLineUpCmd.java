package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterLineUpCmd extends Command{
        ShooterSubsystem sub;
        public ShooterLineUpCmd(ShooterSubsystem sub) {
                this.sub=sub;
                //this.pid
        }


        @Override
        public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
        }
}