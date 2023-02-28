// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import frc.robot.subsystems.ArmJoint;

public class ExtendArmJoint extends CommandBase {
  private final CommandXboxController m_subSystemController = Robot.getSubsystemController();
  
  public ExtendArmJoint() {}

  @Override
  public void initialize() {
    ArmJoint.wristMotorFollow(); /* */
  }

  @Override
  public void execute() {
    double armJointMovement = m_subSystemController.getRightY();
    if (armJointMovement > 0.1){
      ArmJoint.extendArmJoint();
    } else if (armJointMovement < -0.1){
      ArmJoint.flexArmJoint();
    }
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