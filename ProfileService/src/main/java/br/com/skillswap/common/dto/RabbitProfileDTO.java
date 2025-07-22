package br.com.skillswap.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RabbitProfileDTO {


        private Long userId;
        private String firstName;
        private String lastName;

    }
