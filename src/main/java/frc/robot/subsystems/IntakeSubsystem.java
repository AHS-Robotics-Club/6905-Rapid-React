package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {


    private final Solenoid intakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, IntakeConstants.kIntakeSolenoid);

    private final CANSparkMax intakeMotor = new CANSparkMax(IntakeConstants.kIntake1Port, MotorType.kBrushless);

    
    public void intake() {
        intakeMotor.set(0.9);
    }

    public void outtake() {
        intakeMotor.set(-0.9);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }

    public void lowerIntake() {
        intakeSolenoid.set(true);
    }

}
