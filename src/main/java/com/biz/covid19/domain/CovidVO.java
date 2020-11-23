package com.biz.covid19.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CovidVO {
    private String id;
    private String countryName;
    private String newCase;
    private String totalCase;
    private String recovered;
    private String death;
    private String percentage;
    private String newFcase;
    private String newCcase;
}
