// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class kIntake { 
        public static final int INTAKE_PORT = 7;
        public static final int INTAKE_SPEED = 1;
        public static final int PCM_ID = 17;
        public static final int SOLENOID_FRONT = 0;
        public static final int SOLENOID_BACK = 1;
        public static final boolean INVERTED = false;

        // sparks for testing
        public static final int TEST_SPARK_PORT = 15;
        public static final int TEST_SPARK_PORT_SECOND = 3; 
    }

    public static final class kHopper {
        public static final int LEFT_ID = 11;
        public static final int RIGHT_ID = 8;
        public static final int KICKER_ID = 9;
        public static final double FORWARD_SPEED = 0.2;
        public static final double REVERSE_SPEED = FORWARD_SPEED * -1;
        public static final boolean INVERTED = false;
    }

    public static final class kOI {
        public static final int DRIVE_CONTROLLER = 0;
        public static final int OPERATOR_CONTROLLER = 1;
    }
}
