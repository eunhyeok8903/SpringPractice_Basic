package SpringPractice.SpringPractice_Basic;

import SpringPractice.SpringPractice_Basic.repository.JpaMemberRepository;
import SpringPractice.SpringPractice_Basic.repository.MemberRepository;
import SpringPractice.SpringPractice_Basic.repository.MemoryMemberRepository;
import SpringPractice.SpringPractice_Basic.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


//직접 스프링 빈 등록하는것임(뭐뭐 등록되어있는지 의존관계가 한눈에들어와서 명확해짐)
@Configuration
public class SpringConfig {

    private final DataSource datasource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em){
        this.datasource = dataSource;
        this.em=em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}
