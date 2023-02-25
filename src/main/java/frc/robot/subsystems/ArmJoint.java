// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import frc.robot.Constants.DrivetrainConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class ArmJoint extends SubsystemBase {
  private static final VictorSPX m_armJointMotor = new VictorSPX(DrivetrainConstants.kMotorPorts[12]);
  private static final VictorSPX m_wristMotor = new VictorSPX(DrivetrainConstants.kMotorPorts[4]); /**/
  private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static double speed = m_subsystemController.getRightY();

  public ArmJoint() {}
  //Arm Joint Movement
  public static Command ExtendArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput , speed * .25);
    return null;
  }
  public static Command FlexArmJoint(){
    m_armJointMotor.set(ControlMode.PercentOutput, speed * .25);
    return null;
  }
  public static Command WristMotorFollow()    {
    m_wristMotor.set(ControlMode.Follower, 12); /* */
    return null;
  }
 
  @Override
  public void periodic() {}

  public static Command StopMotors(){
    m_armJointMotor.set(ControlMode.Disabled, 0);
    m_wristMotor.set(ControlMode.Disabled, 0);
    return null;
  }
}
