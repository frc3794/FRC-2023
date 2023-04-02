package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveTrain;

public class MoveDrivetrain extends CommandBase {
  
  private DriveTrain m_drivetrain;

  private final CommandXboxController m_controller =
    new CommandXboxController(0);

  public MoveDrivetrain(DriveTrain drivetrain) {
    this.m_drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    m_drivetrain.resetNavX(); 
  }

  @Override
  public void execute() {
    double fwd = 0, rot = 0;

    double rightTrigger = Math.abs(m_controller.getRightTriggerAxis());
    double leftTrigger = Math.abs(m_controller.getLeftTriggerAxis());

    if (rightTrigger > 0.2) fwd = rightTrigger;
    else if (leftTrigger > 0.2) fwd = -leftTrigger;
    else fwd = 0;

    double rightJoystick = m_controller.getRightX();
    double leftJoystick = m_controller.getLeftX();

    if (Math.abs(rightJoystick) > 0.2) rot = rightJoystick * 0.35;
    else if (Math.abs(leftJoystick) > 0.2) rot = leftJoystick;
    else rot = 0;

    fwd *= 0.67;

    m_drivetrain.arcadeDrive(fwd, -rot);

    //this.m_drivetrain.testMotors();

    //m_drivetrain.testEncoder();
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
