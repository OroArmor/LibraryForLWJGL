#version 330 core 
 
layout(location = 0) out vec4 color; 

in vec2 v_texCoor;
in vec4 v_normal;
in vec4 v_lightDir;
in vec4 v_lightPos;
in vec4 v_position;

uniform sampler2D u_Texture;
uniform vec3 u_lightColor;
uniform float u_lightStrength;

void main(){
	vec4 texColor = texture(u_Texture, v_texCoor);
	
	float sunlight = max(0.2, dot(normalize(v_normal), normalize(v_lightDir)));
	
	float lamplightStrength = dot(normalize(v_normal), normalize(v_lightPos)) * u_lightStrength / (length(v_position - v_lightPos) * length(v_position - v_lightPos));
	
	color = texColor * sunlight + vec4(u_lightColor, 0) * lamplightStrength + vec4(0,0,0,100);
}
