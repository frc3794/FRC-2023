package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos {

  private int level = 1;
  private final Timer m_timer = new Timer ();

  public Autos() {}

  public void setLevel (int level) {
    this.level = level;
  }

  public Command autoCommand () {
    switch (this.level) {
      case 1: level1 ();
      case 2: level2 ();
      default: level1 ();
    }

    return new WaitCommand(0.0000);
  }

  private void level1 () {
    
  }

  private void level2 () {
    
  }
}
