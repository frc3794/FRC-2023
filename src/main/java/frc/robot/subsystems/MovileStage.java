package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.MStageConstants;

public class MovileStage extends SubsystemBase {
  private static final VictorSPX m_movileStageMotor = new VictorSPX(DrivetrainConstants.kMotorPorts[3]);
  private final static DigitalInput m_forwardLimit = new DigitalInput(MStageConstants.forwardLimit);
  private final static DigitalInput m_reverseLimit = new DigitalInput(MStageConstants.reverseLimit);

  public static boolean isPressed;
  public MovileStage() {}

//Movile Stage Movement
  public static Command ExtendMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, .75);
    return null;
  }
  public static Command RetractMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, -.75);
    return null;
  }
  public static void GetLimits(){
    if (m_forwardLimit.get() || m_reverseLimit.get()){
      isPressed = true;
    } else {
      isPressed = false;
    }
    
    if (isPressed){
      m_movileStageMotor.set(ControlMode.PercentOutput, 0);
    } 
  }

  @Override
  public void periodic() {
    
  }
//Sus ඞ
  public static Command stopMotors(){
    m_movileStageMotor.set(ControlMode.Disabled, 0);
    return null;
  }
}