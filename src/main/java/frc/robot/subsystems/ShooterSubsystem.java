package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends PIDSubsystem {

    private final CANSparkMax shooter1 = new CANSparkMax(ShooterConstants.kShooter1Port, MotorType.kBrushless);
    private final CANSparkMax shooter2 = new CANSparkMax(ShooterConstants.kShooter2Port, MotorType.kBrushless);

    private final CANSparkMax transport = new CANSparkMax(ShooterConstants.kTransportMotor, MotorType.kBrushless);


    private final Encoder encoder = new Encoder(
            ShooterConstants.kEncoder1Port,
            ShooterConstants.kEncoder2Port,
            false);

    private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(ShooterConstants.kS,
            ShooterConstants.kV);

    public ShooterSubsystem(PIDController controller) {
        super(controller);
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        shooter1.setVoltage(output + feedforward.calculate(setpoint));
        shooter2.setVoltage(output + feedforward.calculate(setpoint));
    }

    @Override
    protected double getMeasurement() {
        return encoder.getRate();
    }

    public void up() {
        transport.set(0.8);
    }

    public void down() {
        transport.set(-0.5);
    }

    public void stopTransport() {
        transport.stopMotor();
    }

}
