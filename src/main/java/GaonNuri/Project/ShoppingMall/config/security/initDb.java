package GaonNuri.Project.ShoppingMall.config.security;

import GaonNuri.Project.ShoppingMall.user.data.entity.Authority;
import GaonNuri.Project.ShoppingMall.user.data.enums.AuthorityEnum;
import GaonNuri.Project.ShoppingMall.user.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final AuthorityRepository authorityRepository;

        //authority DB 에 ROLE_ADMIN, ROLE_USER 저장
        public void dbInit() {
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_ADMIN));
            authorityRepository.save(new Authority(AuthorityEnum.ROLE_USER));
        }
    }
}