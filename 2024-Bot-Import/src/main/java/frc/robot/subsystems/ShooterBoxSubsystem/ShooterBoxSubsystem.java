package frc.robot.subsystems.ShooterBoxSubsystem;

import frc.robot.subsystems.ShooterBoxSubsystem.Wrist;
import frc.robot.subsystems.SwervedriveSubsystem.MAXSwerveModule;
import frc.robot.subsystems.ShooterBoxSubsystem.FlyWheel;
import frc.robot.subsystems.ShooterBoxSubsystem.Indexer;
import frc.robot.Constants.WristConstants;
import frc.utils.WristState;
import frc.utils.FlyWheelState;
import frc.utils.IndexerState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class ShooterBoxSubsystem extends SubsystemBase {

    private final Wrist wrist = new Wrist(WristConstants.WristNeoCanId);

    public ShooterBoxSubsystem(){
    }

    public void kina1(int speed){
        
        WristState open = new WristState();
        
        wrist.setState(open);
    }



}
