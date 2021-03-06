package com.marginallyclever.robotOverlord;

import javax.vecmath.Vector3f;

import com.jogamp.opengl.GL2;

public class LightObject extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3002135757633709477L;
	
	public int index=0;
	private boolean enabled=true;
	private float[] position={1,1,1,0};
	private float[] ambient={0.0f,0.0f,0.0f,1f};
	private float[] diffuse={1f,1f,1f,1f};
	private float[] specular={0.5f,0.5f,0.5f,1f};
    
	
	public void render(GL2 gl2) {
		int i = GL2.GL_LIGHT0+index;
		if(!enabled) {
			gl2.glDisable(i);
			return;
		}
		gl2.glEnable(i);
		gl2.glLightfv(i, GL2.GL_POSITION, position,0);
	    gl2.glLightfv(i, GL2.GL_AMBIENT, ambient,0);
	    gl2.glLightfv(i, GL2.GL_DIFFUSE, diffuse,0);
	    gl2.glLightfv(i, GL2.GL_SPECULAR, specular,0);
	}

	public void enable() {
		enabled=true;
	}

	public void disable() {
		enabled=false;
	}
	
	public boolean isDirectional() {
		return position[3]==0;
	}
	
	/**
	 * 
	 * @param arg0 true for directional light, false for point source light.
	 */
	public void setDirectional(boolean arg0) {
		position[3] = arg0 ? 0 : 1;
	}
	
	@Override
	public void setPosition(Vector3f p) {
		super.setPosition(p);
		position[0] = p.x;
		position[1] = p.y;
		position[2] = p.z;
	}
	
	public void setDiffuse(float r,float g,float b,float a) {
		diffuse[0]=r;
		diffuse[1]=g;
		diffuse[2]=b;
		diffuse[3]=a;
	}

	public void setAmbient(float r,float g,float b,float a) {
		ambient[0]=r;
		ambient[1]=g;
		ambient[2]=b;
		ambient[3]=a;
	}

	public void setSpecular(float r,float g,float b,float a) {
		specular[0]=r;
		specular[1]=g;
		specular[2]=b;
		specular[3]=a;
	}
}
