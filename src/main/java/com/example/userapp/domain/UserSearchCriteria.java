package com.example.userapp.domain;

import com.example.userapp.api.NationalIdNumber;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Builder
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserSearchCriteria {

    @Nullable
    private NationalIdNumber nationalIdNumber;

    public Optional<NationalIdNumber> getNationalIdNumber() {
        return Optional.ofNullable(nationalIdNumber);
    }

    @Value
    public static class ExternalIdentifierCriteria {
        String system;
        String value;

        public ExternalIdentifierCriteria(String value, String system) {
            this.value = requireNonNull(value);
            this.system = requireNonNull(system);
        }
    }

    public static class UserSearchCriteriaBuilder {
        Set<UUID> userUuids;
        Set<UUID> uuids;

        public UserSearchCriteriaBuilder uuids(Set<UUID> uuids) {
            this.uuids = requireNonNull(uuids);
            return this;
        }
    }
}
