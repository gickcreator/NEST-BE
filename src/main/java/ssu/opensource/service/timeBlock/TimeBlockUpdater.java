package ssu.opensource.service.timeBlock;


import org.springframework.stereotype.Component;
import ssu.opensource.domain.TimeBlock;

import java.time.LocalDateTime;

@Component
public class TimeBlockUpdater {

    public void updateTime(
            final TimeBlock timeBlock,
            final LocalDateTime startTime,
            final LocalDateTime endTime
    ){
        timeBlock.updateTimeBlock(startTime, endTime);
    }
}
