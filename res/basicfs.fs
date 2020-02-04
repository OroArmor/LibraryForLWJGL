#version 330 core 
 
layout(location = 0) out vec4 color; 

in vec2 v_texCoor;
in vec4 v_normal;
in vec4 v_lightDir;

uniform sampler2D u_Texture;

void main(){
	vec4 texColor = texture(u_Texture, v_texCoor);
	
	float sunlight = max(0.2, dot(normalize(v_normal), normalize(v_lightDir)));
	
	color = texColor * sunlight + vec4(0,0,0,1);
	color = vec4(v_texCoor.x,v_texCoor.y,0,1);
}
