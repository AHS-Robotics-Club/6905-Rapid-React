// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoCommandTest;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsytem;
import frc.robot.subsystems.InternalTransportSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();

  private final InternalTransportSubsystem feederSubsystem = new InternalTransportSubsystem();

  private final IntakeSubsytem intakeSubsytem = new IntakeSubsytem();

  XboxController controller = new XboxController(OIConstants.kDriverControllerPort);

  private Trigger leftTrigger = new Trigger(() -> controller.getLeftTriggerAxis() > 0.3);
  private Trigger rightTrigger = new Trigger(() -> controller.getRightTriggerAxis() > 0.3);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    // I <3 my Lean-ona :)
    driveSubsystem.setDefaultCommand(new RunCommand(
        () -> driveSubsystem.drive(-controller.getLeftY(), controller.getRightX()), driveSubsystem));
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
    // Shooter
    new JoystickButton(controller, Button.kA.value).whenPressed(() -> shooterSubsystem.setMax());
    new JoystickButton(controller, Button.kB.value).whenPressed(() -> shooterSubsystem.stopShooter());

    // Feeder
    leftTrigger.whenActive(() -> feederSubsystem.reverse(), feederSubsystem)
        .whenInactive(() -> feederSubsystem.stop(), feederSubsystem);
    rightTrigger.whenActive(() -> feederSubsystem.feed(), feederSubsystem)
        .whenInactive(() -> feederSubsystem.stop(), feederSubsystem);

    // Intake (not)
    new JoystickButton(controller, Button.kLeftBumper.value)
        .whenHeld(new StartEndCommand(() -> intakeSubsytem.outtake(), () -> intakeSubsytem.stop(), intakeSubsytem));
    new JoystickButton(controller, Button.kRightBumper.value)
        .whenHeld(new StartEndCommand(() -> intakeSubsytem.intake(), () -> intakeSubsytem.stop(), intakeSubsytem));

    // Slowmode
    new POVButton(controller, 0)
        .toggleWhenPressed(
            new StartEndCommand(() -> driveSubsystem.setMaxOutput(0.2), () -> driveSubsystem.setMaxOutput(0.8)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoCommandTest(driveSubsystem, shooterSubsystem, feederSubsystem);
  }
}
