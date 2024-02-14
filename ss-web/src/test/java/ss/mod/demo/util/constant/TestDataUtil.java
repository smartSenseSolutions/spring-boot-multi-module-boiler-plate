package ss.mod.demo.util.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ss.mod.demo.api.model.request.UserRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataUtil {


    public static UserRequest getUserRequest() {
        return new UserRequest(TestConstant.TEST_USER_NAME, TestConstant.TEST_USER_AGE, TestConstant.TEST_USER_CITY, TestConstant.TEST_USER_COUNTRY);
    }
}
