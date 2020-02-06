#version 330 core 
 
#define MAX_POINT_LIGHTS 10
 
layout(location = 0) in vec4 position; 
layout(location = 1) in vec3 normal;
layout(location = 2) in float height;

uniform mat4 u_MV;
uniform mat4 u_P;

out vec4 v_normal;
out float v_height;

struct PointLight
{
	vec3 position;
	vec4 color;
	float strength;
};

uniform PointLight u_pointlights[MAX_POINT_LIGHTS];
out vec4 v_vectorToPointLights[MAX_POINT_LIGHTS];

void main(){
 	gl_Position = u_MV * u_P * position;
 	
 	v_normal = u_P * vec4(normal,0);
 	v_height = height;
	
	for(int i = 0; i < MAX_POINT_LIGHTS; i++){
		v_vectorToPointLights[i] = u_P * position - vec4(u_pointlights[i].position,0);
	}
	
}
