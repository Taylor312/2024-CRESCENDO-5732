package frc.robot.subsystems.ShooterBoxSubsystem;

//rev imports

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.SparkPIDController;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase;
import com.revrobotics.RelativeEncoder;

//wristimports

import frc.robot.Constants.WristConstants;
import frc.utils.WristState;


public class Wrist {

    private final CANSparkMax m_wristNeoSpark;
    private final AbsoluteEncoder m_wristEncoder;
    private final SparkPIDController m_wristPIDController;
      
    public Wrist(int wristCANId) {

        m_wristNeoSpark = new CANSparkMax(wristCANId, MotorType.kBrushless);

        // Factory reset, so we get the SPARKS MAX to a known state before configuring
        // them. This is useful in case a SPARK MAX is swapped out.
        m_wristNeoSpark.restoreFactoryDefaults();

        // Setup encoder and PID controllers
        m_wristEncoder = m_wristNeoSpark.getAbsoluteEncoder(Type.kDutyCycle);
        m_wristPIDController = m_wristNeoSpark.getPIDController();
        m_wristPIDController.setFeedbackDevice(m_wristEncoder);

        // Apply position and velocity conversion factors for the turning encoder. We
        // want these in radians and radians per second to use with WPILib's swerve
        // APIs.
        m_wristEncoder.setPositionConversionFactor(WristConstants.kTurningEncoderPositionFactor);
        m_wristEncoder.setVelocityConversionFactor(WristConstants.kTurningEncoderVelocityFactor);

        // Inverts the encoder if nessercy 
        m_wristEncoder.setInverted(WristConstants.kTurningEncoderInverted);

        // Enable PID wrap around for the turning motor. This will allow the PID
        // controller to go through 0 to get to the setpoint i.e. going from 350 degrees
        // to 10 degrees will go through 0 rather than the other direction which is a
        // longer route.  (Only use for spefific cases)
        m_wristPIDController.setPositionPIDWrappingEnabled(true);
        m_wristPIDController.setPositionPIDWrappingMinInput(WristConstants.kTurningEncoderPositionPIDMinInput);
        m_wristPIDController.setPositionPIDWrappingMaxInput(WristConstants.kTurningEncoderPositionPIDMaxInput);

        // Set the PID gains for the turning motor

        m_wristPIDController.setP(WristConstants.kTurningP);
        m_wristPIDController.setI(WristConstants.kTurningI);
        m_wristPIDController.setD(WristConstants.kTurningD);
        m_wristPIDController.setFF(WristConstants.kTurningFF);
        m_wristPIDController.setOutputRange(WristConstants.kTurningMinOutput,
            WristConstants.kTurningMaxOutput);

        m_wristNeoSpark.setIdleMode(WristConstants.kTurningMotorIdleMode);
        m_wristNeoSpark.setSmartCurrentLimit(WristConstants.kTurningMotorCurrentLimit);

        // Save the SPARK MAX configurations. If a SPARK MAX browns out during
        // operation, it will maintain the above configurations.

        m_wristNeoSpark.burnFlash();
    }

    public WristState getState() {
        // returns the state of the wrist subsystem

        return new WristState();
    }

    public void setState(WristState e){

    }

}

