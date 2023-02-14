package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.MoveDrivetrain;
import frc.robot.subsystems.DriveTrainOmni;

public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private final DriveTrainOmni m_drive =  m_robotContainer.gDriveTrainOmni();
  private final Command m_driveCommand = new MoveDrivetrain (m_drive);
  private static final CommandXboxController subsystemController = new CommandXboxController(1);

  public static CommandXboxController getSubsystemController() {
    return subsystemController;
  }

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
