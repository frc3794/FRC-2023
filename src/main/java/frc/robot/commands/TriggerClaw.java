package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;

public class TriggerClaw extends CommandBase {

    private final CommandXboxController m_subsystemController = Robot.getSubsystemController();
    private final double leftTrigger = Math.abs (m_subsystemController.getLeftTriggerAxis());
    private final double rightTrigger = Math.abs (m_subsystemController.getRightTriggerAxis());

    private Claw m_claw;

    public TriggerClaw(Claw claw) {
      this.m_claw = claw;
      addRequirements(this.m_claw);
    }

  @Override
  public void initialize() {}

  @Override
  public void execute(){
    if (leftTrigger > 0.2)
      this.m_claw.openClaw();
    else if (rightTrigger > 0.2)
      this.m_claw.closeClaw();
  }

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
    
}
