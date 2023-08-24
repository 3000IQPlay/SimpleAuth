package dev._3000IQPlay.simpleauth.ui.shaders;

public enum GLSLShaderList {
    BlueAurora("/assets/minecraft/shader/blueaurora.fsh"),
	CoolBlob("/assets/minecraft/shader/coolblob.fsh"),
    CoolTerrain("/assets/minecraft/shader/coolterrain.fsh"),
	CrazyColorMix("/assets/minecraft/shader/crazycolormix.fsh"),
    PurpleGradient("/assets/minecraft/shader/purplegradient.fsh");

    private static final GLSLShaderList[] shaderList = values();
    private String shader;

    GLSLShaderList(String s) {
        this.shader = s;
    }

    public static GLSLShaderList[] cloneList() {
        return shaderList.clone();
    }

    public static GLSLShaderList getShader(String name) {
        return valueOf(name);
    }

    public String getShader() {
        return shader;
    }
}