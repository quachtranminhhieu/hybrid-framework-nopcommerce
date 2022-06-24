package environmentConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

// Biến ${env} giống như Dynamic. Lấy giá trị từ file xml thế vào
@Sources({"classpath:${env}.properties"})
public interface Environment extends Config{
	
	// Nếu tên key trùng với tên hàm thì không cần annotation @Key
	// Vd: File properties có 1 key osName = Windows 10
	// Khi khai báo trong file này có thể ghi 1 dòng String osName(); luôn mà không cần @Key("osName") ở trên
	
	@Key("app.url")
	String appURL();
	
	@Key("app.username")
	String appUsername();
	
	@Key("app.password")
	String appPassword();
}
