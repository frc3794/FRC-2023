package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Elevator;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.DrivetrainConstants;

public class LiftElevator extends CommandBase {
  private static CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static DigitalInput m_topLimitSwitch = new DigitalInput(DrivetrainConstants.kDigitalInputPorts[1]);
  private static DigitalInput m_bottomLimitSwitch = new DigitalInput(DrivetrainConstants.kDigitalInputPorts[2]);
  private Trigger m_upDPad = m_subsystemController.povUp();
  private Trigger m_downDPad = m_subsystemController.povDown();

  public void initialize() {}

  @Override
  public void execute() {
    double elevatorMovement = m_subsystemController.getLeftY();
    if (elevatorMovement > 0.1){
      Elevator.GoUp();
    } else if(elevatorMovement < -0.1){
      Elevator.GoDown();
    }
    
    if (m_bottomLimitSwitch.get()){
      Elevator.GoUp();
    } else if(m_topLimitSwitch.get()){
      Elevator.GoDown();
    }

    m_upDPad.onTrue(Elevator.GoToPositiveLevel());
    m_downDPad.onTrue(Elevator.GoToNegativeLevel());
  }

  @Override
  public void end(boolean interrupted) {
    Elevator.StopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
