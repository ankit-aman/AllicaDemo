package com.example.allicademo2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotBlank(message = "firstName should not be empty")
    @Size(min = 1, max = 20, message = "should have minimum 1 letter")
    private String firstName;

    @NotBlank(message = "lastName should not be empty")
    @Size(min = 1, max = 20, message = "should have minimum 1 letter")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
}
