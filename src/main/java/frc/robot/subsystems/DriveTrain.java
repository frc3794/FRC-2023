package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.MoveDrivetrain;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

public class DriveTrain extends SubsystemBase {

  private final DrivetrainConstants kConstants = new Constants.DrivetrainConstants();
 
  private final CANSparkMax m_frontLeft = new CANSparkMax(kConstants.kMotorPorts[0],
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_rearLeft = new CANSparkMax(kConstants.kMotorPorts[1],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_frontRight = new CANSparkMax(kConstants.kMotorPorts[2],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_rearRight = new CANSparkMax(kConstants.kMotorPorts[3],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);

  private final Timer m_timerForTest = new Timer ();
    
  private RelativeEncoder m_leftEncoder = m_frontLeft.getEncoder();
  private RelativeEncoder m_rightEncoder = m_frontRight.getEncoder();

  private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private final Timer m_timer = new Timer ();

  private final double posAt90 = 6.6;
  
  public DriveTrain () {
    m_rightMotors.setInverted(true);
    this.setDefaultCommand(new MoveDrivetrain(this));
  }

  @Override
  public void periodic() {}

  public void arcadeDrive (double speed, double rot) {
    m_drive.arcadeDrive(speed, rot);
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

    m_timerForTest.stop();
  }

  public void testMotor (int can) {
    switch (can) {
      case 15: m_frontLeft.set (1);
      case 6: m_rearRight.set (1);
      case 7: m_rearLeft.set (1);
      case 12: m_frontRight.set (1);
    }
  }

  public void testEncoder (){
    double pos_l = m_leftEncoder.getPosition();

    String l = "Drive: " + String.valueOf(pos_l);
    
    SmartDashboard.putString("DB/String 1", l); 
  }

  public void resetNavX () {
    m_gyro.reset();
    m_gyro.zeroYaw();
    m_gyro.calibrate();
  }

  public void testNavx () {
    double pitch = m_gyro.getPitch();
    
    String p = "Pitch: " + String.valueOf(pitch);
    
    SmartDashboard.putString("DB/String 0", p);
  }

  public void moveToDistance (double setPoint, double v, double t, double t2) {
    double pastPosRight = -m_rightEncoder.getPosition();
    double pastPosLeft = m_leftEncoder.getPosition();

    double posRight, posLeft, posL = 0, posR = 0;

    double velL = v, velR = v;
    
    m_timer.reset ();
    m_timer.start ();

    while (m_timer.get () < t2);

    m_timer.reset ();
    m_timer.start ();
    
    if (setPoint < 0) {
      if (v > 0) {
        velR = -v; velL = -v;
      }

      posLeft = (setPoint * kConstants.kToughBox) / kConstants.kCircWheels;
      posRight = (setPoint * kConstants.kToughBox) / kConstants.kCircWheels;

      while (m_timer.get () <= t && !(velL != 0 && velR != 0)) {
        posL = m_leftEncoder.getPosition() - pastPosLeft;
        posR = -m_rightEncoder.getPosition() - pastPosRight;

        if (posL > posLeft) {
          velL *= 1;
          if (posL < posLeft - (kConstants.kToughBox * 1.5))
            velL = -0.27;
        } else
          velL = 0;

        if (posR > posRight) {
          velR *= 1;
          if (posR < posRight - (kConstants.kToughBox * 1.5))
            velR = -0.27;
        } else
          velR = 0;

        tankDrive(velL, velR);
      }
    } else if (setPoint > 0) {
      posLeft = (setPoint * kConstants.kToughBox) / kConstants.kCircWheels;
      posRight = (setPoint * kConstants.kToughBox) / kConstants.kCircWheels;

      while (m_timer.get () <= t && !(velL != 0 && velR != 0)) {
        posL = m_leftEncoder.getPosition() - pastPosLeft;
        posR = -m_rightEncoder.getPosition() - pastPosRight;

        if (posL < posLeft) {
          velL *= 1;
          if (posL > posLeft - (kConstants.kToughBox * 1.5))
            velL = 0.27;
        } else
          velL = 0;

        if (posR < posRight) {
          velR *= 1;
          if (posR > posRight - (kConstants.kToughBox * 1.5))
            velR = 0.27;
        } else
          velR = 0;

        tankDrive(velL, velR);
      }
    }

    m_drive.stopMotor();

    m_timer.stop();
  }

  public void rotateAngle (int angle, double v, double t) {
    double vel = Math.abs (v);

    m_timer.reset ();
    m_timer.start ();

    while (m_timer.get () < t);
    
    m_timer.stop ();

    if (angle > 0) {
      double newRevs = (angle * posAt90) / 90.00;
      double pastPos = m_leftEncoder.getPosition();
      double newPos = pastPos + newRevs;
      
      while (m_leftEncoder.getPosition() < newPos) {
        m_leftMotors.set(vel);
        m_rightMotors.set(-vel);
        m_drive.feed();
      }

      m_drive.stopMotor();

    } else if (angle < 0) {
      double newRevs = (angle * posAt90) / 90.00;
      double pastPos = m_leftEncoder.getPosition();
      double newPos = pastPos + newRevs;
      
      while (m_leftEncoder.getPosition() > newPos) {
        m_leftMotors.set(-vel);
        m_rightMotors.set(vel);
        m_drive.feed();
      }
    }
 
    m_drive.stopMotor();
    
  }

  private void tankDrive (double sl, double sr) {
    m_drive.tankDrive(sl, sr);
  }

  public void moveToDistanceByTime (double vel, double t) {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () <= t) {
      m_drive.arcadeDrive(vel, 0);
    }

    m_timer.stop ();

    m_drive.stopMotor();
  }

  public void level () {
    
  }

  public void stopMotors () {
    m_drive.stopMotor();
  }
}
