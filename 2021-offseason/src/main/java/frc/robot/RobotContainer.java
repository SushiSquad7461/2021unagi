// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final XboxController operatorController = new XboxController(Constants.kOI.OPERATOR_PORT);

  //Intake 
  private final Intake s_intake;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    s_intake = new Intake();
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  

  //Assign operator controller buttons A and Y to startIntake and startReverse respectively
  private void configureButtonBindings() {
    // A button -> run intake forward
    new JoystickButton(operatorController, XboxController.Button.kA.value)
        .whenPressed(new RunCommand(s_intake::startIntake, s_intake))
        .whenPressed(new RunCommand(s_intake::stopIntake, s_intake));   

    // B button -> run intake reverse
    new JoystickButton(operatorController, XboxController.Button.kY.value)
        .whenPressed(new RunCommand(s_intake::startReverse, s_intake))
        .whenPressed(new RunCommand(s_intake::stopIntake, s_intake)); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
