/*
 * Copyright 2017 TNG Technology Consulting GmbH
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
package com.tngtech.archunit.core.domain.properties;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.ChainableFunction;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;
import static com.tngtech.archunit.core.domain.properties.HasName.Predicates.name;
import static com.tngtech.archunit.core.domain.properties.HasReturnType.Functions.GET_RETURN_TYPE;

public interface HasReturnType {
    @PublicAPI(usage = ACCESS)
    JavaClass getReturnType();

    final class Predicates {
        private Predicates() {
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasReturnType> returnType(DescribedPredicate<? super JavaClass> predicate) {
            return predicate.onResultOf(GET_RETURN_TYPE).as("return type '%s'", predicate.getDescription());
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasReturnType> returnType(Class<?> returnType) {
            return returnType(returnType.getName());
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasReturnType> returnType(String returnTypeName) {
            return returnType(name(returnTypeName)).as("return type '%s'", returnTypeName);
        }
    }

    final class Functions {
        private Functions() {
        }

        @PublicAPI(usage = ACCESS)
        public static final ChainableFunction<HasReturnType, JavaClass> GET_RETURN_TYPE = new ChainableFunction<HasReturnType, JavaClass>() {
            @Override
            public JavaClass apply(HasReturnType input) {
                return input.getReturnType();
            }
        };
    }
}