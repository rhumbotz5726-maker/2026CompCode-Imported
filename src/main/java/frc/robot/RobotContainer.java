// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import choreo.auto.AutoFactory;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivesubsystem;

public class RobotContainer {

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  double deadband = 0.03;
  double slow = 1.0;

  public static final Drivesubsystem driveSub = new Drivesubsystem();




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
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
