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

  private final CANSparkMax m_rearRight = new CANSparkMax(6,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  //MOTOR GROUPS
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);

  private final Timer m_timerForTest = new Timer ();
    
  private RelativeEncoder m_leftEncoder = m_frontLeft.getEncoder();

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
  
  public void moveToDis (double setPoint, double v, double t) {
    double pastPos = m_leftEncoder.getPosition();

    double vel = v;
    double dis = 0.00;
    
    m_timer.reset ();
    m_timer.start ();
    
    if (setPoint < 0) {
      if (vel > 0)
        vel = -vel;

      while (m_timer.get () <= t && dis > setPoint) {
        dis = (m_leftEncoder.getPosition() - pastPos) / Constants.DrivetrainConstants.kRevPerMeters;

        if (dis > setPoint - 4) {
          vel = -0.5;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    } else if (setPoint > 0) {
      while (m_timer.get () <= t && dis < setPoint) {
        dis = (m_leftEncoder.getPosition() - pastPos) / Constants.DrivetrainConstants.kRevPerMeters;

        if (dis < setPoint - 4) {
          vel = 0.35;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    }

    m_drive.stopMotor();
  }

  public void moveToDis2 (double setPoint, double v, double t, double t2) {
    double pastPos = m_leftEncoder.getPosition();

    double vel = v;
    double dis = 0.00;
    
    m_timer.reset ();
    m_timer.start ();

    while (m_timer.get () < t2);

    m_timer.reset ();
    m_timer.start ();
    
    if (setPoint < 0) {
      if (vel > 0)
        vel = -vel;

      while (m_timer.get () <= t && dis > setPoint) {
        dis = (m_leftEncoder.getPosition() - pastPos) / Constants.DrivetrainConstants.kRevPerMeters;

        if (dis > setPoint - 4) {
          vel = -0.35;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    } else if (setPoint > 0) {
      while (m_timer.get () <= t && dis < setPoint) {
        dis = (m_leftEncoder.getPosition() - pastPos) / Constants.DrivetrainConstants.kRevPerMeters;

        if (dis < setPoint - 4) {
          vel = 0.35;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    }

    m_drive.stopMotor();
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
    double pastPos = m_leftEncoder.getPosition();
    double vel = v;
    
    m_timer.reset ();
    m_timer.start ();

    double newRevs = Constants.DrivetrainConstants.kRevPerMeters * setPoint;
    
    if (setPoint < 0) {
      if (vel > 0)
        vel = -vel;

      while (m_timer.get () <= t && (m_leftEncoder.getPosition() - pastPos) > newRevs) {
        if (m_leftEncoder.getPosition() - pastPos > newRevs - 4) {
          vel = -0.25;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    } else if (setPoint > 0) {
      while (m_timer.get () <= t && (m_leftEncoder.getPosition() - pastPos) < newRevs) {
        if (m_leftEncoder.getPosition() - pastPos < newRevs - 4) {
          vel = 0.25;
        }

        m_drive.arcadeDrive(vel, 0);
      }
    }

    m_drive.stopMotor();
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

  public void pastMove (double setPoint, double v, double t) {
    double pastPos = m_leftEncoder.getPosition();
    double vel = v;
    
    m_timer.reset ();
    m_timer.start ();

    double dis = 0.00;
    while (m_timer.get () <= t && dis < setPoint) {
      double pos = m_leftEncoder.getPosition();
      dis = (pos - pastPos) * 7.62;

      m_drive.arcadeDrive(vel, 0);
    }

    m_drive.stopMotor();
  }

  public void testNavx () {
    double pitch = m_gyro.getPitch();
    
    String p = "Pitch: " + String.valueOf(pitch);
    
    SmartDashboard.putString("DB/String 0", p);
  }

  public void rotateAngle (int angle, double v) {
    double vel = Math.abs (v);

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

  public double getPitch () {
    return m_gyro.getPitch();
  }

  public void moveToDistanceByTime (double vel, double t) {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () <= t) {
      m_drive.arcadeDrive(vel, 0);
    }

    m_drive.stopMotor();
  }

  public void wait (double t) {
    m_timerForTest.reset();
    m_timerForTest.start();

    while (m_timerForTest.get () < t);
  }

  public void stopMotors () {
    m_drive.stopMotor();
  }
}
