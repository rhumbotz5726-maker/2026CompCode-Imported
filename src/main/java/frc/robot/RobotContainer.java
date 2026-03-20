// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.LimelightHelpers.RawFiducial;
import frc.robot.Sequences.ShooterSequence;
import frc.robot.commands.BeltCmd;
import frc.robot.commands.IndexerCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.IntakePIDCmd;
import frc.robot.commands.ManualClimbCmd;
import frc.robot.commands.ManualTurretCmd;
import frc.robot.commands.ShooterCmd;
import frc.robot.commands.TurretLimelightCmd;
import frc.robot.commands.auto.TurretPIDCmd;
import frc.robot.commands.teleop.IntakeCycleCmd;
import frc.robot.commands.teleop.manIntake;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriverSub;
import frc.robot.subsystems.Drivesubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.OperatorSub;
//import frc.robot.subsystems.OperatorSub;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);
  
  double deadband = 0.03;
  private final double slow = 1.0;
  double slowOp = 3;

  public final Drivesubsystem driveSub = new Drivesubsystem();
  private final IndexerSubsystem indexerSub = new IndexerSubsystem();
  //private final BeltSubsystem beltSub = new BeltSubsystem();
  public final IntakeSubsystem intakeSub = new IntakeSubsystem();
  private final ClimbSubsystem climbSub = new ClimbSubsystem();
  private final ShooterSubsystem shooterSub = new ShooterSubsystem();
  private final DriverSub driverSub = new DriverSub(driveSub, intakeSub);
  private final OperatorSub operatorSub = new OperatorSub(//beltSub, 
  climbSub, shooterSub,intakeSub);

  //Autos autos = new Autos(driveSub, shooterSub, climbSub);

  private final double scale = 1;


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
        -MathUtil.applyDeadband(driver.getLeftY()/slow, deadband), 
        //x
        -MathUtil.applyDeadband(driver.getLeftX()/slow, deadband), 
        //rot
        -MathUtil.applyDeadband(driver.getRightX()/slow, deadband), 
        //field orient
        driver.getRawButton(6)),
        //run command requirements
        driverSub));

  }

  private void configureBindings() {
      driverControls();
      operatorControls();
    
    }

  public void driverControls(){
    new JoystickButton(driver, 5).whileTrue(new RunCommand(() -> driveSub.resetGyro(), driveSub));
    
    new JoystickButton(driver, 2).whileTrue(new RunCommand(() -> driveSub.drive(
      -MathUtil.applyDeadband(driver.getLeftY()/slow, deadband),
      -MathUtil.applyDeadband(driver.getLeftX()/slow, deadband),
         -(LimelightHelpers.getTX("")+0) * 0.015, false), driveSub));
  }

  public void operatorControls(){
      //Single Operations Buttons
      
      //new JoystickButton(operator, 3).whileTrue(new IntakeCmd(intakeSub, 0.5)); // change to intakeCycleCmd
    
      //new JoystickButton(operator, 2).whileTrue(new manIntake(intakeSub, -0.1));
      //new JoystickButton(operator, 3).whileTrue(new manIntake(intakeSub, 0.1));
      
      new JoystickButton(operator, 1).whileTrue(new ManualClimbCmd(climbSub, 1, -1));
      new JoystickButton(operator, 2).whileTrue(new ManualClimbCmd(climbSub, -1, 1));

      new JoystickButton(operator, 4).whileTrue(new ShooterCmd(shooterSub, 0.6));
      //new JoystickButton(operator, 5).whileTrue(new IntakeCmd(intakeSub, 0.5)); 
      new JoystickButton(operator, 6).whileTrue(new IntakeCycleCmd(intakeSub, indexerSub, -0.55, -0.6));
      new JoystickButton(operator, 7).whileTrue(new BeltCmd(shooterSub, indexerSub, 1.0, -0.6));

      //new JoystickButton(operator, 2).whileTrue(new ManualClimbCmd(climbSub, 1, 1, 0));
      //new JoystickButton(operator, 3).whileTrue(new ManualClimbCmd(climbSub, 2, 0, 1));
      //new JoystickButton(operator, 4).whileTrue(new ManualClimbCmd(climbSub, 2, 0, -1));
      
      new JoystickButton(operator, 8).whileTrue(new IntakePIDCmd(intakeSub, 20.0));
      new JoystickButton(operator, 10).whileTrue(new IntakePIDCmd(intakeSub, 0.0));
      //new JoystickButton(operator, 8).whileTrue(new IndexerCmd(indexerSub, -0.2));
      

      
    }

  

  public Command getAutonomousCommand() {
   // return autos.test("s2tm2") ; 
    return  null;
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

  //un implemented

  public Command limelightLineup(){
    RawFiducial[] aprils = LimelightHelpers.getRawFiducials("");
    int[] follow = {1,2,3,4,5,6};
    RawFiducial[] validAprils = new RawFiducial[follow.length];
    RawFiducial closest = new RawFiducial(-2, 9999, 9999,9999, 9999, 9999, 9999);
    int i2 = 0;

    for (RawFiducial meow : aprils){
      int i =  Arrays.binarySearch(aprils, meow);
      
      if (i >=0){
        validAprils[i2] = aprils[i];
      }

      i2++;
    }

    for (int i = 0; i < validAprils.length-1;i++){
      if(closest.distToCamera > validAprils[i].distToCamera){
          closest = validAprils[i];
      }
    }

    if(LimelightHelpers.getFiducialID("") == closest.id){
      return new TurretLimelightCmd(shooterSub, LimelightHelpers.getTX(""));
    } else {
      return new Command() {
        public boolean isFinished() {return true;}
    };
    }
  }
}
