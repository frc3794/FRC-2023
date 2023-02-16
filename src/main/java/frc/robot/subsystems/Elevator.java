package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class Elevator extends SubsystemBase {
  private static final CANSparkMax m_elevatorMotor1 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[10], CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final CANSparkMax m_elevatorMotor2 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[11], CANSparkMaxLowLevel.MotorType.kBrushless);

  public Elevator() {}

  public static Command GoUp(){
    m_elevatorMotor1.set(.75);
    m_elevatorMotor2.set(.75);
    return null;
  }
  public static Command GoDown(){
    m_elevatorMotor1.set(-.75);
    m_elevatorMotor2.set(-.75);
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
