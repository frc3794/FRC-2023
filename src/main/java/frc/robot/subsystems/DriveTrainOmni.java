package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.MoveDrivetrain;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

public class DriveTrainOmni extends SubsystemBase {
 //MOTORS
  private final CANSparkMax m_frontLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[14],
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_rearLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[15],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_frontRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[1],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_rearRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[0],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  //MOTOR GROUPS
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);
    
  private RelativeEncoder m_leftEncoder;
  private RelativeEncoder m_rightEncoder;

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  
  public DriveTrainOmni () {
    m_leftMotors.setInverted(true);

    this.setDefaultCommand(new MoveDrivetrain(this));
  }

  @Override
  public void periodic() {}

  public void arcadeDrive (double speed, double rot) {
    m_drive.arcadeDrive(speed, rot);
  }

  public void stop () {
    m_drive.arcadeDrive(0, 0);
  }

  private double getEncoderDistance (int side) {
    if (side == 0) {
      m_leftEncoder = m_frontLeft.getEncoder();
      return (7.62 * Math.PI) * (m_leftEncoder.getPosition());
    } else if (side == 1) {
      m_rightEncoder = m_frontRight.getEncoder();
      return (7.62 * Math.PI) * (m_rightEncoder.getPosition());
    } else {
      return 0;
    }
  }

  public void moveToDistance (double setPoint) {
    double pastDistanceL = getEncoderDistance(0);
    double pastDistanceR = getEncoderDistance(1);

    
  }
}
