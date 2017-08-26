package mmdb.kvs.app;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class AppConfiguration extends Configuration {
    @NotEmpty
    private Long port;

    @JsonProperty
    public Long getTemplate() {
        return port;
    }

    @JsonProperty
    public void setTemplate(Long template) {
        this.port = template;
    }

}