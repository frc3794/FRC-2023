// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer;


public class Drivetrain extends SubsystemBase {
  
private final CANSparkMax m_frontLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[0],
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
private final CANSparkMax m_rearLeft = new CANSparkMax(DrivetrainConstants.kMotorPorts[1],
    CANSparkMaxLowLevel.MotorType.kBrushless);

private final CANSparkMax m_frontRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[2],
    CANSparkMaxLowLevel.MotorType.kBrushless);
    
private final CANSparkMax m_rearRight = new CANSparkMax(DrivetrainConstants.kMotorPorts[3],
    CANSparkMaxLowLevel.MotorType.kBrushless);

private RelativeEncoder m_leftEncoder;
private RelativeEncoder m_rightEncoder;

private final Timer timer = new Timer();


    
  @Override
  public void periodic() {
  }
}
