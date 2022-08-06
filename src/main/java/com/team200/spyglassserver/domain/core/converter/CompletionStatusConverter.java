package com.team200.spyglassserver.domain.core.converter;

import com.team200.spyglassserver.domain.core.enums.CompletionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CompletionStatusConverter implements AttributeConverter<CompletionStatus, String> {

    @Override
    public String convertToDatabaseColumn(CompletionStatus completionStatus) {
        if (completionStatus == null) {
            return null;
        }
        return completionStatus.getValue();
    }

    @Override
    public CompletionStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CompletionStatus.values())
                .filter(c -> c.getValue().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}