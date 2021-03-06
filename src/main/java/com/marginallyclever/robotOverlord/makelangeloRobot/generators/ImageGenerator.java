package com.marginallyclever.robotOverlord.makelangeloRobot.generators;

import java.io.IOException;
import java.io.Writer;

import com.jogamp.opengl.GL2;
import com.marginallyclever.robotOverlord.makelangeloRobot.ImageManipulator;
import com.marginallyclever.robotOverlord.makelangeloRobot.MakelangeloRobotDecorator;
import com.marginallyclever.robotOverlord.makelangeloRobot.settings.MakelangeloRobotSettings;
import com.marginallyclever.robotOverlord.RobotOverlord;

/**
 * Generators create gcode from user input.  Fractals might be one example.
 * Don't forget http://www.reverb-marketing.com/wiki/index.php/When_a_new_style_has_been_added_to_the_Makelangelo_software
 * @author dan royer
 *
 */
public abstract class ImageGenerator extends ImageManipulator implements MakelangeloRobotDecorator {
	/**
	 * @return true if generate succeeded.
	 * @param gui ??
	 * @param out where to write the gcode
	 * @throws IOException if writing fails.
	 */
	public boolean generate(RobotOverlord gui,Writer out) throws IOException {
		return false;
	}
	
	/**
	 * live preview as the system is generating.
	 * draw the results as the calculation is being performed.
	 */
	public void render(GL2 gl2, MakelangeloRobotSettings settings) {}
}
