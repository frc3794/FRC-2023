package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DriveTrain;

public class MoveDrivetrain extends CommandBase {
  
  private DriveTrain m_drivetrain;

  private final CommandXboxController m_driverController =
    new CommandXboxController(0);

  public MoveDrivetrain(DriveTrain drivetrain) {
    this.m_drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double fwd = 0, rot = 0;

    double rightTrigger = Math.abs(m_driverController.getRightTriggerAxis());
    double leftTrigger = Math.abs(m_driverController.getLeftTriggerAxis());

    fwd = rightTrigger > 0.2 ? rightTrigger : -leftTrigger;

    double rightJoystick = m_driverController.getRightX();
    double leftJoystick = m_driverController.getLeftX();

    rot = Math.abs (rightJoystick) > 0.2 ? (rightJoystick * 0.8) : (leftJoystick);

    fwd *= DrivetrainConstants.kSensibilityPercent;
    rot *= DrivetrainConstants.kSensibilityPercent * -0.8;

    m_drivetrain.arcadeDrive (fwd, rot);

    //SmartDashboard.putString("DB/String 0", "HELLO A");
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
