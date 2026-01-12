package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbPIDcmd extends Command{

    ClimbSubsystem climbSubsystem;
    float setpoint;

    public ClimbPIDcmd(ClimbSubsystem climbSubsystem, float setpoint){
        this.climbSubsystem = climbSubsystem;
        this.setpoint = setpoint;
        PIDController controller = new PIDController(1, 0, 0);
    }

    

    
}
