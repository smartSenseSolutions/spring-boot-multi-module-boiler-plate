package ss.mod.demo.api.model.response;

import ss.mod.demo.api.model.BaseModel;

public record UserResponse(String id,
                           String name,
                           Integer age,
                           String city,
                           String country) implements BaseModel {
}
