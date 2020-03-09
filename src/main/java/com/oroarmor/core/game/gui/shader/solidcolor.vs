#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec2 texCoord;

uniform mat4 u_V;
uniform mat4 u_M;

void main(){
 	gl_Position = u_V * u_M * position;
}
