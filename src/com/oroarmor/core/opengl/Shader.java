package com.oroarmor.core.opengl;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL45.*;

import java.util.HashMap;

import org.joml.*;
import org.lwjgl.BufferUtils;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructable;

public class Shader implements Bindable, Destructable {

	private String vertexSource, fragmentSource;

	private int shaderProgramID;

	private HashMap<String, Integer> uniforms;

	public Shader(String vertexSource, String fragmentSource) {
		this.vertexSource = vertexSource;
		this.fragmentSource = fragmentSource;

		shaderProgramID = createShader(vertexSource, fragmentSource);
		uniforms = new HashMap<String, Integer>();
	}

	public String getVertexSource() {
		return vertexSource;
	}

	public String getFragmentSource() {
		return fragmentSource;
	}

	@Override
	public void destroy() {
		glDeleteProgram(shaderProgramID);
		uniforms.clear();
		glDeleteShader(shaderProgramID);
	}

	@Override
	public void bind() {
		glUseProgram(shaderProgramID);
	}

	@Override
	public void unbind() {
		glUseProgram(0);
	}

	public void setUniform4f(String name, Vector4f values) {
		setUniform4f(name, values.x, values.y, values.z, values.w);
	}

	public void setUniform4f(String name, float v0, float v1, float v2, float v3) {
		glUniform4f(getUniformLocation(name), v0, v1, v2, v3);
	}

	public void setUniform1i(String name, int v0) {
		glUniform1i(getUniformLocation(name), v0);
	}

	public void setUniformMat4f(String name, Matrix4f values) {
		glUniformMatrix4fv(getUniformLocation(name), false, values.get(BufferUtils.createFloatBuffer(4 * 4)));
	}

	private int getUniformLocation(String name) {

		Integer uniformLocation;

		if (!uniforms.containsKey(name)) {
			uniformLocation = glGetUniformLocation(shaderProgramID, name);
			uniforms.put(name, uniformLocation);
		} else {
			uniformLocation = uniforms.get(name);
		}

		return uniformLocation;
	}

	private int compileShader(int shaderType, String shaderSource) {
		int id = glCreateShader(shaderType);
		glShaderSource(id, shaderSource);
		glCompileShader(id);

		return id;
	}

	private int createShader(String vertexShaderSource, String fragmentShaderSource) {
		int program = glCreateProgram();

		int vertexShader = compileShader(GL_VERTEX_SHADER, vertexShaderSource);
		int fragmentShader = compileShader(GL_FRAGMENT_SHADER, fragmentShaderSource);

		glAttachShader(program, vertexShader);
		glAttachShader(program, fragmentShader);
		glLinkProgram(program);

		glValidateProgram(program);

		return program;
	}

	public void setUniform3f(String name, Vector3f vector) {
		glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
	}

	public void setUniform1f(String name, float v0) {
		glUniform1f(getUniformLocation(name), v0);
	}

}
