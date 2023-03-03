package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MoveDrivetrain;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

public class DriveTrain extends SubsystemBase {
 //MOTORS
  private final CANSparkMax m_frontLeft = new CANSparkMax(15,
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_rearLeft = new CANSparkMax(7,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_frontRight = new CANSparkMax(12,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_rearRight = new CANSparkMax(3,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  //MOTOR GROUPS
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);

  private final Timer m_timerForTest = new Timer ();
    
  private RelativeEncoder m_leftEncoder = m_frontLeft.getEncoder();
  private RelativeEncoder m_rightEncoder = m_rearRight.getEncoder();

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private final Timer m_timer = new Timer ();
  
  public DriveTrain () {
    m_rightMotors.setInverted(true);

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

  public void testMotors () {
    m_timerForTest.reset();
    m_timerForTest.start();

    while (m_timerForTest.get () < 1) {
      m_rearLeft.set(1);
    }
    m_rearLeft.set (0);

    while (m_timerForTest.get () < 2) {
      m_frontLeft.set(1);
    }
    m_frontLeft.set (0);

    while (m_timerForTest.get () < 3) {
      m_rearRight.set(1);
    }
    m_rearRight.set (0);

    while (m_timerForTest.get () < 4) {
      m_frontRight.set(1);
    }
    m_frontRight.set (0);

    while (m_timerForTest.get () < 5) {
      m_leftMotors.set(1);
    }
    m_leftMotors.set (0);

    while (m_timerForTest.get () < 6) {
      m_rightMotors.set(1);
    }
    m_rightMotors.set (0);
  }

  public void moveToDistance (double setPoint, double v, double t) {
    double vel = v;

    m_timer.reset ();
    m_timer.start ();

    double leftVel = m_leftEncoder.getVelocity() / 60.00;
    double rightVel = m_rightEncoder.getVelocity() / 60.00;

    double leftDis = leftVel * 7.62;
    double rightDis = rightVel * 7.62;

    while (m_timer.get () < 1) {
      leftVel = m_leftEncoder.getVelocity() / 60.00;
      leftDis = leftVel * 7.62;
    }

    m_drive.arcadeDrive(vel, 0);

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

  public void moveToDistanceByTime (double vel, double t) {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () <= t) {
      m_drive.arcadeDrive(vel, 0);
    }

    m_drive.stopMotor();
  }
}
