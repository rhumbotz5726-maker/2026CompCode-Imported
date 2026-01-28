package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterPIDCmd extends Command{
    private ShooterSubsystem shooterSub;
    private double setpoint; 
    private double servopoint;
    private PIDController controller;

    public ShooterPIDCmd (ShooterSubsystem shooterSub, double setpoint) {
        this.shooterSub = shooterSub;
        this.setpoint = setpoint;

        // TODO set these up so that like they are real values and not just 1
        controller = new PIDController(1, 0, 0);
        // can't remember what else goes here for now
        // you got it all pmuch
    }

       public ShooterPIDCmd (ShooterSubsystem shooterSub, double setpoint, double servopoint) {
        this.shooterSub = shooterSub;
        this.setpoint = setpoint;
        this.servopoint = servopoint;
       }

    @Override
    public void initialize() {
            controller.setSetpoint(setpoint);
            shooterSub.setServo(servopoint);
    }

    @Override
    public void execute() {
        shooterSub.setTurretSpeed(controller.calculate(shooterSub.getPos())); // replace it with the math thingy later
    }

    @Override
    public void end(boolean isFinished) {

    }

}
