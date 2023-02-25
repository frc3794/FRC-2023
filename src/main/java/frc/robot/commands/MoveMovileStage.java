package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.MovileStage;
import frc.robot.Robot;

public class MoveMovileStage extends CommandBase {
  public static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
   
  
  public MoveMovileStage() {}

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Trigger leftSubsystemBumper = m_subsystemController.leftBumper();
    Trigger rightSubsystemBumper = m_subsystemController.rightBumper();

    leftSubsystemBumper.whileTrue(MovileStage.RetractMovileStage());
    rightSubsystemBumper.whileTrue(MovileStage.ExtendMovileStage());

    MovileStage.GetLimits();
  }

  @Override
  public void end(boolean interrupted) {
    MovileStage.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
