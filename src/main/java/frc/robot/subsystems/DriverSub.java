package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriverSub extends SubsystemBase{

    Drivesubsystem driveSub;
    IntakeSubsystem intakeSub;

    public DriverSub(Drivesubsystem driveSub, IntakeSubsystem intakeSub){
            this.driveSub = driveSub;
            this.intakeSub = intakeSub;
    }


    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldOriented){
        driveSub.drive(xSpeed, ySpeed, rot, fieldOriented);
    }

    
    
}
