// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.commands.takeIndexer;

public class Indexer extends SubsystemBase {

  private final IndexerConstants kConstants = new Constants.IndexerConstants ();
  
  private final TalonSRX m_contLeft = new TalonSRX (kConstants.kMotorPorts[0]);
  private final TalonSRX m_contRight = new TalonSRX (kConstants.kMotorPorts[1]);

  private final Timer m_timer = new Timer ();

  public Indexer() {
    m_contRight.setInverted(true);
    this.setDefaultCommand(new takeIndexer(this));
  }

  public void stop () {
    m_contLeft.set(TalonSRXControlMode.Velocity, 0);
    m_contRight.set(TalonSRXControlMode.Velocity, 0);
  }

  public void take () {
    m_contLeft.set(TalonSRXControlMode.Velocity, 0.25);
    m_contRight.set(TalonSRXControlMode.Velocity, 0.25);
  }

  public void eject (int dec) {
    m_contLeft.set(TalonSRXControlMode.Velocity, -0.25 * dec);
    m_contRight.set(TalonSRXControlMode.Velocity, -0.25 * dec);
  }

  public void ejectAuto (double vel, double time) {
    m_timer.reset();
    m_timer.start();

    while (m_timer.get () < time) {
      m_contLeft.set(TalonSRXControlMode.Velocity, -vel);
      m_contRight.set(TalonSRXControlMode.Velocity, -vel);
    }

    m_timer.stop();

    eject (0);
  }

  @Override
  public void periodic() {
    
  }
}