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
package ss.mod.demo.api.utils;

import lombok.experimental.UtilityClass;

/**
 * Contain Math related custom function
 *
 * @author Sunil Kanzar
 * @since 14th feb 2024
 */
@UtilityClass
public class MathUtil {
    public static Double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
