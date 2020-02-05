#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec3 normal;

uniform mat4 u_MV;
uniform mat4 u_P;
uniform vec3 u_lightDir;

out vec4 v_normal;
out vec4 v_lightDir;
out float v_height;

void main(){
 	gl_Position = u_MV * u_P * position;
 	
 	v_normal = u_MV * u_P * vec4(normal,0);
 	v_lightDir = u_MV * vec4(u_lightDir,0);
 	
 	v_height = position.y;
}
