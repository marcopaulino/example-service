package mx.com.marcopaulino.exampleservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {

    private Long id;

    private String name;

    private String description;

}
