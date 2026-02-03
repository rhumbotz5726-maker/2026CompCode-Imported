// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BeltCmd;
import frc.robot.commands.ClimbPIDcmd;
import frc.robot.commands.ShooterCmd;
import frc.robot.commands.ShooterPIDCmd;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.Drivesubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  double deadband = 0.03;
  double slow = 1.0;

  public static final Drivesubsystem driveSub = new Drivesubsystem();
  public static final BeltSubsystem beltSub = new BeltSubsystem();
  public static final IntakeSubsystem intakeSub = new IntakeSubsystem();
  public static final ClimbSubsystem climbSub = new ClimbSubsystem();
  public static final ShooterSubsystem shooterSub = new ShooterSubsystem();




  public static double getAxis(XboxController controller, int axis, double deadband) {
    double value = controller.getRawAxis(axis);
    if (Math.abs(value) < deadband) {
      return 0.0;
    } else {
      return value;
    }

  }

  public RobotContainer() {


    configureBindings();

    driveSub.setDefaultCommand(
      new RunCommand(() -> 
      driveSub.drive(
        getAxis(driver,0, deadband)/slow, 
        getAxis(driver,1, deadband)/slow,
        getAxis(driver,2, deadband)/slow, 
        driver.getRawButton(6)) , driveSub));

    beltSub.setDefaultCommand(new BeltCmd(beltSub, 0.8));
  }

  private void configureBindings() {
       new JoystickButton(driver, 2).whileTrue(new RunCommand(() -> driveSub.drive(
      -MathUtil.applyDeadband(driver.getLeftY()/slow, OIConstants.kDriveDeadband),
      -MathUtil.applyDeadband(driver.getLeftX()/slow, OIConstants.kDriveDeadband),
         -(LimelightHelpers.getTX("")+0) * 0.015, false), driveSub));

    new JoystickButton(driver, 3).whileTrue(new RunCommand(() -> driveSub.drive(
     getTY(-2,-0.05),
      -MathUtil.applyDeadband(driver.getLeftX()/slow, OIConstants.kDriveDeadband), 
      getTX(0, 0.015), 
        false
      )));

      new JoystickButton(operator, 0).whileTrue(new frc.robot.commands.teleop.IntakeCycleCmd(intakeSub)); 
      //There's also intake pid
      new JoystickButton(operator, 1).whileTrue(new ShooterCmd(shooterSub, 0.5));
      new JoystickButton(operator, 2).whileTrue(new ShooterPIDCmd(shooterSub, 90)); //PID
      new JoystickButton(operator, 3).whileTrue(new ShooterPIDCmd(shooterSub, 90, 90)); //servo
      new JoystickButton(operator, 4).whileTrue(new ClimbPIDcmd(climbSub, 90));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  
  public static double getTY(double offset, double scale){
    if(LimelightHelpers.getFiducialID("") == -1){
      return LimelightHelpers.getTY("");
    } else {
      return (LimelightHelpers.getTY("")+offset) * scale;
    }
  }

  public static double getTX(double offset, double scale){
    if(LimelightHelpers.getFiducialID("") == -1){
      return LimelightHelpers.getTX("");
    } else {
      return  -(LimelightHelpers.getTX("")+offset) * scale ;
    }
  }
}
