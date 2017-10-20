/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.http;

import org.particleframework.core.convert.value.MutableConvertibleValues;

import java.util.Locale;
import java.util.Optional;

/**
 * Common interface for HTTP messages
 *
 * @param <B> The body type
 *
 * @see HttpRequest
 * @see HttpResponse
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public interface HttpMessage<B> {
    /**
     * @return The {@link HttpHeaders} object
     */
    HttpHeaders getHeaders();

    /**
     * <p>A {@link MutableConvertibleValues} of the attributes for this HTTP message.</p>
     *
     * <p>Attributes are designed for internal data sharing and hence are isolated from headers and parameters which are client supplied</p>
     *
     * @return The attributes of the message
     */
    MutableConvertibleValues<Object> getAttributes();

    /**
     * @return The request body
     */
    B getBody();

    /**
     * Return the body as the given type
     * @param type The type of the body
     * @param <T> The generic type
     * @return An {@link Optional} of the type or {@link Optional#empty()} if the body cannot be returned as the given type
     */
    <T> Optional<T> getBody(Class<T> type);

    /**
     * @return The locale of the message
     */
    default Locale getLocale() {
        return getHeaders().findFirst(HttpHeaders.CONTENT_LANGUAGE)
                .map(Locale::new)
                .orElse(null);
    }

    /**
     * @return The value of the Content-Length header or -1L if none specified
     */
    default long getContentLength() {
        return getHeaders()
                .contentLength()
                .orElse(-1L);
    }
    /**
     * The request or response content type
     * @return The content type
     */
    default MediaType getContentType() {
        return getHeaders()
                .contentType()
                .orElse(null);
    }

}
