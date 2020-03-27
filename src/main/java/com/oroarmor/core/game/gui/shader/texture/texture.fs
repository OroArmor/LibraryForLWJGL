#version 330 core 
 
layout(location = 0) out vec4 color; 

in vec2 v_texCoord;

uniform sampler2D u_Texture;

void main(){
	color = texture(u_Texture, vec2(v_texCoord.x, 1 - v_texCoord.y));
}
