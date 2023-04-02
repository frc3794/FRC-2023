// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Robot;
import frc.robot.subsystems.Indexer;

public class takeIndexer extends CommandBase {
  
  private final Indexer m_indexer;
  private final CommandXboxController m_subSystemController = Robot.getSubsystemController();

  private final Trigger m_btnX = m_subSystemController.x();
  private final Trigger m_btnB = m_subSystemController.b();
  private final Trigger m_lb = m_subSystemController.leftBumper();
  private final Trigger m_rb = m_subSystemController.rightBumper();

  private int dec = 0;

  public takeIndexer(Indexer indexer) {
    this.m_indexer = indexer;
    addRequirements(this.m_indexer);    
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (m_rb.getAsBoolean())
      this.m_indexer.take();
    else if (m_lb.getAsBoolean()) {
      this.m_indexer.stop();
    }

    if (m_btnX.getAsBoolean() || m_btnB.getAsBoolean()) dec = 1;
    else dec = 0;

    this.m_indexer.eject(dec);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
