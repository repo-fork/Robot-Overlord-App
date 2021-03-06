package com.marginallyclever.robotOverlord.mantisRobot.tool;

import com.jogamp.opengl.GL2;
import com.marginallyclever.robotOverlord.PhysicalObject;
import com.marginallyclever.robotOverlord.mantisRobot.MantisRobot;
import com.marginallyclever.robotOverlord.model.Model;
import com.marginallyclever.robotOverlord.model.ModelFactory;

public abstract class MantisTool extends PhysicalObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5418173275880663460L;
	protected Model visibleShape = null;
	protected String shapeFile = null;
	protected MantisRobot attachedTo=null;

		
	public void attachTo(MantisRobot robot) {
		attachedTo=robot;
	}
	
	public MantisRobot getAttachedTo() {
		return attachedTo;
	}
	
	public void render(GL2 gl2) {
		if( visibleShape==null && shapeFile!=null ) {
			try {
				visibleShape = ModelFactory.createModelFromFilename(shapeFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(!visibleShape.isLoaded()) return;

		material.render(gl2);
		visibleShape.render(gl2);
	}
	
	public void updateGUI() {}
	
	public void update(float dt) {}
}
