package testdata;

public enum AuthCredentialsLogin {
    STANDARD_USER("standard_user"), PERFORMANCE_GLITCH_USER("performance_glitch_user");
private final String desc;

AuthCredentialsLogin(String desc){
    this.desc=desc;
}
public String getDesc(){
    return desc;
}
}
