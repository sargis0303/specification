package am.aca.specification.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessToken;
    private Date accessTokenExpirationDate;

}
