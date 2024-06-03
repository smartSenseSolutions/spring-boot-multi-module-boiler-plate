/*
 * Copyright 2024 smartSense Consulting Solutions Pvt. Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ss.mod.demo.web.apidocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class UserManagementResourceApiDocs {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(description = "Create User based on Provided Data", summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Any kind of Input validation", content = {
                    @Content(examples = {
                            @ExampleObject(name = "Any kind of Input validation", value = """
                                    {
                                       "code": "400-1",
                                       "message": "Invalid request content.",
                                       "status": 400,
                                       "errorParams": {
                                         "age": [
                                           "You are underage"
                                         ]
                                       }
                                    }
                                    """)
                    }) }),
            @ApiResponse(responseCode = "200", description = "User Detail", content = {
                    @Content(examples = {
                            @ExampleObject(name = "User Detail", value = """
                                     {
                                        "message": "User Created successfully",
                                        "body": {
                                          "id": "248b97f0-6f3a-4f56-af04-c0da600125b1",
                                          "name": "Name Surname",
                                          "age": 19,
                                          "city": "Some City",
                                          "country": "Some Country"
                                        }
                                      }
                                    """)
                    })
            }) })
    public @interface CreateUserApiDocs {
    }

    @Target({ ElementType.TYPE, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Any other internal server error", content = {
                    @Content(examples = {
                            @ExampleObject(name = "Internal server error", value = """
                                    {
                                       "code": "500-1",
                                       "message": "Something went wrong",
                                       "status": 500
                                    }
                                    """)
                    }) }) })
    public @interface Common500 {
    }
}
