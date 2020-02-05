#version 330 core 
 
layout(location = 0) out vec4 color; 

uniform float u_waterHeight;
uniform float u_sandHeight;
uniform float u_grassHeight;
uniform float u_rockHeight;
uniform float u_snowHeight;

in vec4 v_normal;
in vec4 v_lightDir;
in float v_height;

void main(){
	
	float sunlight = max(0.4, dot(normalize(v_normal), normalize(v_lightDir)));
	
	vec4 terrainColor = vec4(0,0,0,1);
	
	if(v_height < u_waterHeight){
		terrainColor = vec4(0,0,1,1);
	}  else  if (v_height < u_sandHeight){
		terrainColor = vec4(0.7,.7,.5,1);
	} else if(v_height< u_grassHeight){
		terrainColor = vec4(0.1,.9,.2,1);
	} else if(v_height< u_rockHeight){
		terrainColor = vec4(0.6,.55,.65,1);
	}else if(v_height< u_snowHeight){
		terrainColor = vec4(0.9,.9,.9,1);
	}
	
	color = terrainColor * sunlight + vec4(0,0,0,1);
}
