package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterLineUpCmd extends Command{
        //Convert TX to motor rotations and like make the setpoint that spot
        ShooterSubsystem sub;
        double setpoint;

        //In case the other way doesn't work you can try to the trigger option but its not fully implemented
        BooleanSupplier meow = () -> {return true;};
        Trigger trigger = new Trigger(meow);

        public ShooterLineUpCmd(ShooterSubsystem sub){
                this.sub = sub;
                this.setpoint = LimelightHelpers.getTX("") * Constants.LimelightConstants.TX_TO_SHOOTER_ROTATIONS;
        }

        @Override
        public void execute() {
                Commands.runOnce(()->{
                        //TODO implement distance math effectivley for the servo
                        Commands.parallel(new ShooterPIDCmd(sub, setpoint,90));

                }, sub);
        }

        @Override
        public boolean isFinished() {
        
        return 
        (RobotContainer.getTX(0, 1) < Constants.LimelightConstants.ACCEPTABLE_RANGE && 
        RobotContainer.getTX(0, 1) > -Constants.LimelightConstants.ACCEPTABLE_RANGE) ||
        LimelightHelpers.getFiducialID("") == -1;
        }
}