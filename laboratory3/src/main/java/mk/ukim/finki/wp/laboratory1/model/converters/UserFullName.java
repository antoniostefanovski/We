package mk.ukim.finki.wp.laboratory1.model.converters;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFullName implements Serializable {
    private String clientName;
    private String surname;
}
