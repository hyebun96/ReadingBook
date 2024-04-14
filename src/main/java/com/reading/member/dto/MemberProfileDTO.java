package com.reading.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class MemberProfileDTO {

    private String profile_img_url;

    private String uuid;

    private String nickname;
}
