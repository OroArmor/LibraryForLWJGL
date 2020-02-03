#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec2 texCoor;
layout(location = 2) in vec3 normal;

uniform mat4 u_MV;
uniform mat4 u_P;
uniform vec3 u_lightDir;
uniform vec3 u_lightPos;

out vec2 v_texCoor;
out vec4 v_normal;
out vec4 v_lightDir;
out vec4 v_lightPos;
out vec4 v_position;

void main(){
	v_texCoor = texCoor;
 	gl_Position = u_MV * u_P * position;
 	v_normal = u_MV * u_P * vec4(normal,0);
 	v_lightDir = u_MV * vec4(u_lightDir,0);
 	
 	v_lightPos = u_MV * vec4(u_lightPos,0);
 	v_position = u_MV * u_P * position;
}
