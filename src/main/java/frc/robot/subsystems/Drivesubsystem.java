package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import choreo.trajectory.SwerveSample;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drivesubsystem extends SubsystemBase{
    // Declaring variables (definining our building materials)
    public final SwerveModule frontLeft = new SwerveModule(
        DriveConstants.kFrontLeftDrivingCanId,
        DriveConstants.kFrontLeftTurningCanId,
        DriveConstants.kFrontLeftChassisAngularOffset);
  
    public final SwerveModule frontRight = new SwerveModule(
        DriveConstants.kFrontRightDrivingCanId,
        DriveConstants.kFrontRightTurningCanId,
        DriveConstants.kFrontRightChassisAngularOffset);
  
    public final SwerveModule rearLeft = new SwerveModule(
        DriveConstants.kRearLeftDrivingCanId,
        DriveConstants.kRearLeftTurningCanId,
        DriveConstants.kBackLeftChassisAngularOffset);
  
    public final SwerveModule rearRight = new SwerveModule(
        DriveConstants.kRearRightDrivingCanId,
        DriveConstants.kRearRightTurningCanId,
        DriveConstants.kBackRightChassisAngularOffset);

        private final static AHRS gyro = new AHRS(NavXComType.kMXP_SPI);
  
        SwerveDriveOdometry odometry = new SwerveDriveOdometry(
            DriveConstants.kDriveKinematics,
            Rotation2d.fromDegrees(gyro.getAngle()),
            getSwervePos()
        );

        private final PIDController xController = new PIDController(10.0, 0.0, 0.0);
        private final PIDController yController = new PIDController(10.0, 0.0, 0.0);
        private final PIDController headingController = new PIDController(7.5, 0.0, 0.0);



        //Constructor
        public Drivesubsystem(){
                HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDriveSwerve_MaxSwerve);
        }


        //gets called every 30ms
        @Override
        public void periodic() {
            odometry.update(
            Rotation2d.fromDegrees(gyro.getAngle()), 
            getSwervePos()
            );
            SmartDashboard.putNumber("Robot Heading ", getHeading());
        }


//---------------------------------------------------------------------------------
//      Driving functions
    public void driveRobotRelative(ChassisSpeeds robotRelativeSpeeds) {
    //basically makes the speeds possible for the motors to keep up with 
        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(robotRelativeSpeeds, 0.02);
        SwerveModuleState[] targetStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(targetSpeeds);
        setModuleStates(targetStates);
    }

    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    // Convert the commanded speeds into the correct units for the drivetrain
        double xSpeedDelivered = xSpeed * DriveConstants.kMaxSpeedMetersPerSecond;
        double ySpeedDelivered = ySpeed * DriveConstants.kMaxSpeedMetersPerSecond;
        double rotDelivered = rot * DriveConstants.kMaxAngularSpeed;

        var swerveModuleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(
            fieldRelative
            //For the gyro to work you need to invert the gyro calling then you are good to go  
            ? ChassisSpeeds.fromFieldRelativeSpeeds(
                xSpeedDelivered, ySpeedDelivered, rotDelivered,
                Rotation2d.fromDegrees(-gyro.getAngle()))

            : new ChassisSpeeds(xSpeedDelivered, ySpeedDelivered, rotDelivered));

        SwerveDriveKinematics.desaturateWheelSpeeds(
            swerveModuleStates, DriveConstants.kMaxSpeedMetersPerSecond);

        setModuleStates(swerveModuleStates);    
        //if the robot is slow delete the code line above and put this in place
        /* 
        frontLeft.setDesiredState(swerveModuleStates[0]);
        frontRight.setDesiredState(swerveModuleStates[1]);
        rearLeft.setDesiredState(swerveModuleStates[2]);
        rearRight.setDesiredState(swerveModuleStates[3]);
        */
    }

    //Sets the wheels in an x formation
    public void setX() {
        frontLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(45)));
        frontRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(-45)));
        rearLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(-45)));
        rearRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(45)));
      }
    
      //stops the wheels from moving
      public void stopMoving() {
        frontLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(frontLeft.getState().angle.getDegrees())));
        frontRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(frontRight.getState().angle.getDegrees())));
        rearLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(rearLeft.getState().angle.getDegrees())));
        rearRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(rearRight.getState().angle.getDegrees())));
      }
      //stops the wheels and sets wheel angles to 0
      public void fullStop(){
        frontLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(0)));
        frontRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(0)));
        rearLeft.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(0)));
        rearRight.setDesiredState(new SwerveModuleState(0, Rotation2d.fromDegrees(0)));
      }


//--------------------------------------------------------------------------------
// Reset Commands

//---------------------------------------------------------------------------------
//       Pose/Position and Speed related getters
        public Pose2d getPose() {
            return odometry.getPoseMeters();
        }

        private ChassisSpeeds getRobotRelativeSpeeds(){
                return DriveConstants.kDriveKinematics.toChassisSpeeds(getModuleStates());
        }

        private SwerveModuleState[] getModuleStates(){
            return new SwerveModuleState[]{
                frontLeft.getState(),
                frontRight.getState(),
                rearLeft.getState(),
                rearRight.getState()
            };
        }

        
        public SwerveModulePosition[] getSwervePos(){
            return new SwerveModulePosition[] {
                frontLeft.getPosition(),
                frontRight.getPosition(),
                rearLeft.getPosition(),
                rearRight.getPosition()
            };
        }

//---------------------------------------------------------------------------------
//      Rotation/Heading related getters
        public double getHeading() {
            return Math.IEEEremainder(gyro.getAngle(), 360);
        }

        public double getTurnRate() {
            return -gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
        }

        public Rotation2d geRotation2d() {
            return Rotation2d.fromDegrees(getHeading());
        }
        public void resetGyro(){
            gyro.reset();
        }
        public void resetOdometry(Pose2d pose){
            odometry.resetPosition(
                Rotation2d.fromDegrees(gyro.getAngle()),
                getSwervePos(),
                pose
            );
        }
//---------------------------------------------------------------------------------
//      Pose/Position and Speed related setters
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(
            desiredStates, DriveConstants.kMaxSpeedMetersPerSecond);
            frontLeft.setDesiredState(desiredStates[0]);
            frontRight.setDesiredState(desiredStates[1]);
            rearLeft.setDesiredState(desiredStates[2]);
            rearRight.setDesiredState(desiredStates[3]);
    }

//---------------------------------------------------------------------------------
//           Autonomous related functions
 public void followTrajectory(SwerveSample sample) {
        // Get the current pose of the robot
        Pose2d pose = getPose();

        // Generate the next speeds for the robot
        ChassisSpeeds speeds = new ChassisSpeeds(
            sample.vx + xController.calculate(pose.getX(), sample.x),
            sample.vy + yController.calculate(pose.getY(), sample.y),
            sample.omega + headingController.calculate(pose.getRotation().getRadians(), sample.heading)
        );

        // Apply the generated speeds
        drive(speeds.vxMetersPerSecond,speeds.vyMetersPerSecond,speeds.omegaRadiansPerSecond,true);
    }
}
