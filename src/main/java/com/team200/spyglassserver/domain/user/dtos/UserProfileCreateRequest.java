package com.team200.spyglassserver.domain.user.dtos;

import lombok.*;

public class UserProfileCreateRequest {
    @Getter
    @Setter
    @NoArgsConstructor
    @RequiredArgsConstructor
    @ToString
    public class UserProfileDTO {
        @NonNull
        private String firstName;

        @NonNull
        private String lastName;

        @NonNull
        private String email;

        @NonNull
        private String password;
    }
}
