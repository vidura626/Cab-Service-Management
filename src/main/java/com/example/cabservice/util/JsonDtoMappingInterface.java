package com.example.cabservice.util;

/**
 * Interface for mapping between JSON, request DTOs, entities, and response DTOs.
 * This interface provides methods for converting between different representations
 * such as JSON strings, request DTOs, entities, and response DTOs.
 *
 * @param <T> The type of the request DTO.
 * @param <K> The type of the entity.
 * @param <W> The type of the response DTO.
 */
public interface JsonDtoMappingInterface<T, K, W> {

    /**
     * Converts a JSON string to the corresponding request DTO.
     *
     * @param json The JSON string to be converted.
     * @return The corresponding request DTO object of type T.
     */
    public T fromJsonToRequestDto(String json);

    /**
     * Converts a request DTO to the corresponding entity.
     *
     * @param request The request DTO to be converted.
     * @return The corresponding entity object of type K.
     */
    public K toEntity(T request);

    /**
     * Converts a response DTO to the corresponding response object.
     *
     * @param request The response DTO to be converted.
     * @return The corresponding response object of type K.
     */
    public W toResponseDto(K request);
    /**
     * Converts a response DTO to the json.
     *
     * @param request The response DTO to be converted.
     * @return The corresponding String object..
     */
    public String toJson(W request);
}
