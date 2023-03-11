package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private final Timer m_timer = new Timer ();

  private final double kP = 1.00, kI = 1.00, kD = 1.00;
  private PIDController pid = new PIDController(kP, kI, kD);

  private final double P = 1.00, I = 1.00, D = 1.00;
  private double error, integral, derivative, rcw, previous_error;
  
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

  private double encodersDis () {
    double pastPos = m_leftEncoder.getPosition();

    double dis = 0.00;

    double pos = m_leftEncoder.getPosition();
    dis = (pos - pastPos) * 6 * 2.54 * Math.PI;

    return dis;
  }

  public void testPID (double setPoint) {
    m_drive.arcadeDrive(pid.calculate(encodersDis (), setPoint), 0);
  }

  public void testOtherPID (double setPoint) {
    error = setPoint - encodersDis();
    integral += (error * .01);
    derivative = (error - previous_error) / .01;
    rcw = P * error + I * integral + D * derivative;

    m_drive.arcadeDrive(rcw, 0);
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
    
    m_timer.reset ();
    m_timer.start ();

    double dis = 0.00;

    while (m_timer.get () <= t && dis < setPoint) {
      double pos = m_leftEncoder.getPosition();
      dis = (pos - pastPos) * 6 * 2.54 * Math.PI;

      m_drive.arcadeDrive(v, 0);
    }

    m_drive.arcadeDrive(0, 0);
  }

  public void testEncoder (){
    double pos_l = m_leftEncoder.getPosition();
    double pos_r = m_rightEncoder.getPosition();

    String l = String.valueOf(pos_l);
    String r = String.valueOf(pos_r);
    
    SmartDashboard.putString("DB/String 1", l);
    SmartDashboard.putString("DB/String 2", r); 
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
}
