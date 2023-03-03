package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants.ElevatorConstants;


public class Elevator extends SubsystemBase {
  private final static CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private final static CANSparkMax m_elevatorMotor1 =
   new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final CANSparkMax m_elevatorMotor2 =
   new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final RelativeEncoder m_elevatorEncoder = m_elevatorMotor1.getEncoder();
   
   private static int level = 0;
   private static double speed = m_subsystemController.getLeftY();

  public Elevator() {
    m_elevatorMotor2.setInverted(true);
  }
  
  //"Free" Elevator Movement
  public static Command goUp(){
    m_elevatorMotor1.set(speed * ElevatorConstants.kElevatorSpeed);
    m_elevatorMotor2.set(speed * ElevatorConstants.kElevatorSpeed);
    return null;
  }
  public static Command goDown(){
    m_elevatorMotor1.set(speed * -ElevatorConstants.kElevatorSpeed);
    m_elevatorMotor2.set(speed * -ElevatorConstants.kElevatorSpeed);
    return null;
  }
  //Level Based Elevator Movement
  public static Command goToPositiveLevel(){
    returnPlusLevel();
    switch (level){
      case 1:
      while (m_elevatorEncoder.getPosition() < ElevatorConstants.kEncoderLimits[1]){
        m_elevatorMotor1.set(ElevatorConstants.kElevatorSpeed);
        m_elevatorMotor2.set(ElevatorConstants.kElevatorSpeed);
      }
      break;
      case 2:
      while (m_elevatorEncoder.getPosition() < ElevatorConstants.kEncoderLimits[2]){
        m_elevatorMotor1.set(ElevatorConstants.kElevatorSpeed);
        m_elevatorMotor2.set(ElevatorConstants.kElevatorSpeed);
      }
      break;
    }
    return null;
  }

  public static Command goToNegativeLevel(){
    returnMinusLevel();
    switch (level){
      case 0:
      while (m_elevatorEncoder.getPosition() > ElevatorConstants.kEncoderLimits[0]){
        m_elevatorMotor1.set(-ElevatorConstants.kElevatorSpeed);
        m_elevatorMotor2.set(-ElevatorConstants.kElevatorSpeed);
      }
      break;
      case 1:
      while (m_elevatorEncoder.getPosition() > ElevatorConstants.kEncoderLimits[1]){
        m_elevatorMotor1.set(-ElevatorConstants.kElevatorSpeed);
        m_elevatorMotor2.set(-ElevatorConstants.kElevatorSpeed);
      }
      break;
    }
    return null;
  }
  //Getting the current level
  public static int returnPlusLevel(){
    level = level + 1;
    if (level > 2){
      level = 2;
    }
    return level;
  }

  public static int returnMinusLevel(){
    level = level - 1;
    if (level < 0){
      level = 0;
    }
    return level;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Encoder Position", m_elevatorEncoder.getPosition());
  }

  public static Command stopMotors(){
    m_elevatorMotor1.stopMotor();
    m_elevatorMotor2.stopMotor();
    return null;
  }

}
