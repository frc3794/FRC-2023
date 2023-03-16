// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.subsystems.ArmJoint;

public class ExtendArmJoint extends CommandBase {

  private final CommandXboxController m_subSystemController = Robot.getSubsystemController();
  private final ArmJoint m_armJoint;

  private final Trigger m_btnA = m_subSystemController.a();
  private final Trigger m_btnY = m_subSystemController.y();

  private int extend = 0;
  private boolean pressed = false;

  private final double speed = 0.2;
  
  public ExtendArmJoint(ArmJoint armJoint) {
    this.m_armJoint = armJoint;
    addRequirements(this.m_armJoint);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double m_armJointMovement = m_subSystemController.getRightY();
    double m_movArm = m_subSystemController.getLeftY();

    if (Math.abs (m_armJointMovement) > 0.2){
      m_armJoint.extendArmJoint(Math.abs (m_armJointMovement) * this.speed);
    } else if (Math.abs(m_movArm) > 0.2) {
      m_armJoint.stopMotors();
    } else {
      if (m_btnA.getAsBoolean()) {
        extend = 0; pressed = true;
      } else if (m_btnY.getAsBoolean()) {
        extend = 1; pressed = true;
      }

      if (pressed) {
        m_armJoint.extendArm(extend);
        if (extend == 0)
          pressed = false;
      }
    }    
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