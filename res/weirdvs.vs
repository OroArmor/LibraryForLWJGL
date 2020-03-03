#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec2 texCoord;
layout(location = 2) in vec3 normal;

uniform mat4 u_MV;
uniform mat4 u_P;

out vec2 v_tex;

void main(){
	v_tex = texCoord;
 	gl_Position = u_MV * u_P * position; 
}
