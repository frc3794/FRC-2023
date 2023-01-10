package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DrivetrainMec;

public class MoveDrivetrainMec extends CommandBase {
  
  private final DrivetrainMec m_drivetrain;
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public MoveDrivetrainMec(DrivetrainMec drivetrain) {
    this.m_drivetrain = drivetrain;
    addRequirements(drivetrain);

    configureBindings();
  }

  @Override
  public void initialize() {}

  private void configureBindings() {
    // Trigger(m_exampleSubsystem::exampleCondition)
    //    .onTrue(new ExampleCommand(m_exampleSubsystem));

    //m_driverController.b ().whileTrue (m_exampleSubsystem.exampleMethodCommand ());
  }

  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
