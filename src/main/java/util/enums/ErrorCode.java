package util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    RUNTIME_ERROR("ERR-500", "Generic Error"),
    DATABASE_ERROR("ERR-501", "Database connection failed"),
    VALIDATION_ERROR("ERR-400", "Invalid input data");

    private final String code;
    private final String description;
}
