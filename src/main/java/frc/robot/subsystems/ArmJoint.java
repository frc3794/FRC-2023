// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ExtendArmJoint;

public class ArmJoint extends SubsystemBase {

  private final double defaultSpeed = 0.15;
  private double limitExtend;
  private double limitBack;

  private final CANSparkMax m_motor1 = new CANSparkMax(2,
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_motor2 = new CANSparkMax(1,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final MotorControllerGroup m_motors = new MotorControllerGroup(m_motor1, m_motor2);

  private RelativeEncoder m_encoder1 = m_motor1.getEncoder();

  private final Timer m_timer = new Timer ();

  public boolean extended = false;

  public ArmJoint() {
    this.setDefaultCommand(new ExtendArmJoint (this));
    limitExtend = m_encoder1.getPosition() + 3.2;
    limitBack = m_encoder1.getPosition() + 0.1;
  }

  public void extendArmJoint(double speed){
    if (m_encoder1.getPosition() < limitExtend)
      m_motors.set(speed);
    else
      m_motors.set (0);
  }

  public void brake (boolean x) {
    if (x) {
      if (m_encoder1.getPosition() > limitBack)
        m_motors.set(0);
      else
        m_motors.set (0.07);
    }
  }

  public void flexArmJoint (double speed){
    if (m_encoder1.getPosition() > limitBack)
      m_motors.set(-speed);
    else
      m_motors.set (0);
  }

  public void extendArm (int dec) {
    int decEnc = 1;
    if (m_encoder1.getPosition() >= limitExtend)
      decEnc = 0;

    m_motors.set(defaultSpeed * dec * decEnc);
  }

  public void testEncoder () {
    double pos = m_encoder1.getPosition();
    String p = "En0: " + String.valueOf(pos);
    SmartDashboard.putString("DB/String 0", p);
  }

  public void watchMe (double vel, double t) {
    double limitUp = 10, limitDown = 6;
    
    m_timer.reset ();
    m_timer.start ();

    while (m_timer.get () <= t - 1) {
      if (m_encoder1.getPosition() < limitUp)
        m_motors.set (vel);
      else if (m_encoder1.getPosition() > limitDown)
        m_motors.set (-vel);
    }

    if (m_encoder1.getPosition() > limitDown) {
      m_motors.set (-vel);
    }

    m_motors.set (0);
  }
 
  @Override
  public void periodic() {}

  public void stopMotors(){
    m_motor1.stopMotor();
    m_motor2.stopMotor();
  }
}
