#version 150

in vec2 v_TexCoords;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat4 cleanViewMat;

uniform vec3 cameraPos;
uniform vec3 blobCenter;
uniform float u_Time;
uniform sampler2D Sampler0;

out vec4 fragColor;

float map(vec3 p) {
    vec3 localP = p - blobCenter;

    float core = length(localP) - 0.55;
    float displacement = sin(localP.x * 5.0 + u_Time * 2.0) *
    sin(localP.y * 5.0 + u_Time * 1.5) *
    sin(localP.z * 5.0 + u_Time * 1.2) * 0.15;
    float throb = sin(u_Time * 3.0) * 0.05;

    return core + displacement - throb;
}

vec3 calcNormal(vec3 p) {
    float eps = 0.01;
    vec2 e = vec2(1.0, -1.0) * eps;

    return normalize(
    e.xyy * map(p + e.xyy) +
    e.yyx * map(p + e.yyx) +
    e.yxy * map(p + e.yxy) +
    e.xxx * map(p + e.xxx)
    );
}

void main() {
    vec2 ndc = v_TexCoords * 2.0 - 1.0;

    vec4 clip = vec4(ndc, 1.0, 1.0);
    vec4 view = inverse(ProjMat) * clip;
    view /= view.w;

    vec3 rayDirView = normalize(view.xyz);
    mat3 invViewRot = transpose(mat3(cleanViewMat));
    vec3 rayDir = normalize(invViewRot * rayDirView);
    vec3 ro = cameraPos;

    vec3 oc = ro - blobCenter;
    float b = dot(oc, rayDir);
    float c = dot(oc, oc) - (1.5 * 1.5);
    float h = b*b - c;

    if(h < 0.0) discard;

    float t = -b - sqrt(h);
    t = max(0.0, t);

    float sceneDepth = texture(Sampler0, v_TexCoords).r;

    bool hit = false;
    vec3 p;

    for(int i = 0; i < 32; i++) {
        p = ro + rayDir * t;

        if(length(p - blobCenter) > 1.55) break;

        vec4 projP = ProjMat * cleanViewMat * vec4(p - cameraPos, 1.0);
        float currentDepth = (projP.z / projP.w) * 0.5 + 0.5;

        if (sceneDepth < currentDepth) {
            break;
        }

        float d = map(p);

        if(d < 0.005) {
            hit = true;
            break;
        }
        t += d;
    }

    if(hit) {
        vec3 n = calcNormal(p);
        vec3 lightDir = normalize(vec3(0.5, 1.0, 0.3));
        float diff = max(dot(n, lightDir), 0.0);
        float fresnel = pow(1.0 - max(dot(n, -rayDir), 0.0), 3.0);

        vec3 bloodBase = vec3(0.5, 0.0, 0.05);
        vec3 bloodGlow = vec3(1.0, 0.1, 0.1);

        vec3 finalColor = bloodBase * (diff * 0.8 + 0.2) + (bloodGlow * fresnel);
        fragColor = vec4(finalColor, 0.95);
    } else {
        discard;
    }
}