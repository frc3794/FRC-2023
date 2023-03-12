// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.subsystems.ArmJoint;

public class ExtendArmJoint extends CommandBase {

  private final CommandXboxController m_subSystemController = Robot.getSubsystemController();
  private final ArmJoint m_armJoint;

  private final double speed = 0.5;

  private final Trigger m_btnA = m_subSystemController.a();
  private final Trigger m_btnY = m_subSystemController.y();
  
  public ExtendArmJoint(ArmJoint armJoint) {
    this.m_armJoint = armJoint;
    addRequirements(this.m_armJoint);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double m_armJointMovement = m_subSystemController.getRightY() * this.speed;

    if (m_armJointMovement > 0.2){
      m_armJoint.extendArmJoint(m_armJointMovement);
    } else if (m_armJointMovement < -0.2){
      m_armJoint.flexArmJoint(m_armJointMovement);
    } else {
      m_btnA.onTrue(extendArm());
      m_btnY.onTrue(flexArm());
    }

  }

  private Command extendArm () {
    m_armJoint.extendArmJoint(this.speed);
    return new WaitCommand (0.00);
  }

  private Command flexArm () {
    m_armJoint.flexArmJoint(this.speed);
    return new WaitCommand (0.00);
  }

  @Override
  public void end(boolean interrupted) {
    m_armJoint.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}