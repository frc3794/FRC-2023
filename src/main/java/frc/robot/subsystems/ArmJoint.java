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

  private final double defaultSpeed = 0.7;
  private final double limitExtend = 3.0;
  private final double limitBack = 0.00;

  private final CANSparkMax m_motor1 = new CANSparkMax(2,
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_motor2 = new CANSparkMax(1,
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final MotorControllerGroup m_motors = new MotorControllerGroup(m_motor1, m_motor2);

  private RelativeEncoder m_encoder1 = m_motor1.getEncoder();

  private final Timer m_timer = new Timer ();

  public ArmJoint() {
    this.setDefaultCommand(new ExtendArmJoint (this));
  }

  public void extendArmJoint(double speed){
    if (m_encoder1.getPosition() < limitExtend)
      m_motors.set(defaultSpeed * speed);
    else
      m_motors.set (0);
  }

  public void flexArmJoint(double speed){
    if (m_encoder1.getPosition() > limitBack)
      m_motors.set(-defaultSpeed * speed);
    else
      m_motors.set (0);
  }

  public void testEncoder () {
    double pos = m_encoder1.getPosition();
    String p = "Enc: " + String.valueOf(pos);
    SmartDashboard.putString("DB/String 4", p);
  }

  public void watchMe (double vel, double t) {
    double limitUp = 10, limitDown = 6;
    
    m_timer.reset ();
    m_timer.start ();

    while (m_timer.get () <= t) {
      if (m_encoder1.getPosition() < limitUp)
        m_motors.set (vel);
      else if (m_encoder1.getPosition() > limitDown)
        m_motors.set (-vel);
    }

    m_motors.set (0);
  }
 
  @Override
  public void periodic() {}

  public void stopMotors(){
    m_motors.set (0);
  }
}
