package task02;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SomeObject implements Serializable {
    private Integer number;
    private Double someDouble;
    private List<String> namesList;
}
