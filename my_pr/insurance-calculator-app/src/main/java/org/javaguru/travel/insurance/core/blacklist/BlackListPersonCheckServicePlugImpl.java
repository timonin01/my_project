package org.javaguru.travel.insurance.core.blacklist;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("h2")
public class BlackListPersonCheckServicePlugImpl implements BlackListPersonCheckService{

    private final Logger logger = LoggerFactory.getLogger(BlackListPersonCheckServicePlugImpl.class);

    @Override
    public boolean isPersonBlacklisted(PersonDTO personDTO) {
        logger.info("BlackList stub invoked! Always return false!");
        return false;
    }
}
