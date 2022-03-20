package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TransportConstants;

public class InternalTransportSubsystem extends SubsystemBase {

    private final CANSparkMax motor1 = new CANSparkMax(TransportConstants.kTransport1, MotorType.kBrushless);

    public InternalTransportSubsystem() {
    }

    public void feed() {
        motor1.set(1.0);
    }

    public void stop() {
        motor1.stopMotor();
    }

    public void reverse() {
        motor1.set(-0.2);
    }

}
