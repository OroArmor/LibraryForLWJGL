#version 330 core 
 
layout(location = 0) out vec4 color; 

in vec2 v_texCoor;
in vec4 v_normal;
in vec4 v_lightDir;
in float v_height;

uniform sampler2D u_Texture;

void main(){
	vec4 texColor = texture(u_Texture, v_texCoor);
	
	float sunlight = max(0.2, dot(normalize(v_normal), normalize(v_lightDir)));
	
	color = texColor * sunlight + vec4(0,0,0,1);
	
	vec4 terrainColor = vec4(0,0,0,1);
	
	if(v_height < 0){
		terrainColor = vec4(0,0,1,1);
	}  else{
		terrainColor = vec4(0.1,.9,.2,1);
	}
	
	color = terrainColor * sunlight + vec4(0,0,0,1);
}
