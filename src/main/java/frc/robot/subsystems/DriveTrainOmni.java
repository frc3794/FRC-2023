package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.MoveDrivetrain;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

public class DriveTrainOmni extends SubsystemBase {
 //MOTORS
  private final CANSparkMax m_frontLeft = new CANSparkMax(15,
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_rearLeft = new CANSparkMax(0,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_frontRight = new CANSparkMax(12,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_rearRight = new CANSparkMax(3,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  //MOTOR GROUPS
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);
    
  private RelativeEncoder m_leftEncoder = m_frontLeft.getEncoder();
  private RelativeEncoder m_rightEncoder = m_rearRight.getEncoder();

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private final Timer m_timer = new Timer ();
  
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

  public void moveToDistance (double setPoint, double v, double t) {
    double vel = v;

    m_timer.reset ();
    m_timer.start ();

    m_drive.arcadeDrive(vel, 0);
    
    double leftVel = m_leftEncoder.getVelocity() / 60.00;
    double rightVel = m_rightEncoder.getVelocity() / 60.00;

    double leftDis = leftVel * 7.62;
    double rightDis = rightVel * 7.62;

    double timeTo = 0.00;

    timeTo = setPoint / leftDis;
    while (timeTo > t) {
      vel = vel + 0.1;

      leftVel = m_leftEncoder.getVelocity() / 60.00;
      rightVel = m_rightEncoder.getVelocity() / 60.00;
  
      leftDis = leftVel * 7.62;
      rightDis = rightVel * 7.62;

      timeTo = setPoint / leftDis;
    }

    while (m_timer.get () <= timeTo) {
      m_drive.arcadeDrive(vel, 0);
    }

    m_drive.arcadeDrive(0, 0);
  }
}
