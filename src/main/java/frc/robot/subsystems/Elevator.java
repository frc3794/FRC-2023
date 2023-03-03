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
import frc.robot.commands.LiftElevator;


public class Elevator extends SubsystemBase {
  private final static CommandXboxController m_subsystemController = Robot.getSubsystemController();
  private final static CANSparkMax m_elevatorMotor1 =
   new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final CANSparkMax m_elevatorMotor2 =
   new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
   private static final RelativeEncoder m_elevatorEncoder = m_elevatorMotor1.getEncoder();
   
   private static int level = 0;

   private final static double elevatorSpeed = 0.5;

  public Elevator() {
    this.setDefaultCommand(new LiftElevator (this));
  }
  
  //"Free" Elevator Movement
  public static void goUp(double speed){
    m_elevatorMotor1.set(speed * elevatorSpeed);
    m_elevatorMotor2.set(speed * elevatorSpeed);
  }
  
  public static void goDown(double speed){
    m_elevatorMotor1.set(speed * elevatorSpeed);
    m_elevatorMotor2.set(speed * elevatorSpeed);
  }
  //Level Based Elevator Movement
  public static Command goToPositiveLevel(){
    level += 1;
    switch (level){
      case 1:
        while (m_elevatorEncoder.getPosition() < ElevatorConstants.kEncoderLimits[1]){
          m_elevatorMotor1.set(elevatorSpeed);
          m_elevatorMotor2.set(elevatorSpeed);
        }
      break;
      case 2:
        while (m_elevatorEncoder.getPosition() < ElevatorConstants.kEncoderLimits[2]){
          m_elevatorMotor1.set(elevatorSpeed);
          m_elevatorMotor2.set(elevatorSpeed);
        }
      break;
      default:
        level = 2; break;
    }
    return null;
  }

  public static Command goToNegativeLevel(){
    level -= 1;
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
      default:
        level = 0; break;
    }
    return null;
  }

  @Override
  public void periodic() {
    
  }

  public void testEncoder () {
    double pos = m_elevatorEncoder.getPosition();
    String x = String.valueOf(pos);
    SmartDashboard.putString("DB/String 0", x);
  }

  public static void stopMotors(){
    m_elevatorMotor1.stopMotor();
    m_elevatorMotor2.stopMotor();
  }

}
