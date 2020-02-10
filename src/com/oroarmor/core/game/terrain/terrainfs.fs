#version 330 core 
 
#define MAX_SUN_LIGHTS 3
#define MAX_POINT_LIGHTS 10

layout(location = 0) out vec4 color; 

uniform float u_waterHeight;
uniform float u_waterWarmHeight;
uniform float u_sandHeight;
uniform float u_grassHeight;
uniform float u_rockHeight;
uniform float u_snowHeight;

in vec4 v_normal;
in float v_height;

struct SunLight
{
	vec3 direction;
	vec4 color;
};

uniform SunLight u_sunlights[MAX_SUN_LIGHTS];

struct PointLight
{
	vec3 position;
	vec4 color;
	float strength;
};

uniform PointLight u_pointlights[MAX_POINT_LIGHTS];
in vec4 v_vectorToPointLights[MAX_POINT_LIGHTS];

void main(){
	
	float sunlightValues[MAX_SUN_LIGHTS];
	
	vec4 normal = normalize(v_normal);
	
	for(int i = 0; i < MAX_SUN_LIGHTS; i++){
		sunlightValues[i] = max(0.2, dot(normal, normalize(vec4(u_sunlights[i].direction,0))))
		+ max(0.1, 1 - dot(normal, normalize(-vec4(u_sunlights[i].direction,0))))*0.2;
	}
	
	float pointlightValues[MAX_POINT_LIGHTS];
	
	for(int i = 0; i < MAX_POINT_LIGHTS; i++){
		float dist = length(v_vectorToPointLights[i]);
		pointlightValues[i] = max(0,dot(normal, normalize(v_vectorToPointLights[i])) 
		* u_pointlights[i].strength/(1 + 0.1*dist+ 0.01*dist*dist));
	}

	vec4 terrainColor = vec4(0,0,0,1);
	if(v_height < u_waterHeight){
		terrainColor = vec4(0,0,1,1);
	}  else if(v_height < u_waterWarmHeight){
		terrainColor = vec4(.3,.5,1,1);
	}  else  if (v_height < u_sandHeight){
		terrainColor = vec4(0.7,.7,.5,1);
	} else if(v_height< u_grassHeight){
		terrainColor = vec4(0.1,.9,.2,1);
	} else if(v_height< u_rockHeight){
		terrainColor = vec4(0.6,.55,.65,1);
	}else if(v_height< u_snowHeight){
		terrainColor = vec4(0.9,.9,.9,1);
	}
	
	color = vec4(0,0,0,1);
	
	for(int i = 0; i < MAX_SUN_LIGHTS; i++){
		color = color + terrainColor * u_sunlights[i].color * vec4(sunlightValues[i],sunlightValues[i],sunlightValues[i],1);
	}
	
	for(int i = 0; i < MAX_POINT_LIGHTS; i++){
		color = color + terrainColor * u_pointlights[i].color * vec4(pointlightValues[i],pointlightValues[i],pointlightValues[i],1);
	}
}
