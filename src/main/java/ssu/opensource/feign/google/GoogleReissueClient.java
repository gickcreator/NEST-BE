package ssu.opensource.feign.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ssu.opensource.constant.GoogleConstant;

@FeignClient(name="GoogleReissueClient", url = GoogleConstant.GOOGLE_TOKEN_URL)
public interface GoogleReissueClient {
    @PostMapping
    GoogleTokenResponse googleReissue(
            final GoogleReissueRequest googleReissueRequest
    );
}
