// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import frc.robot.subsystems.ArmJoint;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.ArmJointConstants;

public class ExtendArmJoint extends CommandBase {
  private final CommandXboxController m_subSystemController = Robot.getSubsystemController();
  private final DigitalInput m_armJointSwitch = new DigitalInput(2);
  private final ArmJoint m_armJoint;
  
  public ExtendArmJoint(ArmJoint armJoint) {
    this.m_armJoint = armJoint;
    addRequirements(this.m_armJoint);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double m_armJointMovement = Math.abs (m_subSystemController.getRightTriggerAxis());
    double m_armJointMovement2 = Math.abs (m_subSystemController.getLeftTriggerAxis());
    if (m_armJointMovement > 0.2){
      m_armJoint.extendArmJoint(m_armJointMovement);
    } else if (m_armJointMovement2 < -0.2){
      m_armJoint.flexArmJoint(m_armJointMovement);
    }

    /*if (m_armJointSwitch.get()){
      m_armJoint.m_armJointMotor.set(ControlMode.PercentOutput, 0);
    }*/
  }

  @Override
  public void end(boolean interrupted) {
    ArmJoint.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}