package ge.alpin.javakhet.milkfactory.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

    private String name;

    private Object value;

    private SearchOperation operation;

}
