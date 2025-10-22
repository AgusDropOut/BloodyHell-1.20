#version 150

uniform sampler2D Sampler0;
uniform float Time;
uniform vec2 Resolution;
uniform vec3 LookDir;

in vec2 vTexCoord;
out vec4 fragColor;

// simple function to distort radius
float length2(vec2 p) {
    vec2 q = p*p*p*p;
    return pow(q.x + q.y, 0.25);
}

void main() {
    vec2 fragCoord = vTexCoord * Resolution;
    vec2 p = (2.0 * fragCoord - Resolution.xy) / Resolution.y;

    float r = length(p);
    float a = atan(p.y, p.x);

    // iris color
    vec3 col = vec3(0.0, 0.3, 0.4);
    col = mix(col, vec3(0.2,0.5,0.4), sin(r*8.0+Time)*0.5+0.5);
    col = mix(col, vec3(0.9,0.6,0.2), 1.0 - smoothstep(0.2, 0.6, r));

    // pupil
    float f = 1.0 - smoothstep(0.2, 0.25, r);
    col = mix(col, vec3(0.0), f);

    // highlight
    f = 1.0 - smoothstep(0.0, 0.6, length2(p - vec2(0.3, 0.5)));
    col += vec3(1.0, 0.9, 0.9) * f * 0.985;

    fragColor = vec4(col, 1.0);
}