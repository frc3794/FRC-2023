package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants.MStageConstants;

public class MovileStage extends SubsystemBase {
  private static final TalonSRX m_movileStageMotor = new TalonSRX(4);
  private final static DigitalInput m_forwardLimit = new DigitalInput(MStageConstants.kForwardLimit);
  private final static DigitalInput m_reverseLimit = new DigitalInput(MStageConstants.kReverseLimit);

  public static boolean isPressed;
  public MovileStage() {}

//Movile Stage Movement
  public static Command extendMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, MStageConstants.kMovileStageSpeed);
    return null;
  }
  public static Command retractMovileStage(){
    m_movileStageMotor.set(ControlMode.PercentOutput, -MStageConstants.kMovileStageSpeed);
    return null;
  }
  public static void getLimits(){
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