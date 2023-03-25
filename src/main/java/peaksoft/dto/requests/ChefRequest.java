package peaksoft.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * @author :ЛОКИ Kelsivbekov
 * @created 17.03.2023
 */
public record ChefRequest(
        @Size(min = 3, max = 30, message = "First name great between 2 and 30")
        @NotEmpty(message = "First Name must not be empty")
        String firstName,
        @Size(min = 3, max = 30, message = "Last name great between 2 and 30")
        @NotEmpty(message = "Last Name must not be empty")
        String lastName,
        LocalDate dateOfBrith,
        @Email(message = "Invalid email address")
        @NotEmpty
        String email,
        @Size(min = 4, max = 100, message = "Password great between 4 and 100")
        @NotEmpty()
        String password,
        @NotEmpty
        String phoneNumber,
        @NotNull
        Integer experience
) {
}
