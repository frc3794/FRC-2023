// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class ArmJoint extends SubsystemBase {
  private static final CANSparkMax m_armJointMotor = new CANSparkMax(DrivetrainConstants.kMotorPorts[9],
   CANSparkMaxLowLevel.MotorType.kBrushless);
  private static final CANSparkMax m_wristMotor = new CANSparkMax(DrivetrainConstants.kMotorPorts[13], 
  CANSparkMaxLowLevel.MotorType.kBrushless);
  public ArmJoint() {}
  //Arm Joint Movement
  public static Command ExtendArmJoint(){
    m_armJointMotor.set(.60);
    return null;
  }
  public static Command FlexArmJoint(){
    m_armJointMotor.set(-.60);
    return null;
  }
  public static Command WristMotorFollow(){
    m_wristMotor.follow(m_armJointMotor, true);
    return null;
  }


  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
