package com.oroarmor.core.opengl;

import java.util.HashMap;

import com.oroarmor.core.Bindable;
import com.oroarmor.core.Destructor;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL20.*;

public class Shader implements Bindable {
    /**
     * The different ids of the shader programs
     */
    protected int[] ids;

    /**
     * The id of the shader
     */
    protected int shaderProgramID;

    /**
     * A map of the uniforms and their integer values
     */
    private final HashMap<String, Integer> uniforms;

    /**
     * The source of the shader programs
     */
    private final String vertexSource;
    private final String fragmentSource;

    /**
     * Creates a {@link Shader} with the vertex and fragment sources
     *
     * @param vertexSource
     * @param fragmentSource
     */
    public Shader(String vertexSource, String fragmentSource) {
        this.vertexSource = vertexSource;
        this.fragmentSource = fragmentSource;

        addShader(GL_VERTEX_SHADER, vertexSource);
        addShader(GL_FRAGMENT_SHADER, fragmentSource);

        uniforms = new HashMap<>();

        Destructor.addDestructable(this::destroy);
    }

    /**
     * Adds a shader program to the ids of the shader
     *
     * @param shaderType   Type of the shader, i.e. Vertex, Fragment, or Geometry
     * @param shaderSource String source of the shader
     */
    protected void addShader(int shaderType, String shaderSource) {
        int id = glCreateShader(shaderType);
        glShaderSource(id, shaderSource);
        glCompileShader(id);

        if (ids == null) {
            ids = new int[]{id};
        } else {

            int[] newIDs = new int[ids.length + 1];

            for (int i = 0; i < ids.length; i++) {
                newIDs[i] = ids[i];
            }
            newIDs[ids.length] = id;
            ids = newIDs;
        }
    }

    @Override
    public void bind() {
        glUseProgram(shaderProgramID);
    }

    /**
     * Compile the shader, adding all the programs to the shader
     */
    public void compile() {
        int program = glCreateProgram();

        for (int id : ids) {
            glAttachShader(program, id);
        }

        glLinkProgram(program);
        glValidateProgram(program);

        shaderProgramID = program;
    }

    public void destroy() {
        glDeleteProgram(shaderProgramID);
        uniforms.clear();
        glDeleteShader(shaderProgramID);
    }

    /**
     * @return The fragment source
     */
    public String getFragmentSource() {
        return fragmentSource;
    }

    /**
     * An optimization to not find the uniforms each call, storing them in a hash
     * map
     *
     * @param name Name of the uniform
     * @return The integer value of the uniform
     */
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

    /**
     * @return The vertex source
     */
    public String getVertexSource() {
        return vertexSource;
    }

    /**
     * @param name Name of the uniform
     * @param v0   Value to set the uniform
     */
    public void setUniform1f(String name, float v0) {
        glUniform1f(getUniformLocation(name), v0);
    }

    /**
     * @param name Name of the uniform
     * @param v0   Value to set the uniform
     */
    public void setUniform1i(String name, int v0) {
        glUniform1i(getUniformLocation(name), v0);
    }

    /**
     * @param name   Name of the uniform
     * @param vector Vector to set the uniform
     */
    public void setUniform3f(String name, Vector3f vector) {
        glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
    }

    /**
     * @param name Name of the uniform
     * @param v0   Value to set the uniform
     * @param v1   Value to set the uniform
     * @param v2   Value to set the uniform
     * @param v3   Value to set the uniform
     */
    public void setUniform4f(String name, float v0, float v1, float v2, float v3) {
        glUniform4f(getUniformLocation(name), v0, v1, v2, v3);
    }

    /**
     * @param name   Name of the uniform
     * @param values Vector to set the uniform
     */
    public void setUniform4f(String name, Vector4f values) {
        this.setUniform4f(name, values.x, values.y, values.z, values.w);
    }

    /**
     * @param name   Name of the uniform
     * @param values Matrix to set the uniform
     */
    public void setUniformMat4f(String name, Matrix4f values) {
        glUniformMatrix4fv(getUniformLocation(name), false, values.get(BufferUtils.createFloatBuffer(4 * 4)));
    }

    @Override
    public void unbind() {
        glUseProgram(0);
    }

}
