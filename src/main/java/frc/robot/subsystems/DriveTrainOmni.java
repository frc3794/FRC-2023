// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
  private final CANSparkMax m_frontLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[0],
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
  private final CANSparkMax m_rearLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[1],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_frontRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[2],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_rearRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[3],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  private final CANSparkMax m_middle = new CANSparkMax(DrivetrainConstants.kMotorPorts[4],
    CANSparkMaxLowLevel.MotorType.kBrushless);

  //MOTOR GROUPS
  private final MotorControllerGroup m_leftMotors = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup m_rightMotors = new MotorControllerGroup(m_frontRight, m_rearRight);
    
  //ENCODERS 
  private RelativeEncoder m_leftEncoder;
  private RelativeEncoder m_rightEncoder;
  private RelativeEncoder m_middleEncoder;

<<<<<<< HEAD
  public DriveTrainOmni() {
    m_left.setInverted(true);

    this.setDefaultCommand(new MoveDrivetrain(this));
=======
  private final DifferentialDrive m_drive;    

  public DriveTrainOmni() { 
    m_leftMotors.setInverted(true);
    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
>>>>>>> e32c6a3705b481404338e8e083fb9cca8471fb07
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive (double speed, double rot) {
    m_drive.arcadeDrive(speed, rot);
  }

  public void stop () {
    m_drive.arcadeDrive(0, 0);
  }
}
