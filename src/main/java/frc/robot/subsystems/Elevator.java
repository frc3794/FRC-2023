package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class Elevator extends SubsystemBase {
  private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static final CANSparkMax m_elevatorMotor1 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[2], CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final CANSparkMax m_elevatorMotor2 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[13], CANSparkMaxLowLevel.MotorType.kBrushless);
   
   private static double speed = m_subsystemController.getLeftY();

  public Elevator() {}
  //Elevator Movement
  public static Command GoUp(){
    m_elevatorMotor1.set(speed * .45);
    m_elevatorMotor2.set(speed * .45);
    return null;
  }
  public static Command GoDown(){
    m_elevatorMotor1.set(speed * -.45);
    m_elevatorMotor2.set(speed * -.45);
    return null;
  }

  @Override
  public void periodic() {
  }

  public static Command StopMotors(){
    m_elevatorMotor1.stopMotor();
    m_elevatorMotor2.stopMotor();
    return null;
  }

}
