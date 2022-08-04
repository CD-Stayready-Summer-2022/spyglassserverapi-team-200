package com.team200.spyglassserver.domain.user.dtos;

import lombok.*;
    @Getter
    @Setter
    @NoArgsConstructor
    @RequiredArgsConstructor
    @ToString
    public class UserCreateRequest {
        @NonNull
        private String firstName;

        @NonNull
        private String lastName;

        @NonNull
        private String email;

        @NonNull
        private String password;
    }
