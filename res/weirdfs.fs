#version 330 core 
 
layout(location = 0) out vec4 color; 

in vec2 v_tex;

void main(){
	color = vec4(v_tex.x, v_tex.y,0,1);
}
