package frc.robot;

import choreo.auto.AutoFactory;

public class Autos {



    AutoFactory autoFactory =new AutoFactory(
    //these are method pointers they basically tell the code "Hey the method you want is right here"
        RobotContainer.driveSub::getPose,
        RobotContainer.driveSub::resetOdometry,
        RobotContainer.driveSub::followTrajectory,
        false, 
        RobotContainer.driveSub
    );


    
    
}
