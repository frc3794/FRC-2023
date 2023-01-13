// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MoveDrivetrain;
import frc.robot.subsystems.DriveTrainOmni;

public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final DriveTrainOmni m_drive =  m_robotContainer.gDriveTrainOmni();
  private final Command m_driveCommand = new MoveDrivetrain (m_drive);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }
 @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

          if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    m_driveCommand.execute();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
