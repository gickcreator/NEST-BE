package ssu.opensource.feign.google;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssu.opensource.constant.GoogleConstant;

@FeignClient(name="GoogleUnlinkClient", url = GoogleConstant.GOOGLE_UNLINK_URL)
public interface GoogleUnlinkClient {
    @PostMapping
    void googleUnlink(
            @RequestParam(GoogleConstant.TOKEN) final String token
    );
}