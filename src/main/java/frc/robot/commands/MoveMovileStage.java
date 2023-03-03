package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.MovileStage;
import frc.robot.Robot;

public class MoveMovileStage extends CommandBase {
  public static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static MovileStage m_movileStage;

  public MoveMovileStage(MovileStage mov) {
    this.m_movileStage = mov;
    addRequirements(this.m_movileStage);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    Trigger leftSubsystemBumper = m_subsystemController.leftBumper();
    Trigger rightSubsystemBumper = m_subsystemController.rightBumper();

    leftSubsystemBumper.whileTrue(this.m_movileStage.retractMovileStage());
    rightSubsystemBumper.whileTrue(this.m_movileStage.extendMovileStage());

    this.m_movileStage.getLimits();
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
