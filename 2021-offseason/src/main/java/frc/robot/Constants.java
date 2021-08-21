// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class kIntake { 
        public static final int INTAKE_PORT = 0;
        public static final int INTAKE_SPEED = 1;
        public static final int SOLENOID_FRONT = 0;
        public static final int SOLENOID_BACK = 1;
        public static final boolean INVERTED = false;
    }

    public static final class kHopper {
        public static final int LEFT_ID = 0;
        public static final int RIGHT_ID = 0;
        public static final int KICKER_ID = 0;
        public static final double FORWARD_SPEED = 0.2;
        public static final double REVERSE_SPEED = FORWARD_SPEED * -1;
        public static final boolean INVERTED = false;
    }

    public static final class kOI {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
    }

    public static final class kFlywheel {
        public static final int LEFT_ID = 0;
        public static final int RIGHT_ID = 0;
        public static final CANSparkMaxLowLevel.MotorType MOTOR_TYPE = CANSparkMaxLowLevel.MotorType.kBrushless;
        public static final int CURRENT_LIMIT = 40;
        public static final double SPEED = 0.5;
    }
}
