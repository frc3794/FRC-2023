package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Robot;
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;


public class Elevator extends SubsystemBase {
  private static final CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private static final CANSparkMax m_elevatorMotor1 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[2], CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final CANSparkMax m_elevatorMotor2 =
   new CANSparkMax(DrivetrainConstants.kMotorPorts[13], CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final RelativeEncoder m_elevatorEncoder = m_elevatorMotor1.getEncoder();
   
   private static int level = 0;
   private static double speed = m_subsystemController.getLeftY();

  public Elevator() {}
  //"Free" Elevator Movement
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
  //Level Based Elevator Movement
  public static Command GoToPositiveLevel(){
    ReturnPlusLevel();
    switch (level){
      case 1:
      while (m_elevatorEncoder.getPosition() < 1000){
        m_elevatorMotor1.set(.45);
        m_elevatorMotor2.set(.45);
      }
      break;
      case 2:
      while (m_elevatorEncoder.getPosition() < 2000){
        m_elevatorMotor1.set(.45);
        m_elevatorMotor2.set(.45);
      }
      break;
    }
    return null;
  }

  public static Command GoToNegativeLevel(){
    ReturnMinusLevel();
    switch (level){
      case 0:
      while (m_elevatorEncoder.getPosition() > 0){
        m_elevatorMotor1.set(-.45);
        m_elevatorMotor2.set(-.45);
      }
      break;
      case 1:
      while (m_elevatorEncoder.getPosition() > 1000){
        m_elevatorMotor1.set(-.45);
        m_elevatorMotor2.set(-.45);
      }
      break;
    }
    return null;
  }
  //Getting the current level
  public static int ReturnPlusLevel(){
    level = level + 1;
    if (level > 2){
      level = 2;
    }
    return level;
  }

  public static int ReturnMinusLevel(){
    level = level - 1;
    if (level < 0){
      level = 0;
    }
    return level;
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
