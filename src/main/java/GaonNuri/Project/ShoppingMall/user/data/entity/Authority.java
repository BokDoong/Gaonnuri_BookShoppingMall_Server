package GaonNuri.Project.ShoppingMall.user.data.entity;

import GaonNuri.Project.ShoppingMall.user.data.enums.AuthorityEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @Column(name = "authority_status")
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authorityStatus;

    public String getAuthorityStatus() {
        return this.authorityStatus.toString();
    }

    @Builder
    public Authority(AuthorityEnum authorityStatus) {
        this.authorityStatus = authorityStatus;
    }
}
