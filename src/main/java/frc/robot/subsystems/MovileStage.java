package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax; 
import frc.robot.Constants.DrivetrainConstants;
import com.revrobotics.CANSparkMaxLowLevel;

public class MovileStage extends SubsystemBase {
  private static final CANSparkMax m_movileStageMotor = new CANSparkMax(DrivetrainConstants.kMotorPorts[6],
   CANSparkMaxLowLevel.MotorType.kBrushless);
 
  public MovileStage() {}
//Movile Stage Movement
  public static Command ExtendMovileStage(){
    m_movileStageMotor.set(.75);
    return null;
  }
  public static Command RetractMovileStage(){
    m_movileStageMotor.set(-.75);
    return null;
  }

  @Override
  public void periodic() {
    
  }
//Sus ඞ
  public static Command stopMotors(){
    m_movileStageMotor.stopMotor();
    return null;
  }
}