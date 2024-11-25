package ssu.opensource.feign.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ssu.opensource.constant.GoogleConstant;

@FeignClient(name="GoogleInfoClient", url = GoogleConstant.GOOGLE_USER_INFO_URL)
public interface GoogleInfoClient {
    @GetMapping
    GoogleUserInfoResponse googleInfo(
            @RequestHeader(GoogleConstant.AUTHORIZATION) final String accessToken
    );

}