#version 330 core 
 
layout(location = 0) out vec4 color; 

uniform vec4 u_color;

uniform sampler2D u_Texture;

in vec2 v_texCoord;

void main(){
	color = u_color * texture(u_Texture, vec2(v_texCoord.x, 1 - v_texCoord.y));
}
