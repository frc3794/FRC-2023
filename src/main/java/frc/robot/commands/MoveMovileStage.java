package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.MovileStage;
import frc.robot.Constants.OperatorConstants;

public class MoveMovileStage extends CommandBase {
  private final CommandXboxController m_subsystemController =
   new CommandXboxController(OperatorConstants.kSubsystemControllerPort);

  public MoveMovileStage() {}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  @Override
  public void execute() {
    Trigger leftSubsystemTrigger = m_subsystemController.leftTrigger();
    Trigger rightSubsystemTrigger = m_subsystemController.rightTrigger();

    leftSubsystemTrigger.onTrue(MovileStage.RetractMovileStage());
    rightSubsystemTrigger.onTrue(MovileStage.ExtendMovileStage());

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
