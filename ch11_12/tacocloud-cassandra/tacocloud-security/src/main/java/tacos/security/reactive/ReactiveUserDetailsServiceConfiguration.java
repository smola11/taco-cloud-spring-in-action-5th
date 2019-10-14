package tacos.security.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import tacos.User;
import tacos.data.UserRepository;

@Configuration
public class ReactiveUserDetailsServiceConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        return new ReactiveUserDetailsService() {
            @Override
            public Mono<UserDetails> findByUsername(String username) {
                return userRepository.findByUsername(username)
                    .map(User::toUserDetails);
            }
        };
    }

}
