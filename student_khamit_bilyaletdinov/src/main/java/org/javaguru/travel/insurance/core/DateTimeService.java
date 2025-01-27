package org.javaguru.travel.insurance.core;

import java.math.BigDecimal;
import java.util.Date;

public interface DateTimeService {
    public BigDecimal calculateAgreementPrice(Date startDate, Date endDate);
}
