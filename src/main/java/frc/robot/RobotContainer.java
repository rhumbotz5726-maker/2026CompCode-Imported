// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import choreo.auto.AutoFactory;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BeltCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShooterCmd;
import frc.robot.commands.ShooterPIDCmd;
import frc.robot.subsystems.BeltSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriverSub;
import frc.robot.subsystems.Drivesubsystem;
import frc.robot.subsystems.IntakeSubsystem;
//import frc.robot.subsystems.OperatorSub;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);
  
  double deadband = 0.03;
  double slow = 3;

  public static final Drivesubsystem driveSub = new Drivesubsystem();
  //private final BeltSubsystem beltSub = new BeltSubsystem();
  private final IntakeSubsystem intakeSub = new IntakeSubsystem();
  //private final ClimbSubsystem climbSub = new ClimbSubsystem();
  //private final ShooterSubsystem shooterSub = new ShooterSubsystem();
  //Autos autos = new Autos(driveSub, shooterSub, climbSub);
  private final DriverSub driverSub = new DriverSub(driveSub, intakeSub);
  //private final OperatorSub operatorSub = new OperatorSub(beltSub, climbSub, shooterSub);



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

  
    driverSub.setDefaultCommand(new RunCommand(() -> 
    driveSub.drive(
        //y
        -getAxis(driver,1, deadband)/slow, 
        //x
        -getAxis(driver,0, deadband)/slow,
        //rot
        -getAxis(driver,4, deadband)/slow, 
        //field orient
        driver.getRawButton(6)),
        //run command requirements
        driverSub));

    /*operatorSub.setDefaultCommand(new RunCommand(
      ()-> operatorSub.operatorControls(
        //belt
        getAxis(operator, 4, deadband)/slow,
        //turret
        getAxis(operator, 0, deadband)/slow),
        //run command requirements
        operatorSub)); */

  }

  private void configureBindings() {
      driverControls();
      //operatorControls();
    
    }

  public void driverControls(){
    new JoystickButton(driver, 5).whileTrue(new RunCommand(() -> driveSub.resetGyro(), driveSub));
    //new JoystickButton(driver, 1).whileTrue(autos.test("test"));
    //new JoystickButton(driver, 2).whileTrue(autos.test("s2tm2"));
  }

  /*public void operatorControls(){
      new JoystickButton(operator, 0).whileTrue(new IntakeCmd(intakeSub, 0.5)); // change to intakeCycleCmd
      new JoystickButton(operator, 1).whileTrue(new ShooterCmd(shooterSub, 0.5));
      new JoystickButton(operator, 3).whileTrue(new ClimbPIDcmd(climbSub, 9, 1)); // play with this to find out the correct constant for climb setpoint
     // new JoystickButton(operator, 2).whileTrue(new ShooterPIDCmd(shooterSub, 0, getTX())); //PID with limelight
     // new JoystickButton(operator, 4).whileTrue(new ClimbPIDcmd(climbSub, 90));
    }*/

  

  public Command getAutonomousCommand() {
   // return autos.test("s2tm2") ; 
    return null;
  }

  
  public double getTY(double offset, double scale){
    if(LimelightHelpers.getFiducialID("") == -1){
      return LimelightHelpers.getTY("");
    } else {
      return (LimelightHelpers.getTY("")+offset) * scale;
    }
  }

  public double getTY(int ID,double offset,double scale){
    if(LimelightHelpers.getFiducialID("") == ID){
      return getTY(offset, scale);
    } else {
      return 0;
    }
  }

   public double getTX(int ID,double offset,double scale){
    if(LimelightHelpers.getFiducialID("") == ID){
      return getTX(offset, scale);
    } else {
      return 0;
    }
  }

  public double getTX(double offset, double scale){
    if(LimelightHelpers.getFiducialID("") == -1){
      return LimelightHelpers.getTX("");
    } else {
      return  -(LimelightHelpers.getTX("")+offset) * scale ;
    }
  }
}
