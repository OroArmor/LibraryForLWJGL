#version 330 core 
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec2 texCoord;

uniform mat4 u_V;
uniform mat4 u_M;
uniform float u_Z;

void main(){
 	gl_Position = u_V * u_M * (position + vec4(0, 0, u_Z, 0));
}
