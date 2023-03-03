package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Elevator;
import frc.robot.Robot;

public class LiftElevator extends CommandBase {
  private final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private final Trigger m_upDPad = m_subsystemController.povUp();
  private final Trigger m_downDPad = m_subsystemController.povDown();

  private Elevator m_elevator;

  public void initialize() {}

  public LiftElevator (Elevator elev) {
    this.m_elevator = elev;
    addRequirements(this.m_elevator);
  }

  @Override
  public void execute() {
    double elevatorMovement = m_subsystemController.getLeftY();
    if (elevatorMovement > 0.1){
      m_elevator.goUp();
    } else if(elevatorMovement < -0.1){
      m_elevator.goDown();
    }
    m_upDPad.onTrue(m_elevator.goToPositiveLevel());
    m_downDPad.onTrue(m_elevator.goToNegativeLevel());
  }

  @Override
  public void end(boolean interrupted) {
    m_elevator.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
