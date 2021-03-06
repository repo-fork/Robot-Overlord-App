package com.marginallyclever.robotOverlord.model.modelLoadAndSavers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import com.marginallyclever.convenience.MathHelper;
import com.marginallyclever.robotOverlord.model.Model;
import com.marginallyclever.robotOverlord.model.ModelLoadAndSave;

/**
 * 
 * @author Admin
 *
 */
// see https://en.wikipedia.org/wiki/Wavefront_.obj_file
public class ModelLoadAndSaveOBJ implements ModelLoadAndSave {

	@Override
	public boolean canLoad(String filename) {
		boolean result = filename.toLowerCase().endsWith(".obj");
		return result;
	}

	@Override
	public boolean canLoad() {
		return true;
	}

	@Override
	public Model load(BufferedInputStream inputStream) throws Exception {
		Model model = new Model();

		ArrayList<Float> vertexArray = new ArrayList<Float>();
		ArrayList<Float> normalArray = new ArrayList<Float>();
		ArrayList<Float> texCoordArray = new ArrayList<Float>();

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		String line;
		while( ( line = br.readLine() ) != null ) {
			line = line.trim();
			if(line.startsWith("v ")) {
				// vertex
				String[] tokens = line.split("\\s+");
				vertexArray.add(Float.parseFloat(tokens[1]));
				vertexArray.add(Float.parseFloat(tokens[2]));
				vertexArray.add(Float.parseFloat(tokens[3]));
			} else if(line.startsWith("vn ")) {
				// normal - might not be unit length
				String[] tokens = line.split("\\s+");

				float x=Float.parseFloat(tokens[1]);
				float y=Float.parseFloat(tokens[2]);
				float z=Float.parseFloat(tokens[3]);
				float len = MathHelper.length(x,y,z);
				x/=len;
				y/=len;
				z/=len;
				
				normalArray.add(x);
				normalArray.add(y);
				normalArray.add(z);
			} else if(line.startsWith("vt ")) {
				// texture coordinate
				String[] tokens = line.split("\\s+");
				texCoordArray.add(Float.parseFloat(tokens[1]));
				texCoordArray.add(Float.parseFloat(tokens[2]));
			} else if(line.startsWith("f ")) {
				// face
				String[] tokens = line.split("\\s+");
				System.out.println("face len="+tokens.length);
				int index;
				for(int i=1;i<tokens.length;++i) {
					String [] subTokens = tokens[i].split("/");
					// vertex data
					index = Integer.parseInt(subTokens[0]);
					model.addVertex(
							vertexArray.get(index*3+0),
							vertexArray.get(index*3+1),
							vertexArray.get(index*3+2));
					// texture data (if any)
					if(subTokens.length>1 && subTokens[1].length()>0) {
						index = Integer.parseInt(subTokens[1]);
						model.addTexCoord(
								texCoordArray.get(index*3+0),
								texCoordArray.get(index*3+1));
					}
					// normal data (if any)
					if(subTokens.length>2 && subTokens[2].length()>0) {
						index = Integer.parseInt(subTokens[2]);
						model.addNormal(
								normalArray.get(index*3+0),
								normalArray.get(index*3+1),
								normalArray.get(index*3+2));
					}
				}
			}
		}
		
		return model;
	}

	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canSave(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save(OutputStream inputStream, Model model) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
