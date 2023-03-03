// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants.ArmJointConstants;

public class ArmJoint extends SubsystemBase {
  public static final TalonSRX m_armJointMotor = new TalonSRX(6);
  private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static double speed = m_subsystemController.getRightY();

  public ArmJoint() {}
  //Arm Joint Movement
  public static Command extendArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput , speed * ArmJointConstants.kArmJointSpeed);
    return null;
  }
  public static Command flexArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput, speed * -ArmJointConstants.kArmJointSpeed);
    return null;
  }
 
  @Override
  public void periodic() {}

  public static Command stopMotors(){
    m_armJointMotor.set(ControlMode.Disabled, 0);
    return null;
  }
}
