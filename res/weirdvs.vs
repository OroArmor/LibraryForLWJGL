#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec2 texCoord;

uniform mat4 u_MV;
uniform mat4 u_P;

out vec4 frag_pos;

void main(){
	frag_pos = vec4(position.x,position.y,0,0);
 	gl_Position = u_MV * u_P * position; 
}
