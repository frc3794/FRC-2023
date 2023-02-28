package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Elevator;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.DrivetrainConstants;

public class LiftElevator extends CommandBase {
  private final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private final DigitalInput m_topLimitSwitch = new DigitalInput(DrivetrainConstants.kDigitalInputPorts[1]);
  private final DigitalInput m_bottomLimitSwitch = new DigitalInput(DrivetrainConstants.kDigitalInputPorts[2]);
  private final Trigger m_upDPad = m_subsystemController.povUp();
  private final Trigger m_downDPad = m_subsystemController.povDown();

  public void initialize() {}

  @Override
  public void execute() {
    double elevatorMovement = m_subsystemController.getLeftY();
    if (elevatorMovement > 0.1){
      Elevator.goUp();
    } else if(elevatorMovement < -0.1){
      Elevator.goDown();
    }
    
    if (m_bottomLimitSwitch.get()){
      Elevator.goUp();
    } else if(m_topLimitSwitch.get()){
      Elevator.goDown();
    }

    m_upDPad.onTrue(Elevator.goToPositiveLevel());
    m_downDPad.onTrue(Elevator.goToNegativeLevel());
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
