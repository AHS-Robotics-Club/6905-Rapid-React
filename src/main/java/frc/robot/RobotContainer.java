// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private double mult = 1.0;

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem(
      new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD));
  private final LiftSubsystem liftSubsystem = new LiftSubsystem();

  private XboxController controller = new XboxController(OIConstants.controller);

  public RobotContainer() {
    configureButtonBindings();

    driveSubsystem.setDefaultCommand(
        new RunCommand(() -> driveSubsystem.drive(-controller.getLeftY(), controller.getRightX(), () -> mult),
            driveSubsystem));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Intake
    new JoystickButton(controller, Button.kLeftBumper.value)
        .whenHeld(new StartEndCommand(intakeSubsystem::outtake, intakeSubsystem::stop, intakeSubsystem));
    new JoystickButton(controller, Button.kRightBumper.value)
        .whenHeld(new StartEndCommand(intakeSubsystem::intake, intakeSubsystem::stop, intakeSubsystem));

    // Internal Transport
    new JoystickButton(controller, Button.kY.value)
        .whenHeld(new StartEndCommand(shooterSubsystem::up, shooterSubsystem::stopTransport, shooterSubsystem));
    new JoystickButton(controller, Button.kX.value)
        .whenHeld(new StartEndCommand(shooterSubsystem::down, shooterSubsystem::stopTransport, shooterSubsystem));

    // Shooter
    new JoystickButton(controller, Button.kA.value)
        .toggleWhenPressed(new StartEndCommand(shooterSubsystem::enable, shooterSubsystem::disable, shooterSubsystem));

    // Lift
    new POVButton(controller, 180)
        .toggleWhenPressed(new StartEndCommand(liftSubsystem::lift, liftSubsystem::pull, liftSubsystem));

    // Slow Mode
    new POVButton(controller, 0)
        .toggleWhenPressed(new StartEndCommand(() -> mult = 0.5, () -> mult = 1.0, driveSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
